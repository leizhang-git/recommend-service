package com.recommend.bootstrap.vue.element.admin.entity;

import java.util.List;

/**
 * @description:
 * @author: zhanglei
 * @create: 2023-06-23 23:04
 **/
public class Menu {

    private int id;
    private String label;
    private int pid;
    private String icon;
    private String routePath;
    private String routeElement;

    private List<Menu> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKey() {
        return String.valueOf(id);
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public String getRouteElement() {
        return routeElement;
    }

    public void setRouteElement(String routeElement) {
        this.routeElement = routeElement;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
