package com.example.springboot_.config;

import com.example.springboot_.entity.User;
import com.example.springboot_.entity.UserDetailsImpl.LoginUser;
import com.example.springboot_.filter.JwtAutionticationTokeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAutionticationTokeFilter jwtAutionticationTokeFilter;

    //security存储在数据库中的密码不能为明文，要用PasswordEncoder来加密，被系统用BCryptPasswordEncoder来加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                //SecurityContext是表单验证成功后存储的用户信息，前后端分离不能用
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/login").anonymous()
                .antMatchers("/register").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //因为自定义了token认证，所以需要使用自定的身份认证过滤器，不用自带的UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAutionticationTokeFilter, UsernamePasswordAuthenticationFilter.class);

    }


    /*
    将认证成功的用户认证凭证存入配置类
     */
    private final List<User> userList = new ArrayList<>();
    public List<User> getUserList() {
        return userList;
    }

}
