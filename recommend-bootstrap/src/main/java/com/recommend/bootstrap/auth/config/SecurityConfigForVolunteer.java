package com.recommend.bootstrap.auth.config;


import com.recommend.bootstrap.config.condition.ConditionSysPlatformContext;
import com.recommend.bootstrap.domain.enums.PlatformEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Auth zhanglei
 * @Date 2023/3/6 22:00
 */
@Configuration
@EnableWebSecurity
@ConditionSysPlatformContext({PlatformEnum.JWT})
@EnableConfigurationProperties({MultipartProperties.class})
public class SecurityConfigForVolunteer extends WebSecurityConfigurerAdapter {

    private static Logger log = LoggerFactory.getLogger(SecurityConfigForVolunteer.class);

    private final MultipartProperties multipartProperties;

    public SecurityConfigForVolunteer(MultipartProperties multipartProperties) {
        this.multipartProperties = multipartProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
