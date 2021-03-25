package cn.imut.ncee.algorithm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
     * 意向表包括：文理（0理，1文）、地区、相差的分数、具体专业、专业类别(若输入专业，则默认忽略此指标)、
     * @param information 意向表 顺序为：subject、address、scoreInterval、majorName、majorCategory
     * @return 高校-专业
     */
    public Map<String,List<String>> majorRecommend(Map<String,String> information) {
        Map<String, List<String>> recommendList = new HashMap<>();
        String subject = information.get("subject");
        int subjectInt = Integer.parseInt(subject);
        String address = information.get("address");
        String scoreInterval = information.get("scoreInterval");
        String majorCategory = information.get("majorCategory");

        String sql = "select `major_id` from major_info where subject = "+subject+";";

        List<Map<String, Object>> idList = jdbcTemplate.queryForList(sql);
        System.out.println(idList);
        return recommendList;
    }

    @Test
    public void testAlgorithm() {
        Map<String, String> userIndex = new TreeMap<>();
        userIndex.put("subject","0");
        userIndex.put("address", "北京");
        userIndex.put("scoreInterval", "200");
        userIndex.put("majorCategory", "计算机类");
        majorRecommend(userIndex);
    }
}
