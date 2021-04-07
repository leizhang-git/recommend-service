package cn.imut.ncee.algorithm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * @Author zhanglei
 * @Date 2021/3/28 12:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RecommendAlgorithmTest {

    @Autowired
    private RecommendAlgorithm recommendAlgorithm;

    @Test
    public void testAlgorithm() {
        Map<String, String> userIndex = new TreeMap<>();
        userIndex.put("subject","0");
        userIndex.put("address", "北京市");
        userIndex.put("score", "333");
        userIndex.put("majorCategory", "计算机类");
        System.out.println(recommendAlgorithm.majorRecommend(userIndex));
    }
}