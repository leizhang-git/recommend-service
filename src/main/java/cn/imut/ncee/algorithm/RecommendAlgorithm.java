package cn.imut.ncee.algorithm;

import cn.imut.ncee.entity.pojo.AlgorithmIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高考志愿推荐算法
 * @Author zhanglei
 * @Date 2021/3/15 14:27
 */
@Component
public class RecommendAlgorithm {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据用户所填写的意向信息，推荐合适的高校以及专业
     * 意向表包括：文理（0理，1文）、地区、分数、具体专业、专业类别(若输入专业，则默认忽略此指标)、
     * @param information 意向表 顺序为：subject、address、scoreInterval、majorName、majorCategory
     * @return 高校-专业
     */
    public Object majorRecommend(AlgorithmIndex information) {
        //返回结果：
        //格式：    高校：专业1，专业2，专业3...
        Map<String, List<String>> recommendList = new HashMap<>();
        String subject = information.getSubject();
        //用户输入指标（若用户）
        int subjectInt = -1;
        //若用户不传入科目，则自动默认用户不分文理（-1）（目前由于前端优化，已经解决此问题）
        if(subject.length() != 0) {
            subjectInt = Integer.parseInt(subject);
        }
        String address = information.getAddress();
        String score = information.getScore();
        String majorCategory = information.getMajorCategory();
        String numberJudge = "/\\d+/g";
        String scoreJudge = "^(750(\\.[0]+)?|7[0-4][0-9](\\.\\d+)?|[1-6][0-9][0-9](\\.\\d+)?|[1-9][0-9](\\.\\d+)?|[0-9](\\.\\d+)?)$";
        //根据科目获取所有专业id
        String subjectSql;
        //用户即没有输入科目同时没有输入专业类别
        if(subjectInt == -1 && majorCategory.length() == 0) {
            subjectSql = "select `major_id` from major_info;";
        }else if(subjectInt == -1) {    //用户没有输入科目，但是输入了专业类别
            subjectSql = "select `major_id` from major_info where major_category = '"+majorCategory+"';";
        }else if(majorCategory.length() == 0 && (subjectInt == 0 || subjectInt == 1)){      //用户没有输入类别，但是输入了科目
            subjectSql = "select `major_id` from major_info where subject = "+subjectInt+";";
        }else {     //用户科目与专业类别均输入
            subjectSql = "select `major_id` from major_info where subject = "+subjectInt+" and major_category = '"+majorCategory+"';";
        }

        //查询出
        List<Map<String, Object>> idList = jdbcTemplate.queryForList(subjectSql);
        if(idList.size() != 0) {
            for (Map<String, Object> stringObjectMap : idList) {
                String majorId = (String) stringObjectMap.get("major_id");
                //查询出开设此专业的所有高校Id,同时排除分数不符合的高校
                String universitySql;
                if(score.length() != 0) {
                    universitySql = "select distinct `university_id` from statistics_score where major_id = '" + majorId + "' and (min_score < " + score + " or avg_score - 10 < " + score + " or max_score - 20 < " + score + ");";
                }else {
                    universitySql = "select distinct `university_id` from statistics_score where major_id = '" + majorId + "'";
                }
                List<Map<String, Object>> universityId = jdbcTemplate.queryForList(universitySql);
                if (universityId.size() == 0) {
                    continue;
                }
                for (Map<String, Object> objectMap : universityId) {
                    List<String> majors = new ArrayList<>();
                    String uId = (String) objectMap.get("university_id");
                    //与用户输入的高校地址进行对比，不符合则进行下一个
                    String addressSql;
                    if(address.length() != 0) {
                        addressSql = "select `university_name` from `university_info` where `university_address` = '" + address + "' and `university_id` = '" + uId + "';";
                    }else {
                        addressSql = "select `university_name` from `university_info` where `university_id` = '" + uId + "';";
                    }
                    List<Map<String, Object>> finalResult = jdbcTemplate.queryForList(addressSql);
                    if (finalResult.size() == 0) {
                        continue;
                    }
                    //此高校满足用户输入地区指标
                    String uName = (String) finalResult.get(0).get("university_name");
                    String uIdSQL = "select `university_id` from `university_info` where `university_name` = '"+uName+"'";
                    List<Map<String, Object>> resuId = jdbcTemplate.queryForList(uIdSQL);
                    String unId = (String) resuId.get(0).get("university_id");
                    //获取该高校的所有专业（以便和上述专业进行对比）
                    String majorsSql;
                    if(subjectInt == -1) {
                        if(majorCategory.length() != 0 && score.length() != 0) {
                            majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info` where `major_category` =  '" + majorCategory + "' and  `statistics_score`.`university_id` = '" + unId + "' and (major_info.major_id = statistics_score.major_id) and (`statistics_score`.`min_score` < " + score + " or `statistics_score`.`avg_score` - 10 < " + score + " or `statistics_score`.`max_score` - 20 < " + score + ") and ("+score+" < `statistics_score`.`max_score`);";
                        }else if(majorCategory.length() != 0) {
                            majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info` where `major_category` =  '" + majorCategory + "' and  `statistics_score`.`university_id` = '" + unId + "' and  (major_info.major_id = statistics_score.major_id);";
                        }else if(score.length() != 0) {
                            majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info` where `statistics_score`.`university_id` = '" + unId   + "' and  (major_info.major_id = statistics_score.major_id) and (`statistics_score`.`min_score` < " + score + " or `statistics_score`.`avg_score` - 10 < " + score + " or `statistics_score`.`max_score` - 20 < " + score + ") and ("+score+" < `statistics_score`.`max_score`);";
                        }else {
                            majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info` where `statistics_score`.`university_id` = '" + unId + "' and  (major_info.major_id = statistics_score.major_id);";
                        }
                    }else {
                        if(majorCategory.length() != 0 && score.length() != 0) {
                            majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info` where `major_category` =  '" + majorCategory + "' and  `statistics_score`.`university_id` = '" + unId + "' and (major_info.major_id = statistics_score.major_id) and (`statistics_score`.`min_score` < " + score + " or `statistics_score`.`avg_score` - 10 < " + score + " or `statistics_score`.`max_score` - 20 < " + score + ") and ("+score+" < `statistics_score`.`max_score`) and `subject` = "+subjectInt+";";
                        }else if(majorCategory.length() != 0) {
                            majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info` where `major_category` =  '" + majorCategory + "' and  `statistics_score`.`university_id` = '" + unId + "' and  (major_info.major_id = statistics_score.major_id) and `subject` = "+subjectInt+";";
                        }else if(score.length() != 0) {
                            majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info` where `statistics_score`.`university_id` = '" + unId   + "' and  (major_info.major_id = statistics_score.major_id) and (`statistics_score`.`min_score` < " + score + " or `statistics_score`.`avg_score` - 10 < " + score + " or `statistics_score`.`max_score` - 20 < " + score + ") and ("+score+" < `statistics_score`.`max_score`) and `subject` = "+subjectInt+";";
                        }else {
                            majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info` where `statistics_score`.`university_id` = '" + unId + "' and  (major_info.major_id = statistics_score.major_id) and `subject` = "+subjectInt+";";
                        }
                    }
                    List<Map<String, Object>> majorLists = jdbcTemplate.queryForList(majorsSql);
                    if (majorLists.size() == 0) {
                        continue;
                    }
                    for (Map<String, Object> majorList : majorLists) {
                        String majorName = (String) majorList.get("major_name");
                        //根据用户输入的分数获取高校 - 专业 -年份信息（相差20分）
                        majors.add(majorName);
                    }
                    recommendList.put(uName, majors);
                }
            }
        }
        return recommendList;
    }

}
