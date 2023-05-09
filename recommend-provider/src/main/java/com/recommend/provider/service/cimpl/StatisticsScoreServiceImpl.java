package com.recommend.provider.service.cimpl;

import com.recommend.consumer.dao.MajorDao;
import com.recommend.consumer.dao.StatisticsScoreDao;
import com.recommend.consumer.domain.pojo.MajorInfo;
import com.recommend.consumer.domain.pojo.StatisticsScoreInfo;
import com.recommend.consumer.domain.vo.EntryScore;
import com.recommend.consumer.domain.vo.MajorScore;
import com.recommend.consumer.service.StatisticsScoreService;
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
        //即输入专业名称同时输入年份
        if(years == null && majorName != null && majorName.length() != 0) {
            String majorId = majorDao.selectByName(majorName);
            return statisticsScoreDao.selectByMajor1(majorId);
        }
        assert majorName != null;
        if(majorName.length() == 0) {
            List<MajorScore> list = new ArrayList<>();
            return list;
        }
        String majorId = majorDao.selectByName(majorName);
        if (years.length() != 0) {
            return statisticsScoreDao.selectByMajor(majorId, years);
        } else {
            if(majorId != null) {
                return statisticsScoreDao.selectByMajor1(majorId);
            }else {
                return null;
            }
        }
    }

    public List<StatisticsScoreInfo> selectAll(String universityId, String majorId) {
        return statisticsScoreDao.selectAll(universityId, majorId);
    }
}
