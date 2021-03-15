package cn.imut.ncee.service;

import cn.imut.ncee.entity.pojo.MajorInfo;

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
    boolean updateMajorInfo(MajorInfo majorInfo);

    /**
     * 查询所有专业
     * @return 返回所有专业
     */
    Map<String,Object> selectAllMajorInfo(String universityId, Integer pageNum, Integer pageSize);

    /**
     * 根据高校Id、专业Id查询该专业信息
     * @param universityId 高校Id
     * @param majorId      专业Id
     * @return 返回专业信息
     */
    MajorInfo selectByIdMajorInfo(String universityId, String majorId);
}
