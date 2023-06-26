package com.recommend.consumer.enums;

/**
 * @Describe document类型枚举
 * @Author zhanglei
 * @Date 2023/6/26 10:23
 */
public enum DocumentClassEnum {

    /**
     * Java类
     */
    JAVA("JAVA"),

    /**
     * 系统类
     */
    OS("OS"),

    /**
     * 云原生
     */
    CLOUD("CLOUD"),

    /**
     * 配置文件
     */
    CONF("CONF"),

    /**
     * 工具
     */
    TOOL("TOOL"),

    /**
     * 工作类
     */
    WORK("WORK"),

    /**
     * 人文类
     */
    CULTURAL("CULTURAL"),

    /**
     * 小说类
     */
    FICTION("FICTION"),

    /**
     * 图片类
     */
    PICTURE("PICTURE");

    private final String value;

    DocumentClassEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
