package com.recommend.consumer.dao;

import com.recommend.consumer.domain.pojo.StatisticsScoreInfo;
import com.recommend.consumer.domain.vo.EntryScore;
import com.recommend.consumer.domain.vo.MajorScore;
import org.apache.ibatis.annotations.*;
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
    @Insert("insert into `statistics_score` values (#{universityId}, #{majorId}, #{years}, #{avgScore}, #{minScore}, #{maxScore}, #{ranking})")
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

    /**
     * 根据高校Id查询详细信息（vo展示专用）
     * @param uid 高校Id
     * @return 详细信息
     */
    @Select("select distinct `statistics_score`.`university_id`,`statistics_score`.`major_id`,`university_info`.`university_name`,`major_info`.`major_name`,`major_info`.`major_category`,`statistics_score`.`years`,`statistics_score`.`min_score`,`statistics_score`.`avg_score`,`statistics_score`.`max_score` from `university_info`,`major_info`,`statistics_score` where `university_info`.`university_id` = #{uid} and `major_info`.`major_id` = `statistics_score`.`major_id` and `university_info`.`university_id` = `statistics_score`.`university_id`;")
    List<EntryScore> selectAllScore(@Param("uid") String uid);

    /**
     * 根据高校Id以及具体专业查询详细信息（vo展示专用）
     * @param uid 高校Id
     * @param majorName 专业名称
     * @return 详细信息
     */
    @Select("select distinct `statistics_score`.`university_id`,`statistics_score`.`major_id`,`university_info`.`university_name`,`major_info`.`major_name`,`major_info`.`major_category`,`statistics_score`.`years`,`statistics_score`.`min_score`,`statistics_score`.`avg_score`,`statistics_score`.`max_score` from `university_info`,`major_info`,`statistics_score` where `university_info`.`university_id` = #{uid} and `major_info`.`major_name` like concat('%',#{majorName},'%') and `major_info`.`major_id` = `statistics_score`.`major_id` and `university_info`.`university_id` = `statistics_score`.`university_id`;")
    List<EntryScore> selectAllScoreByMajor(@Param("uid") String uid, @Param("majorName") String majorName);

    /**
     * 根据高校Id，专业Id删除高校-专业
     * @param uid 高校Id
     * @param mId 专业Id
     * @return 是否成功删除
     */
    @Delete("delete from `statistics_score` where `university_id` = #{uid} and `major_id` = #{mId}")
    boolean deleteByUidAndMid(@Param("uid") String uid, @Param("mId") String mId);

    /**
     * 根据高校Id，专业Id修改分数线
     * @param entryScore vo展示分数线
     * @return 是否修改成功
     */
    @Update("update `statistics_score` set `years` = #{entryScore.years}, `avg_score` = #{entryScore.avgScore}, `min_score` = #{entryScore.minScore}, `max_score` = #{entryScore.maxScore} where `university_id` = #{entryScore.universityId} and `major_id` = #{entryScore.majorId}")
    boolean updateByUMId(@Param("entryScore") EntryScore entryScore);

    /**
     * 添加专业信息（高校Id传入）
     * @param entryScore vo展示分数线
     * @return 是否成功添加
     */
    @Insert("insert into `statistics_score` values (#{entryScore.universityId}, #{entryScore.majorId}, #{entryScore.years}, #{entryScore.avgScore}, #{entryScore.minScore}, #{entryScore.maxScore}, #{entryScore.ranking})")
    boolean addByUMId(@Param("entryScore") EntryScore entryScore);

    @Select("select\n" +
            "        university_info.university_name, major_info.major_name, statistics_score.years, statistics_score.avg_score, statistics_score.min_score, statistics_score.max_score\n" +
            "from\n" +
            "        statistics_score, university_info, major_info\n" +
            "where\n" +
            "        statistics_score.major_id = #{majorId}\n" +
            "and\n" +
            "        statistics_score.years = #{years}\n" +
            "and\n" +
            "        statistics_score.university_id = university_info.university_id\n" +
            "and\n" +
            "        statistics_score.major_id = major_info.major_id;")
    List<MajorScore> selectByMajor(@Param("majorId") String majorId, @Param("years") String years);

    @Select("select\n" +
            "        university_info.university_name, major_info.major_name, statistics_score.years, statistics_score.avg_score, statistics_score.min_score, statistics_score.max_score\n" +
            "from\n" +
            "        statistics_score, university_info, major_info\n" +
            "where\n" +
            "        statistics_score.major_id = #{majorId}\n" +
            "and\n" +
            "        statistics_score.university_id = university_info.university_id\n" +
            "and\n" +
            "        statistics_score.major_id = major_info.major_id;")
    List<MajorScore> selectByMajor1(@Param("majorId") String majorId);

    @Select("select * from `statistics_score` where `university_id` = #{universityId} and `major_id` = #{majorId} order by `statistics_score`.`years`")
    List<StatisticsScoreInfo> selectAll(@Param("universityId") String universityId, @Param("majorId") String majorId);
}
