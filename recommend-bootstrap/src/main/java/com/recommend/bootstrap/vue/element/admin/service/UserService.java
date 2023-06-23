package com.recommend.bootstrap.vue.element.admin.service;

import com.recommend.bootstrap.vue.element.admin.dao.UserMapper;
import com.recommend.bootstrap.vue.element.admin.entity.UserInfoDTO;
import com.recommend.consumer.exception.StrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 23:06
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void login(String username, String password) {
        boolean success = userMapper.login(username, password);
        if (!success) {
            throw new StrException("用户登录失败!");
        }
    }

    public UserInfoDTO findUserInfo(String username) {
        return userMapper.findUserInfo(username);
    }

    public void updateUserInfo(UserInfoDTO dto) {
        userMapper.updateUserInfo(dto);
    }
}
