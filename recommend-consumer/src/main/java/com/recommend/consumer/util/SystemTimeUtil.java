package com.recommend.consumer.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 21:36
 */
public class SystemTimeUtil {

    public static final DateTimeFormatter NOW_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SystemTimeUtil() {
    }

    public static String getCurrentTime() {
        return LocalDateTime.now().format(NOW_TIME);
    }

    public static Long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public static String getTime(Instant time) {
        return NOW_TIME.format(time);
    }
}
