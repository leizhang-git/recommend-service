package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.UniversityMajorDao;
import cn.imut.ncee.entity.vo.UniversityMajorInfo;
import cn.imut.ncee.service.UniversityMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/2/26 10:42
 */
@Service
public class UniversityMajorServiceImpl implements UniversityMajorService {

    @Autowired
    private UniversityMajorDao universityMajorDao;

    @Override
    public boolean insertUniversityMajorInfo(UniversityMajorInfo universityMajorInfo) {
        return universityMajorDao.insertUniversityMajorInfo(universityMajorInfo);
    }

    @Override
    public List<UniversityMajorInfo> selectUniversityMajorInfo() {
        return universityMajorDao.selectUniversityMajorInfo();
    }
}
