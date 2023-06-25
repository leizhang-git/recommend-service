package com.recommend.bootstrap;

import com.recommend.consumer.dao.MajorDao;
import com.recommend.consumer.domain.pojo.MajorInfo;
import com.recommend.provider.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/25 16:41
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LearnTest {

    @Autowired
    private MajorDao majorDao;

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
