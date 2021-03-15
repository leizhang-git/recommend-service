package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 专业详细信息
 * @Author zhanglei
 * @Date 2021/3/15 15:42
 */
@Repository
public interface StatisticsScoreDao {

    @Insert("insert into `statistics_score` values (#{universityId}, #{majorId}, #{years}, #{avgScore}, #{minScore}, #{maxScore})")
    void insertMajorInfo(StatisticsScoreInfo statisticsScoreInfo);
}
