package cn.imut.ncee.config.condition;

import cn.imut.ncee.domain.enums.PlatformEnum;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @Auth zhanglei
 * @Date 2023/2/25 23:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(SysContextCondition.class)
public @interface ConditionSysPlatformContext {

    /**
     *
     * @return 系统属性值
     */
    PlatformEnum[] value() default { PlatformEnum.NORMAL };
}
