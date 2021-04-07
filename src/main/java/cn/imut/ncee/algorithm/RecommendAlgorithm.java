package cn.imut.ncee.algorithm;

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
    public Map<String,List<String>> majorRecommend(Map<String,String> information) {
        //高校：专业1，专业2，专业3...
        Map<String, List<String>> recommendList = new HashMap<>();
        String subject = information.get("subject");
        //用户输入指标
        int subjectInt = Integer.parseInt(subject);
        String address = information.get("address");
        String score = information.get("score");
        String majorCategory = information.get("majorCategory");

        //根据科目获取所有专业id
        String subjectSql = "select `major_id` from major_info where subject = "+subjectInt+" and major_category = '"+majorCategory+"';";

        //根据科目查询出的所有专业信息
        List<Map<String, Object>> idList = jdbcTemplate.queryForList(subjectSql);
        if(idList.size() != 0) {
            for (Map<String, Object> stringObjectMap : idList) {
                String majorId = (String) stringObjectMap.get("major_id");
                //查询出开设此专业的所有高校Id,同时排除分数不符合的高校
                String universitySql = "select distinct `university_id` from statistics_score where major_id = '" + majorId + "' and (min_score < " + score + " or avg_score - 10 < " + score + " or max_score - 20 < " + score + ");";
                List<Map<String, Object>> universityId = jdbcTemplate.queryForList(universitySql);
                if (universityId.size() == 0) {
                    continue;
                }
                for (Map<String, Object> objectMap : universityId) {
                    List<String> majors = new ArrayList<>();
                    String uId = (String) objectMap.get("university_id");
                    //与用户输入的高校地址进行对比，不符合则进行下一个
                    String addressSql = "select `university_name` from `university_info` where `university_address` = '" + address + "' and `university_id` = '" + uId + "';";
                    List<Map<String, Object>> finalResult = jdbcTemplate.queryForList(addressSql);
                    if (finalResult.size() == 0) {
                        continue;
                    }
                    //此高校满足用户输入地区指标
                    String uName = (String) finalResult.get(0).get("university_name");
                    //获取该高校的所有专业（以便和上述专业进行对比）
                    String majorsSql = "select distinct `major_info`.`major_name` from `statistics_score`,`major_info`,`university_info` where `major_category` =  '" + majorCategory + "' and  `university_info`.`university_name` = '" + uName + "' and  (major_info.major_id = statistics_score.major_id) and (`statistics_score`.`min_score` < " + score + " or `statistics_score`.`avg_score` - 10 < " + score + " or `statistics_score`.`max_score` - 20 < " + score + ");";
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
