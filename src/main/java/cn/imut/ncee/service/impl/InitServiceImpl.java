package cn.imut.ncee.service.impl;

import cn.imut.ncee.service.InitService;
import cn.imut.ncee.util.ConstantDefinitionUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther zhanglei
 * @create 2023-04-30-23:46
 */
@Service
public class InitServiceImpl implements InitService {
    @Override
    public Map<String, Object> getMarcoValues(HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        Map<String, String> allMacro = ConstantDefinitionUtil.getAllMacro();
        result.putAll(allMacro);
        return result;
    }
}
