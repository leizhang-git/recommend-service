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
        UniversityInfo universityInfo = new UniversityInfo("内蒙古化工职业学院", "AW","内蒙古化工职业学院是内蒙古自治区教育厅直属的一所化工和建材行业国办全日制高等职业院校，入选了国家骨干高等职业院校，自治区优质校。2003年5月，经内蒙古自治区人民政府批准、教育部备案，在原内蒙古石油化工学校和内蒙古建材工业学校的基础上组建。", "内蒙古自治区");
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
        boolean isSuccess = universityDao.updateUniversity("e5512d1f7e4611eb873252540099a41f", "3A");
        System.out.println(isSuccess);
    }

    @Test
    public void selectUniversityIdForName() {
        String id = universityDao.selectIdForName("南京大学");
        System.out.println(id);
    }

    @Test
    public void deleteUniversityById() {
        boolean isSuccess = universityDao.deleteUniversityById("e5512d1f7e4611eb873252540099a41f");
        System.out.println(isSuccess);
    }
}