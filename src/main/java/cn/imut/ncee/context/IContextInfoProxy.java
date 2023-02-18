package cn.imut.ncee.context;

import cn.hutool.core.util.StrUtil;
import cn.imut.ncee.util.JSONUtil;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下文信息
 * @Author: zhang lei
 * @Date: 2022/4/14 17:08
 */
public class IContextInfoProxy {

    private static IContextInfoProxy cip = new IContextInfoProxy();

    private static final ThreadLocal<InvocationInfo> threadLocal = ThreadLocal.withInitial(InvocationInfo::new);

    public static String getParameter(String parameter) {
        return threadLocal.get().params.get(parameter);
    }

    public static void setParameter(String parameter, String value) {
        threadLocal.get().params.put(parameter, value);
    }

    public static IContextInfoProxy getInstance() {
        return cip;
    }

    public Map<String, Object> getCIs() {
        String cisJSON = IContextInfoProxy.getParameter("cis");
        Map<String, Object> cisMap = new HashMap<>();
        if(StrUtil.isNotEmpty(cisJSON)) {
            cisMap = JSONUtil.gsonToMaps(cisJSON);
        }
        return cisMap;
    }

    public void setCIs(Map<String, Object> cis) {
        if(CollectionUtils.isEmpty(cis)){
            cis = new HashMap<>();
        }
        String cisStr = JSONUtil.gsonString(cis);
        IContextInfoProxy.setParameter("ctxs",cisStr);
    }

    public void setCI(String key, String value) {
        Map<String, Object> ciMap = getCIs();
        ciMap.put(key,value);
        setCIs(ciMap);
    }

    public String getCI(String key) {
        Map<String, Object> ciMap = getCIs();
        String value = ciMap.get(key).toString();
        if(StrUtil.isEmpty(value)) {
            value = "";
        }
        return value;
    }
}
