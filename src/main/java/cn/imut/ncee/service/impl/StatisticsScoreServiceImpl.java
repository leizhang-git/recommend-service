package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.MajorDao;
import cn.imut.ncee.dao.StatisticsScoreDao;
import cn.imut.ncee.entity.pojo.MajorInfo;
import cn.imut.ncee.entity.vo.EntryScore;
import cn.imut.ncee.entity.vo.MajorScore;
import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import cn.imut.ncee.service.StatisticsScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/4/10 20:30
 */
@Service
public class StatisticsScoreServiceImpl implements StatisticsScoreService {

    @Autowired
    private StatisticsScoreDao statisticsScoreDao;

    @Autowired
    private MajorDao majorDao;

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

    @Override
    public List<EntryScore> selectAllScore(String uid, String majorName) {
        if(majorName == null) {
            return statisticsScoreDao.selectAllScore(uid);
        }
        return statisticsScoreDao.selectAllScoreByMajor(uid, majorName);
    }

    @Override
    public boolean deleteByUidAndMid(String uId, String mId) {
        return statisticsScoreDao.deleteByUidAndMid(uId, mId);
    }

    @Override
    public boolean insertAndUpdate(EntryScore entryScore) {

        //修改
        boolean isSuccess = false;
        if(entryScore.getMajorId() == null) {
            int code = 63;
            MajorInfo majorInfo = new MajorInfo(entryScore.getMajorName(), String.valueOf(code), entryScore.getMajorCategory(), 35);
            majorDao.insertMajorInfo(majorInfo);
            String majorId = majorDao.selectByName(entryScore.getMajorName());
            entryScore.setMajorId(majorId);
            //增加
            return statisticsScoreDao.addByUMId(entryScore);
        }
        if(entryScore.getMajorId() != null || entryScore.getMajorId().length() != 0) {
            isSuccess = statisticsScoreDao.updateByUMId(entryScore);
        }
        return isSuccess;
    }

    @Override
    public List<MajorScore> selectByMajor(String majorName, String years) {
        if(majorName.length() != 0 && years.length() != 0) {
            String majorId = majorDao.selectByName(majorName);
            return statisticsScoreDao.selectByMajor(majorId, years);
        }else if(years.length() == 0) {
            String majorId = majorDao.selectByName(majorName);
            return statisticsScoreDao.selectByMajor1(majorId);
        }else {
            List<MajorScore> majorScores = new ArrayList<>();
            return majorScores;
        }
    }
}
