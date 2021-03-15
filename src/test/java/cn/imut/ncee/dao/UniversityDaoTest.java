package cn.imut.ncee.dao;

import cn.imut.ncee.entity.pojo.UniversityInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author zhanglei
 * @Date 2021/2/23 9:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UniversityDaoTest {

    @Autowired
    private UniversityDao universityDao;

    @Test
    public void insertUniversity() {
        UniversityInfo universityInfo = new UniversityInfo("南京大学", "3A");
        boolean isSuccess = universityDao.insertUniversity(universityInfo);
        System.out.println(isSuccess);
    }

    @Test
    public void selectAllUniversity() {
        List<UniversityInfo> universityInfos = universityDao.selectAllUniversity();
        universityInfos.forEach(System.out::println);
    }

    @Test
    public void selectUniversityById() {
        UniversityInfo universityInfo = universityDao.selectUniversityById("f43d0163757811ebb53a52540099a41f");
        System.out.println(universityInfo.getUniversityName() + "-----" + universityInfo.getUniversityCode());
    }

    @Test
    public void updateUniversity() {
    }

    @Test
    public void deleteUniversityById() {
    }
}