package cn.imut.ncee.util;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther zhanglei
 * @create 2023-04-30-14:43
 */
public class CookieUtil {

    public static Map<String, String> getCookieMap(String cookieKey, HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        if(StrUtil.isBlank(cookieKey)) {
            return result;
        }
        if(null == request) {
            return result;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieKey.equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();
                    result.put(cookieKey,cookieValue);
                    break;
                }
            }
        }
        return result;
    }
}
