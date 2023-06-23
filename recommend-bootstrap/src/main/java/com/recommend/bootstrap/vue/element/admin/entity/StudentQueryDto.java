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
public class StudentQueryDto {

    String name;

    String sex;

    Integer[] age;

    int page;

    int size;

    public int offset() {
        return (page - 1) * size;
    }

    public int limit() {
        return size;
    }
}
