package cn.imut.ncee.service;

import cn.imut.ncee.entity.pojo.UniversityInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/2/23 9:04
 */
@Service
public interface UniversityService {

    /**
     * 添加高校信息
     * @param universityInfo 高校信息
     * @return 是否添加成功
     */
    boolean insertUniversity(UniversityInfo universityInfo);

    /**
     * 查询所有高校的信息
     *
     * @return 高校列表
     */
    List<UniversityInfo> selectAllUniversity(Integer pageNum, Integer pageSize);

    /**
     * 根据Id查询高校信息
     *
     * @param universityId 高校Id
     * @return 高校信息
     */
    UniversityInfo selectUniversityById(@Param("universityId") String universityId);

    /**
     * 修改高校信息
     *
     * @param universityInfo 高校信息
     * @return 是否成功修改
     */
    boolean updateUniversity(UniversityInfo universityInfo);

    /**
     * 根据高校Id删除高校
     *
     * @param universityId 高校Id
     * @return 是否成功删除高校
     */
    boolean deleteUniversityById(String universityId);
}
