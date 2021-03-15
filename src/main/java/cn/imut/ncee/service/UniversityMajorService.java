package cn.imut.ncee.service;

import cn.imut.ncee.entity.vo.UniversityMajorInfo;

import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/2/26 10:42
 */
public interface UniversityMajorService {

    boolean insertUniversityMajorInfo(UniversityMajorInfo universityMajorInfo);

    List<UniversityMajorInfo> selectUniversityMajorInfo();
}
