package com.recommend.bootstrap.vue.element.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 23:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSaveDto {

    String name;

    String sex;

    int age;
}
