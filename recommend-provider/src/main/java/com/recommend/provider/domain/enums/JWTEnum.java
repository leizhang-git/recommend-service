package com.recommend.provider.domain.enums;

/**
 * @Auth zhanglei
 * @Date 2023/3/4 22:54
 */
public enum JWTEnum {

    AK("!77sdaFDAFSDHJKASD$$#@@!asdghfas2");

    private final String value;

    JWTEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
