package com.recommend.bootstrap.auth.config;

import com.recommend.bootstrap.auth.config.security.JwtAuthenticationEntryPoint;
import com.recommend.bootstrap.auth.config.security.jwt.*;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Desc extends WebSecurityConfigurerAdapter 已经被弃用，spring鼓励使用基于组件的安全配置
 * @Author zhanglei
 * @Date 2024/3/25 11:17
 */
@Configuration
public class RecommendSecurityConfig {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final UserDetailsService userDetailsService;

    private final CorsFilter corsFilter;
    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public RecommendSecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService, CorsFilter cors) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
        this.corsFilter = cors;
    }

    @PostConstruct
    public void init() {
        try {
            authenticationManagerBuilder
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                //异常处理
                .exceptionHandling()
                .and()
                //禁用springSecurity自带的跨域拦截
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                //security的session生成策略改为security不主动创建session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //如何进行控制
                .authorizeRequests()
                .antMatchers("/**").permitAll();

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.addFilterAfter(corsFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(bodyReqFilter(), CorsFilter.class);
        http.addFilterAfter(urlPatternFilter(), BodyReqFilter.class);
        http.addFilterAfter(jwtFilter(), UrlPatternFilter.class);
        http.addFilterAfter(invocationFilter(),JWTFilter.class);
        http.addFilterAfter(jwtFilter(),InvocationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }

    @Bean
    public InvocationFilter invocationFilter(){
        return new InvocationFilter();
    }

    @Bean
    public BodyReqFilter bodyReqFilter(){
        return new BodyReqFilter();
    }

    @Bean
    public UrlPatternFilter urlPatternFilter(){
        return new UrlPatternFilter();
    }

    @Bean
    public CORSFilter corsFilter(){
        return new CORSFilter();
    }
}
