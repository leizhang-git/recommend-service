package cn.imut.ncee.dao;

import cn.imut.ncee.entity.vo.MessageBoard;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 留言板接口
 * @Author zhanglei
 * @Date 2021/4/10 17:16
 */
@Repository
public interface MessageBoardDao {

    /**
     * 新增留言内容
     * @param messageBoard 留言板
     * @return 是否成功添加
     */
    @Insert("insert into `message_board` values (#{uId}, #{uName}, #{uTime}, #{uDate})")
    boolean addMessage(MessageBoard messageBoard);

    /**
     * 查询用户所有留言
     * @return 所有留言内容
     */
    @Select("select `u_id`,`u_name`,`u_time`,`u_date` from `message_board`")
    List<MessageBoard> queryAll();

    /**
     * 删除该留言信息
     * @param uId 用户Id
     * @return 是否成功删除
     */
    @Delete("delete from `message_board` where `u_id` = #{uId}")
    boolean deleteById(@Param("uId") String uId);
}
