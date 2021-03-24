package cn.imut.ncee.controller;

import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.entity.pojo.UniversityInfo;
import cn.imut.ncee.service.PersonService;
import cn.imut.ncee.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户(管理员与普通用户)
 * @Author zhanglei
 * @Date 2021/1/29 18:46
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * 登陆
     * @param id 账号（邮箱）
     * @param password 密码
     * @return 是否登陆成功
     */
    @GetMapping("/login")
    public Results<Boolean> login(String id, String password) {
        boolean isSuccess = personService.login(id, password);
        return Results.dataOf(isSuccess);
    }

    /**
     * 注册
     * @param person id、姓名、密码
     * @return 是否成功注册
     */
    @PostMapping("/register")
    public Results<Boolean> register(@RequestBody Person person) {
        boolean isSuccess = personService.register(person);
        return Results.dataOf(isSuccess);
    }

    /**
     * 查询所有用户信息
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     * @return 用户信息
     */
    @GetMapping("/queryPerson")
    public ResponseEntity<?> queryAll(@RequestParam(defaultValue = "1",required = false) int pageNum,
                                      @RequestParam(defaultValue = "3",required = false) int pageSize) {
        List<Person> person = personService.selectAllPerson(pageNum, pageSize);
        return ResponseEntity.ok(person);
    }
}
