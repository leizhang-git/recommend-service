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
    public List<UniversityInfo> selectByAddress(String universityAddress, String universityName) {
        if(universityAddress == null && universityName != null) {
            return universityDao.selectUniversityByName(universityName);
        } else if(universityAddress != null && universityName == null) {
            return universityDao.selectByAddress(universityAddress);
        } else {
            assert universityAddress != null;
            if(universityAddress.length() != 0 && universityName.length() != 0) {
                return universityDao.selectUniversityByName(universityName);
            }else if(universityAddress.length() == 0) {     //同时为0已被前端筛选
                return universityDao.selectUniversityByName(universityName);
            }else {
                return universityDao.selectByAddress(universityAddress);
            }
        }
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
