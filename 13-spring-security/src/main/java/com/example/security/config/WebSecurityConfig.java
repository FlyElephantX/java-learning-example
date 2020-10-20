package com.example.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置拦截请求资源
     * @param http：HTTP请求安全处理
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()  //HTTP Basic认证方式
//                .and()
//                .authorizeRequests()  // 授权配置
//                .anyRequest()  // 所有请求
//                .authenticated(); // 都需要认证
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                // 表单认证
//                .formLogin()
//                // 登录页
//                .loginPage("/login")
//                // 登录表单提交地址
//                .loginProcessingUrl("/auth/login")
//                .and()
//                // 身份认证请求
//                .authorizeRequests()
//                // URL路径匹配
//                .antMatchers("/login").permitAll()
//                // 任意请求
//                .anyRequest()
//                // 身份认证
//                .authenticated();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/auth/login")
//                // 登陆成功处理器
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        System.out.printf("登录成功处理...");
//                        response.setContentType("application/json;charset=utf-8");
//                        PrintWriter writer = response.getWriter();
//                        ObjectMapper om = new ObjectMapper();
//                        String successMsg = om.writeValueAsString(om.writeValueAsString(authentication));
//                        writer.write(successMsg);
//                        writer.flush();
//                        writer.close();
//                    }
//                })
//                // 登陆失败处理器
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
//                        System.out.printf("登录失败处理...");
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter writer = resp.getWriter();
//                        writer.write(new ObjectMapper().writeValueAsString(e));
//                        writer.flush();
//                        writer.close();
//                    }
//                })
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest()
//                .authenticated();
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth/login")
                // 登陆成功处理器
                .successHandler(successHandler)
                // 登陆失败处理器
                .failureHandler(failureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated();

    }

}
