package com.recommend.bootstrap.vue.element.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 23:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuAndRoute {

    List<Route> routeList;

    List<Menu> menuTree;

}
