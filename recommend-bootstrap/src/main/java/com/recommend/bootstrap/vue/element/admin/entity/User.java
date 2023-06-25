package com.recommend.bootstrap.vue.element.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/25 16:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private String avatar;

    private String introduction;

    private List<String> roles;
}
