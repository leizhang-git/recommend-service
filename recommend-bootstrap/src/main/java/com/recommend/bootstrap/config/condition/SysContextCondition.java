package com.recommend.bootstrap.config.condition;

import com.recommend.bootstrap.domain.enums.PlatformEnum;
import com.recommend.bootstrap.util.SettingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

/**
 * @Auth zhanglei
 * @Date 2023/2/26 0:00
 */
public class SysContextCondition implements Condition {

    private static Logger log = LoggerFactory.getLogger(SysContextCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionSysPlatformContext.class.getName());
        return Optional.ofNullable(attributes)
                .map(attrs -> (PlatformEnum[]) attrs.get("value"))
                .map(Arrays::asList)
                .map(HashSet::new)
                .map(platformEnums -> platformEnums.contains(SettingUtil.getBuildPlatform()))
                .orElse(false);
    }
}
