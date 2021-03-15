package cn.imut.ncee.dao;


import cn.imut.ncee.entity.pojo.MajorInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/1/29 19:41
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MajorDaoTest {

    @Autowired
    private MajorDao majorDao;

    @Test
    public void insertMajorInfo() {
        MajorInfo major = new MajorInfo("播音与主持艺术", "62", "戏剧与影视学类", "35");
        boolean isSuccess = majorDao.insertMajorInfo(major);
        System.out.println(isSuccess);
    }

    @Test
    public void updateMajorInfo() {
    }

    @Test
    public void selectAllMajorInfo() {
        List<MajorInfo> majorInfos = majorDao.selectAllMajorInfo();
        majorInfos.forEach(System.out::println);
    }

    @Test
    public void selectByIdMajorInfo() {

    }
}