package com.recommend.bootstrap.vue.element.admin.dao;

import com.recommend.bootstrap.vue.element.admin.entity.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 23:07
 **/
@Mapper
public interface UserMapper {

    @Select("select count(*) from user where username=#{username} and password=#{password}")
    boolean login(@Param("username") String username, @Param("password") String password);

    @Select("select * from user_info where username=#{username}")
    UserInfoDTO findUserInfo(String username);

    @Update("update user_info set name=#{name}, sex=#{sex} where username=#{username}")
    void updateUserInfo(UserInfoDTO dto);
}
