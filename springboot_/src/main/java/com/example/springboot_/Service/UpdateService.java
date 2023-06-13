package com.example.springboot_.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot_.entity.User;
import com.example.springboot_.mapper.UserMapper;
import com.example.springboot_.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {
    @Autowired
    private UserMapper userMapper;
    public Result<?> update(User user){
        /*
        将前端传过来的密码加密
         */
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userMapper.updateById(user);
        return Result.success();
    }
}
