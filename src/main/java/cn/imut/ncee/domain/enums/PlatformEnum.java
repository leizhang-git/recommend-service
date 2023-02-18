package cn.imut.ncee.domain.enums;

/**
 * 构建平台
 * @Auth zhanglei
 * @Date 2023/2/18 22:52
 */
public enum PlatformEnum {

    /**
     * JWT管理
     */
    JWT("JWT"),

    /**
     * 无权限管理
     */
    NORMAL("NORMAL");

    private final String value;

    PlatformEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
