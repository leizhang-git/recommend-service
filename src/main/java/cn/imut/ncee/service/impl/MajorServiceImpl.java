package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.MajorDao;
import cn.imut.ncee.domain.entity.pojo.MajorInfo;
import cn.imut.ncee.service.MajorService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhanglei
 * @Date 2021/2/23 9:04
 */
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorDao majorDao;

    @Override
    public boolean insertMajorInfo(MajorInfo majorInfo) {
        return majorDao.insertMajorInfo(majorInfo);
    }

    @Override
    public boolean updateMajorInfo(MajorInfo majorInfo, String majorId) {
        return majorDao.updateMajorInfo(majorInfo, majorId);
    }

    @Override
    public String selectIdByCode(String majorCode) {
        return majorDao.selectIdByCode(majorCode);
    }

    @Override
    public Map<String,Object> selectAllMajorInfo(Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        //分页
        PageHelper.startPage(pageNum,pageSize);
        List<MajorInfo> majorInfos = majorDao.selectAllMajorInfo();
        for (int i = 0; i < majorInfos.size(); i++) {
            map.put(majorInfos.get(i).getMajorId(), majorInfos);
        }
        return map;
    }

    @Override
    public MajorInfo selectByIdMajorInfo(String majorId) {
        return majorDao.selectByIdMajorInfo(majorId);
    }

    @Override
    public Integer selectSubject(String majorId) {
        return majorDao.selectSubject(majorId);
    }

    @Override
    public List<String> selectAllId(Integer pageNum, Integer pageSize) {
        //分页
        PageHelper.startPage(pageNum,pageSize);
        List<String> ids = majorDao.selectAllId();
        return ids;
    }

    @Override
    public boolean deleteById(String majorId) {
        return majorDao.deleteById(majorId);
    }

}
