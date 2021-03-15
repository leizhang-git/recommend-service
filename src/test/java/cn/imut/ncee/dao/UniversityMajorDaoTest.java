package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.UniversityMajorInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author zhanglei
 * @Date 2021/2/26 10:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UniversityMajorDaoTest {

    @Autowired
    private UniversityMajorDao universityMajorDao;

    @Test
    public void insertUniversityMajorInfo() {
        UniversityMajorInfo universityMajorInfo = new UniversityMajorInfo("f43d0163757811ebb53a52540099a41f", "a8dcee7e757111ebb53a52540099a41f");
        boolean isSuccess = universityMajorDao.insertUniversityMajorInfo(universityMajorInfo);
        System.out.println(isSuccess);
    }

    @Test
    public void selectUniversityMajorInfo() {
        List<UniversityMajorInfo> universityMajorDaos = universityMajorDao.selectUniversityMajorInfo();
        universityMajorDaos.forEach(System.out::println);
    }
}