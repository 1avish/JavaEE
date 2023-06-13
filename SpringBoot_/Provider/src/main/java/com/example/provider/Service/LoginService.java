package com.example.provider.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.provider.mapper.UserMapper;
import com.example.provider.util.JwtUtil;
import com.example.provider.util.Result;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public Result<?> login( User user){
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword,user.getPassword()));
        if(res==null){
            return Result.error("-1","用户名或密码错误");
        }else{
            Map<String,String> info = new HashMap<>();
            info.put("username",user.getUsername());
            info.put("id",res.getId().toString());
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), user.getUsername(), null);
            info.put("token",token);
            return Result.success(info);
        }

    }
}
