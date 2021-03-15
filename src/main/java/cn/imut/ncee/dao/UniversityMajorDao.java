package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.UniversityMajorInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 高校-专业
 * @Author zhanglei
 * @Date 2021/2/26 10:17
 */
@Repository
public interface UniversityMajorDao {

    @Insert("insert into university_major_info (`university_id`,`major_id`) values (#{universityId}, #{majorId})")
    boolean insertUniversityMajorInfo(UniversityMajorInfo universityMajorInfo);

    @Select("select * from university_major_info")
    List<UniversityMajorInfo> selectUniversityMajorInfo();
}
