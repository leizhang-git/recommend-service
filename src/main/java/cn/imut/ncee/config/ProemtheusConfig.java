package cn.imut.ncee.config;


import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 普罗米修斯监控配置
 * @Author zhanglei
 * @Date 2021/2/24 14:33
 */
@Component
public class ProemtheusConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer() {
        return (registry) -> registry.
                config().
                commonTags("application", applicationName);
    }
}
