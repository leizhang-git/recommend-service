package com.recommend.bootstrap;

import com.esotericsoftware.kryo.Kryo;
import com.recommend.bootstrap.config.LogProxy;
import com.recommend.bootstrap.domain.entity.Person;
import com.recommend.bootstrap.util.SerializationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
@Rollback(value = false)
public class SerializableTest {

    private static final Logger log = LoggerFactory.getLogger(SerializableTest.class);

    @Test
    public void serializableTest() throws Exception {

        log.info(">>>>>>>>>>>>>>>>>开始测试序列化性能~");
        Person person = new Person();
        Kryo kryo = new Kryo();
        kryo.register(Person.class);
        com.recommend.bootstrap.domain.protos.Person protoBufPerson = com.recommend.bootstrap.domain.protos.Person.newBuilder().build();
        com.recommend.bootstrap.domain.thrift.Person thriftPerson = new com.recommend.bootstrap.domain.thrift.Person();

        // proxy
        SerializationUtil serializationUtil = (SerializationUtil) LogProxy.getCglibProxy(SerializationUtil.class);

        // call
        // JDK自带序列化
        serializationUtil.jdkSerialization(person);

        //fst框架
        serializationUtil.fstSerialization(person);

        //kryo框架
        serializationUtil.kryoSerialization(person, kryo);

        //protobuf框架
        serializationUtil.protobufSerialization(protoBufPerson);

        //thrift框架
        serializationUtil.thriftSerialization(thriftPerson);

        //hessian框架
        serializationUtil.hessianSerialization(person);
    }
}