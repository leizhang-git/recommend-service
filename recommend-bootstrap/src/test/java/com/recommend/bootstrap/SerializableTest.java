package com.recommend.bootstrap;

import com.recommend.bootstrap.config.LogProxy;
import com.recommend.bootstrap.domain.entity.Person;
import com.recommend.bootstrap.util.SerializationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/19 18:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class SerializableTest {

    @Test
    public void test() throws Exception {
        // proxy
        SerializationUtil serializationUtil = (SerializationUtil) LogProxy.getCglibProxy(SerializationUtil.class);

        // call
        Person person = new Person();
        serializationUtil.jdkSerialization(person);
    }
}
