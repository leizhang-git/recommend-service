package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.MessageBoard;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * 留言板接口
 * @Author zhanglei
 * @Date 2021/4/10 17:16
 */
@Repository
public interface MessageBoardDao {

    @Insert("insert into `message_board` values (#{uId}, #{uName}, #{uTime}, #{uDate})")
    boolean addMessage(MessageBoard messageBoard);
}
