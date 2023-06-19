package com.recommend.bootstrap.util;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${obj.serialization.path}")
    private String path;

    public void jdkSerialization(Object obj) throws Exception {
        if(obj instanceof Serializable) {
            SerializationUtils.serialize((Serializable) obj, new FileOutputStream(new File(path)));
        }
    }
}
