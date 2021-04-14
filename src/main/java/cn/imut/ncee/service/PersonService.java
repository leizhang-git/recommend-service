package cn.imut.ncee.service;

import cn.imut.ncee.entity.pojo.AlgorithmIndex;
import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.entity.vo.MessageBoard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author zhanglei
 * @Date 2021/2/23 9:03
 */
@Service
public interface PersonService {

    /**
     * 用户注册
     * @param person 用户信息
     * @return 是否成功注册
     */
    boolean register(Person person);

    /**
     * 用户登陆
     * @param personId       用户Id
     * @param personPassword 用户密码
     * @return 是否成功登陆
     */
    boolean login(String personId, String personPassword);

    /**
     * 修改用户信息
     * @param person 用户信息
     * @return 是否成功修改
     */
    boolean updatePerson(Person person, String id);

    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    List<Person> selectAllPerson(Integer pageNum, Integer pageSize);

    /**
     * 根据用户Id查询用户信息
     * @param personId 用户Id
     * @return 用户信息
     */
    Person selectByIdPerson(String personId);

    /**
     * 根据Id删除用户
     * @param id 用户Id
     * @return 是否删除成功
     */
    boolean deletePerson(@Param("id") String id);

    /**
     * 用户填报志愿
     * @param index 用户输入指标
     * @return 推荐的结果
     */
    Object voluntary(AlgorithmIndex index);

    /**
     * 用户留言
     * @param messageBoard 留言板
     */
    boolean addMessage(MessageBoard messageBoard);
}
