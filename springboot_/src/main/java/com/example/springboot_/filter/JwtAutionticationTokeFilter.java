package com.example.springboot_.filter;

import com.example.springboot_.config.SecurityConfig;
import com.example.springboot_.entity.User;
import com.example.springboot_.entity.UserDetailsImpl.LoginUser;
import com.example.springboot_.mapper.UserMapper;
import com.example.springboot_.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAutionticationTokeFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)||securityConfig.getUserList().isEmpty()){
            /*
              放行
              在后面把认证信息设置为已认证，就不会经过UsernamePasswordAuthenticationFilter
              这里没有设置，就会经过UsernamePasswordAuthenticationFilter，但是没有携带username和password，
              UsernamePasswordAuthenticationFilter检查不到就会抛出异常
             */

            filterChain.doFilter(request,response);
            return;
        }
        //userid
        String userid;
        /*
        解析token，调用parseJWT时会自动检查jwt是否过期，如果过期就会抛出异常，通过catch来捕获异常
         */
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();   //userid
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }


        /*
        将认证成功后的认证信息存在配置类中，这样就不用存入redis或从数据库中查找了，还保存了权限信息，下面是用数据库查找的例子
         */
        User user = securityConfig.getUserList().get(0);
        if(!user.getId().toString().equals(userid)){
            System.out.println("用户未登录");
            throw new RuntimeException("用户未登录");
        }

        //从数据库中获取用户信息
        /*
        User user = userMapper.selectById(subject);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户未登录");
        }
        LoginUser loginUser = new LoginUser(user);
         */




        /*
        因为过滤器都是在SecurityContextHolder获取凭证来进行过滤，所以要将凭证存入SecurityContextHolder
         */
        //TODO 获取权限信息
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        /*
        当请求到达UsernamePasswordAuthenticationFilter时，它
        会检查SecurityContextHolder中是否已经存在认证凭证（Authentication对象）。
        如果已经存在认证凭证，UsernamePasswordAuthenticationFilter将不会再执行认证过程，
        而是将认证凭证传递给下一个过滤器或处理器进行后续的请求处理。
        */
        filterChain.doFilter(request,response);
    }
}
