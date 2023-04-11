package cn.imut.ncee.dao;

import cn.imut.ncee.domain.entity.vo.MessageBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhanglei
 * @Date 2021/4/30 10:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageBoardDaoTest {

    @Autowired
    private MessageBoardDao messageBoardDao;

    @Test
    public void addMessage() {
        long time = System.currentTimeMillis();
        String nowTime = String.valueOf(time);
        for (int i = 0; i < 3; i++) {
            MessageBoard messageBoard = new MessageBoard("zhanglei", "张磊", nowTime + (i * 90), "大家快来评价！！！！！！！");
            messageBoardDao.addMessage(messageBoard);
        }
    }
}