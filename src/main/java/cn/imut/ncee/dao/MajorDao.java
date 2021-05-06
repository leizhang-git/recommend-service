package cn.imut.ncee.dao;

import cn.imut.ncee.entity.pojo.MajorInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 专业
 * @Author zhanglei
 * @Date 2021/1/27 17:45
 */
@Repository
@Mapper
public interface MajorDao {

    /**
     * 添加专业信息
     * @param majorInfo 专业信息
     * @return 是否成功添加
     */
    boolean insertMajorInfo(MajorInfo majorInfo);

    /**
     * 修改专业信息
     * @param majorInfo 专业信息
     * @param majorId 专业Id
     * @return 是否修改成功
     */
    boolean updateMajorInfo(@Param("majorInfo") MajorInfo majorInfo, @Param("majorId") String majorId);

    /**
     * 根据majorCode获取Id
     * @param majorCode 专业编码
     * @return 专业Id
     */
    String selectIdByCode(String majorCode);

    /**
     * 查询所有专业
     * @return 返回所有专业
     */
    List<MajorInfo> selectAllMajorInfo();

    /**
     * 根据专业Id查询该专业信息
     * @param majorId      专业Id
     * @return 返回专业信息
     */
    MajorInfo selectByIdMajorInfo(String majorId);

    /**
     * 根据专业名称查询其专业Id
     * @param majorName 专业名称
     * @return 返回专业Id
     */
    String selectByName(String majorName);

    /**
     * 根据专业Id，查询该专业属于文科还是理科
     * @param majorId 专业Id
     * @return 0理科/1文科/-1文/理
     */
    Integer selectSubject(String majorId);

    /**
     * 查询所有专业Id号
     * @return 专业Id
     */
    List<String> selectAllId();

    /**
     * 根据Id删除专业
     * @param majorId 专业Id
     * @return 是否成功删除
     */
    boolean deleteById(String majorId);
}
