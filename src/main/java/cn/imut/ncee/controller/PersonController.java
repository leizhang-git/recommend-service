package cn.imut.ncee.controller;

import cn.imut.ncee.dto.LoginDTO;
import cn.imut.ncee.entity.pojo.AlgorithmIndex;
import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.service.PersonService;
import cn.imut.ncee.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户(管理员与普通用户)
 * @Author zhanglei
 * @Date 2021/1/29 18:46
 */
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private static Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    /**
     * 登陆
     * @param loginDTO 账号（邮箱）,密码
     * @return 是否登陆成功
     */
    @PostMapping("/login")
    public ResultVO<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) {
        List<Person> person = personService.login(loginDTO.getAccount(), loginDTO.getPwd(), response);
        return ResultVO.getSuccess(person.get(0));
    }

    /**
     * 修改密码
     * @param loginInfo 账号（邮箱）,密码
     * @return 是否登陆成功
     */
    @PostMapping("/update")
    public ResultVO<?> updatePWD(@RequestBody Map<String,String> loginInfo) {
        boolean isSuccess = personService.update(loginInfo.get("oldP"), loginInfo.get("newP"), loginInfo.get("id"));
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 注册
     * @param person id、姓名、密码
     * @return 是否成功注册
     */
    @PostMapping("/register")
    public ResultVO<?> register(@RequestBody Person person) {
        boolean isSuccess = personService.register(person);
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 查询所有用户信息（管理员除外）
     * @param infos 信息
     * @return 用户信息
     */
    @PostMapping("/queryAllPerson")
    public ResultVO<?> queryAll(@RequestBody Map<String,String> infos) {
        List<Person> person = personService.selectAllPerson(infos.get("id"));
        return ResultVO.getSuccess(person);
    }

    /**
     * 根据用户Id修改用户信息
     * @param person 新的用户信息
     * @param id 用户Id
     * @return 是否修改成功
     */
    @PatchMapping("/updatePerson")
    public ResultVO<?> queryAll(@RequestBody Person person, @RequestParam("id") String id) {
        boolean isSuccess = personService.updatePerson(person, id);
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 根据用户Id查询用户信息
     * @param id 用户Id
     * @return 用户信息
     */
    @GetMapping("/queryPerson")
    public ResultVO<?> queryById(String id) {
        List<Person> person = personService.selectByIdPerson(id);
        return ResultVO.getSuccess(person);
    }

    /**
     * 管理员根据Id删除用户信息
     * @param id 用户Id
     * @return 是否成功删除
     */
    @GetMapping("/deletePerson")
    public ResultVO<?> deleteById(String id) {
        boolean isSuccess = personService.deletePerson(id);
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 用户志愿填报推荐
     * @param algorithmIndex 用户输入指标（文理（0理，1文）、地区、分数、具体专业、专业类别(若输入专业，则默认忽略此指标)）
     * @return 返回推荐的列表
     */
    @PostMapping("/voluntaryService")
    public ResultVO<?> voluntaryService(@RequestBody AlgorithmIndex algorithmIndex) {
        Object voluntary = personService.voluntary(algorithmIndex);
        return ResultVO.getSuccess(voluntary);
    }

    /**
     * 用户输入留言内容
     * @param messageBoard 留言板
     * @return 是否留言成功
     */
    @PostMapping("/message")
    public ResultVO<?> messageBoard(@RequestBody MessageBoard messageBoard) {
        boolean isSuccess = personService.addMessage(messageBoard);
        return ResultVO.getSuccess(isSuccess);
    }
}
