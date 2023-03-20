package cn.imut.ncee.config.condition;

import cn.imut.ncee.domain.enums.PlatformEnum;
import cn.imut.ncee.util.SettingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

/**
 * @Auth zhanglei
 * @Date 2023/2/26 0:00
 */
public class SysContextCondition implements Condition {

    private static Logger log = LoggerFactory.getLogger(SysContextCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionSysPlatformContext.class.getName());
        if(null != attributes) {
            PlatformEnum[] value = (PlatformEnum[]) attributes.get("value");
            HashSet<PlatformEnum> platformEnums = new HashSet<>(Arrays.asList(value));
            PlatformEnum plat = SettingUtil.getBuildPlatform();
            return platformEnums.contains(plat);
        }
        return false;
    }
}
