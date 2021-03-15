package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.MajorDao;
import cn.imut.ncee.entity.pojo.MajorInfo;
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
        return false;
    }

    @Override
    public boolean updateMajorInfo(MajorInfo majorInfo) {
        return false;
    }

    @Override
    public Map<String,Object> selectAllMajorInfo(String universityId, Integer pageNum, Integer pageSize) {
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
    public MajorInfo selectByIdMajorInfo(String universityId, String majorId) {
        return null;
    }
}
