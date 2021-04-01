package cn.imut.ncee.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
        //高校：专业1，转义2，专业3...
        Map<String, List<String>> recommendList = new HashMap<>();
        String subject = information.get("subject");
        //用户输入指标
        int subjectInt = Integer.parseInt(subject);
        String address = information.get("address");
        String score = information.get("score");
        String majorCategory = information.get("majorCategory");

        //根据科目获取所有专业id
        String subjectSql = "select `major_id` from major_info where subject = "+subject+" and major_category = "+majorCategory+";";

        //根据科目查询出的所有专业信息
        List<Map<String, Object>> idList = jdbcTemplate.queryForList(subjectSql);
        for (int i = 0; i < idList.size(); i++) {
            String majorId = (String) idList.get(i).get("major_id");
            //查询出开设此专业的所有高校Id
            String universitySql = "select distinct `university_id` from statistics_score where major_id = "+majorId+";";

            //根据用户输入的分数获取高校 - 专业 -年份信息（相差20分）
            String scoreSql = "select `university_id`, `major_id`, `years` from statistics_score where min_score < "+score+" or avg_score - 10 < "+score+" or max_score - 20 < "+score+";";
            List<Map<String, Object>> universityId = jdbcTemplate.queryForList(universitySql);
            for (int j = 0; j < universityId.size(); j++) {
                String uId = (String) universityId.get(j).get("university_id");
                String addressSql = "select `university_id` from university_info where university_addreee = "+address+" and university_id = "+uId+";";
                List<Map<String, Object>> finalResult = jdbcTemplate.queryForList(addressSql);
                
            }
            System.out.println(universityId);
        }
        //
        return recommendList;
    }

}
