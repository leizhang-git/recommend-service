package cn.imut.ncee.dao;

import cn.imut.ncee.domain.entity.vo.MessageBoard;
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

    @Select("select `u_id`,`u_name`,`u_time`,`u_date` from `message_board` where `u_time` between #{beginTime} and #{outTime}")
    List<MessageBoard> queryAll2(@Param("beginTime") long beginTime, @Param("outTime") long outTime);

    /**
     * 根据名称查询
     * @param uName 姓名
     * @return
     */
    @Select("select `u_id`,`u_name`,`u_time`,`u_date` from `message_board` where `u_name` = #{uName}")
    List<MessageBoard> queryByName(@Param("uName") String uName);

    @Select("select `u_id`,`u_name`,`u_time`,`u_date` from `message_board` where `u_name` = #{uName} and `u_time` between #{beginTime} and #{outTime}")
    List<MessageBoard> queryByName1(@Param("uName") String uName, @Param("beginTime") long beginTime, @Param("outTime") long outTime);
    /**
     * 删除该留言信息
     * @param uId 用户Id
     * @return 是否成功删除
     */
    @Delete("delete from `message_board` where `u_id` = #{uId} and `u_time` = #{uTime}")
    boolean deleteById(@Param("uId") String uId, @Param("uTime") String uTime);

    /**
     * 根据用户Id查询用户时间戳
     * @param uId 用户Id
     * @return 时间戳
     */
    @Select("select `u_time` from `message_board` where `u_id` = #{uId}")
    List<String> selectById(@Param("uId") String uId);
}
