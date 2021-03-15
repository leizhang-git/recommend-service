package cn.imut.ncee.dao;

import cn.imut.ncee.entity.pojo.UniversityInfo;
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
     * 根据高校Id修改高校编号
     * @param universityId 高校Id
     * @param universityCode 高校编号
     * @return 是否修改成功
     */
    boolean updateUniversity(String universityId, String universityCode);

    /**
     * 根据高校Id删除高校
     * @param universityId 高校Id
     * @return 是否成功删除高校
     */
    boolean deleteUniversityById(String universityId);

    /**
     * 查询该省份所有的高校,分页展示
     * @param universityAddress 高校所在省份
     * @return 所有高校信息
     */
    List<UniversityInfo> selectUniversityByAddress(String universityAddress);


    /**
     * 查询所有高校Id
     * @return 高校Id
     */
    List<String> selectAllId();
}
