package com.ebs.platform.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsUtils;


/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/4/26 15:55
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置 HTTP 验证规则
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.securityContext();
        // 关闭csrf验证
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/archiveborrow/borrowView").permitAll()
                .antMatchers("/engine/getEngineById").permitAll()
                .antMatchers("/engine/getSourceData").permitAll()
                .antMatchers("/context/login").permitAll()
                .antMatchers("/platform/firstInit").permitAll()
                .antMatchers("/sys/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/weixin/**").permitAll()
                .antMatchers("/wxpay/**").permitAll()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/images/**",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.addFilter(new JwtAuthenticationFilter(authenticationManager(),userService))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .exceptionHandling()
                .authenticationEntryPoint(new DefaultAuthenticationEntryPoint());
    }
}
