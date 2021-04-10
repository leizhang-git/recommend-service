package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.UniversityDao;
import cn.imut.ncee.entity.pojo.UniversityInfo;
import cn.imut.ncee.service.UniversityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/2/24 9:32
 */
@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityDao universityDao;

    @Override
    public boolean insertUniversity(UniversityInfo universityInfo) {
        return universityDao.insertUniversity(universityInfo);
    }

    @Override
    public List<UniversityInfo> selectAllUniversity(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        return universityDao.selectAllUniversity();
    }

    @Override
    public UniversityInfo selectUniversityById(String universityId) {
        UniversityInfo universityInfo = universityDao.selectUniversityById(universityId);
        return universityInfo;
    }

    @Override
    public boolean updateUniversity(String uId, String uCode) {
        return universityDao.updateUniversity(uId, uCode);
    }

    @Override
    public boolean deleteUniversityById(String universityId) {
        return universityDao.deleteUniversityById(universityId);
    }
}
