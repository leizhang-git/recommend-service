package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author zhanglei
 * @Date 2021/3/15 15:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticsScoreTest {

    @Autowired
    private StatisticsScoreDao statisticsScoreDao;

    @Autowired
    private MajorDao majorDao;

    @Autowired
    private UniversityDao universityDao;

    @Test
    public void insertMajorInfo() {
        List<String> allUniversityId = universityDao.selectAllId();
        List<String> allMajorId = majorDao.selectAllId();
//        for (String universityId : allUniversityId) {
            for (String majorId : allMajorId) {
                double avgScore = Math.random() * (30) + 620;
                double diffValue = Math.random() * (15) + 4;
                double diffValue1 = Math.random() * (11) + 3;
                DecimalFormat decimalFormat = new DecimalFormat(".#");
                String st = decimalFormat.format(avgScore);
                String st1 = decimalFormat.format(diffValue);
                String st2 = decimalFormat.format(diffValue1);
                double score = Double.parseDouble(st);
                double v = Double.parseDouble(st1);
                double v1 = Double.parseDouble(st2);
                StatisticsScoreInfo statisticsScoreInfo = new StatisticsScoreInfo("0530b16f9d1311eba8f600e04c7c8cb3", majorId, 2017, score, score - v, score + v1);
                statisticsScoreDao.insertStatisticsScore(statisticsScoreInfo);
            }
//        }
    }
}