package com.recommend.bootstrap.vue.element.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 23:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    String path;

    String element;
}
