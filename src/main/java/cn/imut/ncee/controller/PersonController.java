package cn.imut.ncee.controller;

import cn.imut.ncee.service.PersonService;
import cn.imut.ncee.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/login")
    public Results<Boolean> login(String id, String password) {
        boolean isSuccess = personService.login(id, password);
        return Results.dataOf(isSuccess);
    }
}
