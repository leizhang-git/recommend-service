package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.StatisticsScoreDao;
import cn.imut.ncee.entity.vo.EntryScore;
import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import cn.imut.ncee.service.StatisticsScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/4/10 20:30
 */
@Service
public class StatisticsScoreServiceImpl implements StatisticsScoreService {

    @Autowired
    private StatisticsScoreDao statisticsScoreDao;

    @Override
    public boolean insertStatisticsScore(StatisticsScoreInfo statisticsScoreInfo) {
        return statisticsScoreDao.insertStatisticsScore(statisticsScoreInfo);
    }

    @Override
    public List<StatisticsScoreInfo> selectStatisticsScore() {
        return statisticsScoreDao.selectStatisticsScore();
    }

    @Override
    public List<String> selectById(String uId) {
        return statisticsScoreDao.selectById(uId);
    }

    @Override
    public List<StatisticsScoreInfo> selectScore(String uId, String mId) {
        return statisticsScoreDao.selectScore(uId, mId);
    }

    @Override
    public StatisticsScoreInfo selectOneScore(String uId, String mId, String years) {
        return statisticsScoreDao.selectOneScore(uId, mId, years);
    }

    @Override
    public List<EntryScore> selectAllScore(String uid) {
        return statisticsScoreDao.selectAllScore(uid);
    }
}
