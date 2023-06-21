package com.recommend.bootstrap;

import com.recommend.bootstrap.util.RandomUtil;
import com.recommend.consumer.dao.MajorDao;
import com.recommend.consumer.domain.pojo.MajorInfo;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 22:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Autowired
    private MajorDao majorDao;

    @Test
    public void test() {
        String encrypt = stringEncryptor.encrypt("");
    }

    @Test
    public void testInsert() {
        for (int i = 0; i < 1000; i++) {
            MajorInfo majorInfo = new MajorInfo();
            majorInfo.setMajorId(UUID.randomUUID().toString());
            majorInfo.setMajorName(RandomUtil.getMajor());
            majorInfo.setMajorCode("2B");
            majorInfo.setMajorCategory("none");
            majorInfo.setEnrollPerson(RandomUtil.getNum(25, 120));
            majorInfo.setUniversityId("xxx");
            majorInfo.setSubject(RandomUtil.getNum(0, 1));
            majorDao.insertMajorInfo(majorInfo);
        }
    }
}