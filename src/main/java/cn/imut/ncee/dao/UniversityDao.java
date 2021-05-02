package cn.imut.ncee.dao;

import cn.imut.ncee.entity.pojo.UniversityInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 高校
 * @Author zhanglei
 * @Date 2021/1/27 17:44
 */
@Repository
public interface UniversityDao {

    /**
     * 添加高校信息
     * @param universityInfo 高校信息
     * @return 是否添加成功
     */
    boolean insertUniversity(UniversityInfo universityInfo);

    /**
     * 查询所有高校的信息
     * @return 高校列表
     */
    List<UniversityInfo> selectAllUniversity();

    /**
     * 根据Id查询高校信息
     * @param universityId 高校Id
     * @return 高校信息
     */
    UniversityInfo selectUniversityById(String universityId);

    /**
     * 根据名称查询高校信息
     * @param universityName 高校名称
     * @return 高校信息
     */
    List<UniversityInfo> selectUniversityByName(String universityName);

    /**
     * 根据高校Id修改高校编号
     * @param universityId 高校Id
     * @param universityCode 高校编号
     * @return 是否修改成功
     */
    boolean updateUniversity(@Param("universityId") String universityId, @Param("universityCode") String universityCode);

    /**
     * 根据高校Id修改高校编号
     * @param universityInfo 高校信息
     * @return 是否修改成功
     */
    boolean updateById(@Param("universityInfo") UniversityInfo universityInfo);

    /**
     * 根据高校Id删除高校
     * @param universityId 高校Id
     * @return 是否成功删除高校
     */
    boolean deleteUniversityById(@Param("universityId") String universityId);

    /**
     * 查询该省份所有的高校,分页展示
     * @param universityAddress 高校所在省份
     * @return 所有高校信息
     */
    List<UniversityInfo> selectByAddress(String universityAddress);


    /**
     * 查询所有高校Id
     * @return 高校Id
     */
    List<String> selectAllId();

    /**
     * 根据高校名称查询其Id
     * @param name 高校名称
     * @return 高校Id
     */
    String selectIdForName(String name);
}
