package cn.imut.ncee.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 上下文信息
 * @Author: zhang lei
 * @Date: 2022/4/14 17:08
 */
public class IContextInfoProxy {

    private static IContextInfoProxy cip = new IContextInfoProxy();

    private static final ThreadLocal<InvocationInfo> threadLocal = ThreadLocal.withInitial(InvocationInfo::new);


    public static IContextInfoProxy getInstance() {
        return cip;
    }

    public Object getContextAttribute(String key) {
        if(null == threadLocal) {
            return null;
        }
        return threadLocal.get().contextAttributes.get(key);
    }

    public void setContextAttribute(String key, Object value) {
        if (null == threadLocal.get()) {
            threadLocal.set(new InvocationInfo());
        }
        if (null == value) {
            threadLocal.get().contextAttributes.remove(key);
        } else {
            threadLocal.get().contextAttributes.put(key, value);
        }
    }

    public Map<String, Object> getMap() {
        Map<String, Object> resultMap = new ConcurrentHashMap<>();
        for (Map.Entry<Object, Object> entry : threadLocal.get().contextAttributes.entrySet()) {
            resultMap.put((String) entry.getKey(), entry.getValue());
        }
        return resultMap;
    }

    public static void reset() {
        if(null != threadLocal.get()) {
            threadLocal.remove();
        }
    }
}
