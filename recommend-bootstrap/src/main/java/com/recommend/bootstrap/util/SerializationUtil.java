package com.recommend.bootstrap.util;

import com.caucho.hessian.io.Hessian2Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.recommend.bootstrap.domain.entity.Person;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectOutput;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

/**
 * @Describe 序列化工具
 * @Author zhanglei
 * @Date 2023/6/19 17:53
 */
@RefreshScope
@Component
public class SerializationUtil {

    //保存地址
    private final static String path = "D:\\test\\";

    static FSTConfiguration conf = FSTConfiguration.createAndroidDefaultConfiguration();


    public void jdkSerialization(Object obj) throws Exception {
        if(obj instanceof Serializable) {
            FileOutputStream outputStream = new FileOutputStream(new File(path + "jdk-serialization.txt"));
            SerializationUtils.serialize((Serializable) obj, outputStream);
            outputStream.close();
        }
    }

    public void fstSerialization(Object obj) throws Exception {
        FSTObjectOutput out = conf.getObjectOutput(new FileOutputStream(new File(path + "fst-serialization.txt")));
        out.writeObject(obj);
        out.close();
    }

    public void kryoSerialization(Object obj, Kryo kryo) throws Exception {
        Output out = new Output(new FileOutputStream(path + "kryo-serialization.txt"));
        kryo.writeObject(out, obj);
        out.close();
    }

    public void protobufSerialization(com.recommend.bootstrap.domain.protos.Person person) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(path + "protobuf-serialization.txt");
        person.writeTo(outputStream);
        outputStream.close();
    }

    public void thriftSerialization(com.recommend.bootstrap.domain.thrift.Person person) throws Exception {
        FileOutputStream fos = new FileOutputStream(new File(path + "thrift-serialization.txt"));
        person.write(new TBinaryProtocol(new TIOStreamTransport(fos)));
        fos.close();
    }

    public void hessianSerialization(Person person) throws Exception {
        FileOutputStream fos = new FileOutputStream(new File(path + "hessian-serialization.txt"));
        Hessian2Output oo = new Hessian2Output(fos);
        oo.writeObject(person);
        fos.close();
    }
}
