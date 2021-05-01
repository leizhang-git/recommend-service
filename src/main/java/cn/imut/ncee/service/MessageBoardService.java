package cn.imut.ncee.service;

import cn.imut.ncee.entity.vo.MessageBoard;

import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/4/30 10:20
 */
public interface MessageBoardService {

    boolean addMessage(MessageBoard messageBoard);

    List<MessageBoard> queryAll(Integer pageNum, Integer pageSize);

    boolean deleteById(String universityId);
}
