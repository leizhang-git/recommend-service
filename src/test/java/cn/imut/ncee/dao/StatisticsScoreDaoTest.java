package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.EntryScore;
import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void selectAllScore() {
        List<EntryScore> infos = statisticsScoreDao.selectAllScore("fe74bec3a28b11eb814d00e04c7c8cb3");
        System.out.println(infos.toString());
    }

    @Test
    public void selectAllScoreByMajor() {
        List<EntryScore> infos = statisticsScoreDao.selectAllScoreByMajor("111", "111");
        for (EntryScore info : infos) {
            System.out.println(info.toString());
        }
    }

    @Test
    public void selectByUMid() {
        List<StatisticsScoreInfo> statisticsScoreInfos = statisticsScoreDao.selectScore("111", "111");
        for (StatisticsScoreInfo info : statisticsScoreInfos) {
            System.out.println(info.toString());
        }
    }

    @Test
    public void deleteById() {
        boolean isSuccess = statisticsScoreDao.deleteByUidAndMid("111", "111");
        System.out.println(isSuccess);
    }
}