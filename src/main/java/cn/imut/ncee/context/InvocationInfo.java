package cn.imut.ncee.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: zhang lei
 * @Date: 2022/4/14 17:16
 */
public class InvocationInfo {

    String userId;

    String userName;

    String locale;

    String token;

    String org;

    Map<String, String> params = new HashMap<>();

    Map<Object, Object> extendAttributes = new HashMap<>();

    public Iterator<Map.Entry<String, String>> getSummary() {
        Map<String, String> map = new HashMap<>(this.params);
        map.put("token", this.token);
        map.put("locale", this.locale);
        map.put("userId", this.userId);
        map.put("userName", this.userName);
        map.put("organization", this.org);
        return map.entrySet().iterator();
    }
}
