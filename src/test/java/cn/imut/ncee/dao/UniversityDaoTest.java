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
        UniversityInfo universityInfo = new UniversityInfo("北京航空航天大学", "BC","北京航空航天大学（Beihang University）简称”北航“，是中华人民共和国工业和信息化部直属的全国重点大学，位列世界一流大学建设高校、211工程和985工程重点建设高校，入选珠峰计划、2011计划、111计划、卓越工程师教育培养计划、国家建设高水平大学公派研究生项目、中国政府奖学金来华留学生接收院校、国家级新工科研究与实践项目、国家级大学生创新创业训练计划、国家大学生创新性实验计划、全国深化创新创业教育改革示范高校、强基计划试点高校。", "北京市");
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