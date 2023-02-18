package cn.imut.ncee.eccrypt;

import cn.imut.ncee.entity.pojo.MajorInfo;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 22:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void test() {
        String encrypt = stringEncryptor.encrypt("1870535196");
    }
}
