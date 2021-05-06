package cn.imut.ncee.dao;

import cn.imut.ncee.entity.pojo.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 * @Author zhanglei
 * @Date 2021/1/27 17:43
 */
@Repository
public interface PersonDao {

    /**
     * 用户注册
     *
     * @param person 用户信息
     * @return 是否成功注册
     */
    boolean register(Person person);

    /**
     * 用户登陆（查询出密码）
     * @param personId       用户Id
     * @return 是否成功登陆
     */
    String login(String personId);

    /**
     * 修改用户信息
     * @param id 用户Id
     * @param person 用户信息
     * @return 是否成功修改
     */
    boolean updatePerson(@Param("id") String id, @Param("person") Person person);

    /**
     * 用户修改分数
     * @param score 新的分数
     * @return 是否修改成功
     */
    boolean updateScore(@Param("score") double score, @Param("id") String id);


    /**
     * 查询所有用户信息
     *
     * @return 所有用户信息
     */
    List<Person> selectAllPerson();

    /**
     * 根据用户Id查询用户信息
     *
     * @param personId 用户Id
     * @return 用户信息
     */
    List<Person> selectByIdPerson(String personId);

    /**
     * 根据Id删除用户
     * @param id 用户Id
     * @return 是否删除成功
     */
    boolean deletePerson(@Param("id") String id);

    /**
     * 查询Id是否存在
     * @param id 用户账号
     * @return 该账号是否存在
     */
    String selectId(@Param("id") String id);
}
