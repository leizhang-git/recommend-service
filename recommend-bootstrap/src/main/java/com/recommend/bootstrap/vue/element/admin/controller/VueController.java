package com.recommend.bootstrap.vue.element.admin.controller;

import com.recommend.bootstrap.vue.element.admin.entity.LoginDTO;
import com.recommend.bootstrap.vue.element.admin.entity.StudentQueryDto;
import com.recommend.bootstrap.vue.element.admin.entity.StudentSaveDto;
import com.recommend.bootstrap.vue.element.admin.entity.UserInfoDTO;
import com.recommend.bootstrap.vue.element.admin.service.MenuService;
import com.recommend.bootstrap.vue.element.admin.service.StudentService;
import com.recommend.bootstrap.vue.element.admin.service.UserService;
import com.recommend.consumer.service.JWTService;
import com.recommend.consumer.web.vm.ResultVO;
import com.recommend.provider.util.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 22:47
 **/
@RequestMapping("/vue")
@RestController
public class VueController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/user/login")
    public ResultVO<?> login(@RequestBody LoginDTO loginDTO) {
        userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        JWTService jwtService = SpringContextHolder.getBean(JWTService.class);
        String token = jwtService.createToken(loginDTO.getUsername(), "defaultOrg", "");
        return ResultVO.getSuccess(token);
    }

    @PostMapping("/loginSession")
    public ResultVO<?> loginSession(@RequestBody LoginDTO dto, HttpSession session) {
        userService.login(dto.getUsername(), dto.getPassword());
        session.setAttribute("user", dto.getUsername());
        return ResultVO.getSuccess("登录成功");
    }

    @GetMapping("/info/{username}")
    public ResultVO<?> findUserInfo(@PathVariable String username) {
        return ResultVO.getSuccess(userService.findUserInfo(username));
    }

    @PostMapping("/info")
    public ResultVO<?> updateUserInfo(@RequestBody UserInfoDTO dto) {
        userService.updateUserInfo(dto);
        return ResultVO.getSuccess("用户信息更新成功");
    }

    @GetMapping("/menu")
    public ResultVO<?> findAll() {
        return ResultVO.getSuccess(menuService.findAll());
    }

    @GetMapping("/menu/{username}")
    public ResultVO<?> findBy(@PathVariable String username) {
        return ResultVO.getSuccess(menuService.findByUser(username));
    }


    @GetMapping("/students/{id}")
    public ResultVO<?> one(@PathVariable int id) {
//        Thread.sleep(2000);
        return ResultVO.getSuccess(studentService.findById(id));
    }

    @GetMapping("/students")
    public ResultVO<?> all() throws InterruptedException {
        Thread.sleep(2000);
        return ResultVO.getSuccess(studentService.findAll());
    }

    @PostMapping("/students")
    public ResultVO<?> insert(@RequestBody StudentSaveDto dto) {
        studentService.insert(dto);
        return ResultVO.getSuccess("新增成功");
    }

    @PutMapping("/students/{id}")
    public ResultVO<?> update(@PathVariable int id, @RequestBody StudentSaveDto dto) {
        studentService.update(id, dto);
        return ResultVO.getSuccess("修改成功");
    }

    @DeleteMapping("/students/{id}")
    public ResultVO<?> delete(@PathVariable int id) {
        studentService.deleteById(id);
        return ResultVO.getSuccess("删除成功");
    }

    @DeleteMapping("/students")
    public ResultVO<?> delete(@RequestBody int[] ids) {
        studentService.deleteByIds(ids);
        return ResultVO.getSuccess("删除成功");
    }

    // /api/students/q?name=&age=&page=
    @GetMapping("/students/q")
    public ResultVO<?> q(StudentQueryDto queryDto) {
        Map<String, Object> result = new HashMap<>();
        result.put("list", studentService.findBy(queryDto));
        result.put("total", studentService.findCount(queryDto));
        return ResultVO.getSuccess(result);
    }
}
