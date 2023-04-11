package cn.imut.ncee.service;

import cn.imut.ncee.domain.entity.vo.MessageBoard;

import java.text.ParseException;
import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/4/30 10:20
 */
public interface MessageBoardService {

    boolean addMessage(MessageBoard messageBoard);

    List<MessageBoard> queryAll(String uName);

    List<MessageBoard> queryAll1(String uName, long beginTime, long outTime);

    List<MessageBoard> queryAll2(long beginTime, long outTime);

    boolean deleteById(String uId, String uTime) throws ParseException;
}
