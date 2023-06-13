package com.example.springboot_.Service;

import com.example.springboot_.config.SecurityConfig;
import com.example.springboot_.entity.User;
import com.example.springboot_.entity.UserDetailsImpl.LoginUser;
import com.example.springboot_.utils.JwtUtil;
import com.example.springboot_.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityConfig securityConfig;

    public Result<?> Login(User user) {

        //获取Authentication的authenticate进行认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //认证失败抛出异常
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //从返回的认证凭证中获取userid用于生成jwt
        User principal = (User) authenticate.getPrincipal();
        String userid = principal.getId().toString();
        String jwt = JwtUtil.createJWT(userid);


        //把认证信息存入配置类中
        securityConfig.getUserList().add(principal);

        //将jwt作为token返回给前端
        Map<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("user",principal);
        return Result.success(map);
    }

}
