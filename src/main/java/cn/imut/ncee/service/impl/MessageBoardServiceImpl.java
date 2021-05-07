package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.MessageBoardDao;
import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/4/30 10:21
 */
@Service
public class MessageBoardServiceImpl implements MessageBoardService {

    @Autowired
    private MessageBoardDao messageBoardDao;


    @Override
    public boolean addMessage(MessageBoard messageBoard) {
        return messageBoardDao.addMessage(messageBoard);
    }

    @Override
    public List<MessageBoard> queryAll(String uName) {
        if(uName == null || uName.length() == 0) {
            List<MessageBoard> messageBoards = messageBoardDao.queryAll();
            for (MessageBoard messageBoard : messageBoards) {
                String uTime = messageBoard.getUTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowTime = sdf.format(new Date(Long.parseLong(uTime)));
                messageBoard.setUTime(nowTime);
            }
            return messageBoards;
        }else {
            List<MessageBoard> messageBoards = messageBoardDao.queryByName(uName);
            for (MessageBoard messageBoard : messageBoards) {
                String uTime = messageBoard.getUTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowTime = sdf.format(new Date(Long.parseLong(uTime)));
                messageBoard.setUTime(nowTime);
            }
            return messageBoards;
        }
    }

    @Override
    public boolean deleteById(String uId) {
        return messageBoardDao.deleteById(uId);
    }
}
