package cn.imut.ncee.service;

import cn.imut.ncee.domain.entity.pojo.MajorInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author zhanglei
 * @Date 2021/2/23 9:03
 */
public interface MajorService {

    /**
     * 增加专业信息
     * @param majorInfo 专业信息
     * @return 是否增加成功
     */
    boolean insertMajorInfo(MajorInfo majorInfo);

    /**
     * 修改专业信息
     * @param majorInfo 专业信息
     * @return 是否成功修改
     */
    boolean updateMajorInfo(MajorInfo majorInfo, String majorId);

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
    Map<String,Object> selectAllMajorInfo(Integer pageNum, Integer pageSize);


    /**
     * 根据专业Id查询该专业信息
     * @param majorId      专业Id
     * @return 返回专业信息
     */
    MajorInfo selectByIdMajorInfo(String majorId);

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
    List<String> selectAllId(Integer pageNum, Integer pageSize);

    /**
     * 根据Id删除该专业
     * @param majorId 专业Id
     */
    boolean deleteById(String majorId);
}
