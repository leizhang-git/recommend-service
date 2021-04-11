package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author zhanglei
 * @Date 2021/4/11 11:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticsScoreDaoTest {

    @Autowired
    private StatisticsScoreDao statisticsScoreDao;

    @Test
    public void selectScore() {
        StatisticsScoreInfo statisticsScoreInfo = statisticsScoreDao.selectOneScore("98ada0447e4611eb873252540099a41f", "029392657e4511eb873252540099a41f", "2020");
        System.out.println(statisticsScoreInfo.toString());
    }
}