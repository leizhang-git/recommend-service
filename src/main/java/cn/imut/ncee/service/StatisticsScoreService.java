package cn.imut.ncee.service;

import cn.imut.ncee.entity.vo.EntryScore;
import cn.imut.ncee.entity.vo.MajorScore;
import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/4/10 20:26
 */
@Repository
public interface StatisticsScoreService {

    /**
     * 增加高校-专业信息
     * @param statisticsScoreInfo 高校、专业、年份、分数线
     * @return 是否添加成功
     */
    boolean insertStatisticsScore(StatisticsScoreInfo statisticsScoreInfo);

    /**
     * 查询所有信息
     * @return 高校-专业
     */
    List<StatisticsScoreInfo> selectStatisticsScore();

    /**
     * 根据高校Id查询该高校的所有专业
     * @param uId 高校Id
     * @return 所有专业信息
     */
    List<String> selectById(@Param("uId") String uId);

    /**
     * 根据高校Id，专业Id查询出近五年分数
     * @param uId 高校Id
     * @param mId 专业Id
     * @return 近五年分数线
     */
    List<StatisticsScoreInfo> selectScore(@Param("uId") String uId, @Param("mId") String mId);

    /**
     * 查询指定某一年的分数
     * @param uId 高校Id
     * @param mId 专业Id
     * @param years 年份
     * @return 分数线
     */
    StatisticsScoreInfo selectOneScore(@Param("uId") String uId, @Param("mId") String mId, @Param("years") String years);

    /**
     * 查询指定高校的录取分数线
     * @param uid 高校Id
     * @return 分数线
     */
    List<EntryScore> selectAllScore(@Param("uid") String uid);

    /**
     * 查询指定高校及其专业的录取分数线
     * @param uid 高校Id
     * @param majorName 专业名称
     * @return 分数线
     */
    List<EntryScore> selectAllScore(String uid, String majorName);

    /**
     * 根据高校Id，专业Id删除高校-专业
     * @param uId 高校Id
     * @param mId 专业Id
     * @return 是否成功删除
     */
    boolean deleteByUidAndMid(String uId, String mId);

    /**
     * 修改/增加 vo展示
     * @param entryScore vo展示内容
     * @return 是否成功添加/修改
     */
    boolean insertAndUpdate(EntryScore entryScore);

    /**
     * 通过专业，年份，查询历年录取信息
     * @param majorName 专业名称
     * @param years 年份
     * @return 录取信息
     */
    List<MajorScore> selectByMajor(String majorName, String years);

    /**
     * 通过高校Id，专业Id查询近五年的录取情况，以供可视化界面展示
     * @param universityId 高校Id
     * @param majorId 专业Id
     * @return 近五年录取情况
     */
    List<StatisticsScoreInfo> selectAll(String universityId, String majorId);
}
