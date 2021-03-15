package cn.imut.ncee.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author zhanglei
 * @Date 2021/2/23 18:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void get() {
        String value = redisUtils.get("hello");
        System.out.println(value);
    }

    @Test
    public void set() {
        boolean isSuccess = redisUtils.set("testKey", "testValue");
        System.out.println(isSuccess);
    }

    @Test
    public void getAndset() {
        boolean isSuccess = redisUtils.getAndset("hello", "helloTest");
        System.out.println(isSuccess);
    }

    @Test
    public void delete() {
        boolean isSuccess = redisUtils.delete("testKey");
        System.out.println(isSuccess);
    }
}