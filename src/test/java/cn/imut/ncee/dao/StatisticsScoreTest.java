package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.util.List;

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
        for (int i = 2016; i <= 2019; i++) {
            for (String majorId : allMajorId) {
                double avgScore = Math.random() * (50) + 670;       //（max - min） + min  范围 min ~ max
                double diffValue = Math.random() * (10) + 4;
                double diffValue1 = Math.random() * (10) + 3;
                DecimalFormat decimalFormat = new DecimalFormat(".#");
                String st = decimalFormat.format(avgScore);
                String st1 = decimalFormat.format(diffValue);
                String st2 = decimalFormat.format(diffValue1);
                double score = Double.parseDouble(st);
                double v = Double.parseDouble(st1);
                double v1 = Double.parseDouble(st2);
                StatisticsScoreInfo statisticsScoreInfo = new StatisticsScoreInfo("1ac93019a28d11eb814d00e04c7c8cb3", majorId, i, score, score - v, score + v1);
                statisticsScoreDao.insertStatisticsScore(statisticsScoreInfo);
            }
        }
//        }
    }

    @Test
    public void insert() {
        StatisticsScoreInfo statisticsScoreInfo = new StatisticsScoreInfo("111", "111", 123, 444, 444, 444);
        StatisticsScoreInfo statisticsScoreInf1 = new StatisticsScoreInfo("111", "111", 1231, 4441, 4441, 1444);

        statisticsScoreDao.insertStatisticsScore(statisticsScoreInfo);
        statisticsScoreDao.insertStatisticsScore(statisticsScoreInf1);
    }
}