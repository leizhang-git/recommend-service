package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.MessageBoardDao;
import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    public List<MessageBoard> queryAll1(String uName, long beginTime, long outTime) {
        List<MessageBoard> messageBoards = messageBoardDao.queryByName1(uName, beginTime, outTime);
        for (MessageBoard messageBoard : messageBoards) {
            String uTime = messageBoard.getUTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(new Date(Long.parseLong(uTime)));
            messageBoard.setUTime(nowTime);
        }
        return messageBoards;
    }

    @Override
    public List<MessageBoard> queryAll2(long beginTime, long outTime) {
        List<MessageBoard> messageBoards = messageBoardDao.queryAll2(beginTime, outTime);
        for (MessageBoard messageBoard : messageBoards) {
            String uTime = messageBoard.getUTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(new Date(Long.parseLong(uTime)));
            messageBoard.setUTime(nowTime);
        }
        return messageBoards;
    }

    @Override
    public boolean deleteById(String uId, String uTime) throws ParseException {
        //时间戳转换为时间
        //时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse(uTime);
        long ts = parse.getTime();
        String s = String.valueOf(ts);
        //传入的时间戳（秒）
        String mUTime = s.substring(0, s.length() - 4);
        List<String> times = messageBoardDao.selectById(uId);
        for (String time : times) {
            //因为时间转换时间戳最后三位涉及到毫秒级别，无法很好的解析，故截断后四位进行对比即可
            //数据库中的时间戳（秒）
            String mTime = time.substring(0, time.length() - 4);
            if(mTime.equals(mUTime)) {
                return messageBoardDao.deleteById(uId, time);
            }
        }
        return false;
    }
}
