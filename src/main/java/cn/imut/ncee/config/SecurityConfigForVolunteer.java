package cn.imut.ncee.config;

import cn.imut.ncee.security.jwt.JWTFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfigForVolunteer {

    private static Logger log = LoggerFactory.getLogger(SecurityConfigForVolunteer.class);

    @Bean
    public FilterRegistrationBean<?> registrationJWTFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean<>();
        registration.setFilter(jwtFilter());
        registration.setName("jwtFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }
}
