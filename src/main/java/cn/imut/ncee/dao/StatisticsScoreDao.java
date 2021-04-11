package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 高校-专业-分数线
 * @Author zhanglei
 * @Date 2021/3/15 15:42
 */
@Repository
public interface StatisticsScoreDao {

    /**
     * 增加高校-专业信息
     * @param statisticsScoreInfo 高校、专业、年份、分数线
     * @return 是否添加成功
     */
    @Insert("insert into `statistics_score` values (#{universityId}, #{majorId}, #{years}, #{avgScore}, #{minScore}, #{maxScore})")
    boolean insertStatisticsScore(StatisticsScoreInfo statisticsScoreInfo);

    /**
     * 查询所有信息
     * @return 高校-专业
     */
    @Select("select * from `statistics_score`")
    List<StatisticsScoreInfo> selectStatisticsScore();

    /**
     * 根据高校Id查询该高校的所有专业
     * @param uId 高校Id
     * @return 所有专业信息
     */
    @Select("select distinct `major_id` from `statistics_score` where `university_id` = #{uId}")
    List<String> selectById(@Param("uId") String uId);

    /**
     * 根据高校Id，专业Id查询出近五年分数
     * @param uId 高校Id
     * @param mId 专业Id
     * @return 近五年分数线
     */
    @Select("select * from statistics_score where university_id = #{uId} and major_id = #{mId};")
    List<StatisticsScoreInfo> selectScore(@Param("uId") String uId, @Param("mId") String mId);

    /**
     * 查询指定某一年的分数
     * @param uId 高校Id
     * @param mId 专业Id
     * @param years 年份
     * @return 分数线
     */
    @Select("select * from statistics_score where university_id = #{uId} and major_id = #{mId} and years = #{years};")
    StatisticsScoreInfo selectOneScore(@Param("uId") String uId, @Param("mId") String mId, @Param("years") String years);
}
