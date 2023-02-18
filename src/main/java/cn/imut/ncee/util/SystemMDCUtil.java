package cn.imut.ncee.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * 日志追踪工具类
 * @Auth zhanglei
 * @Date 2023/2/18 21:39
 */
public class SystemMDCUtil {

    public SystemMDCUtil() {
    }

    public static String getTraceId() {
        return MDC.get("traceId");
    }

    public static String getProfile() {
        return MDC.get("profile");
    }

}
