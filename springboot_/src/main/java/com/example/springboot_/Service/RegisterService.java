package com.example.springboot_.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot_.entity.User;
import com.example.springboot_.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegisterService {
    @Autowired
    private UserMapper userMapper;

    public boolean register(User user){
        if(!Objects.isNull(userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()))))
            return false;
        else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encode = bCryptPasswordEncoder.encode(user.getPassword());
            User user1 = new User(user.getUsername(),encode,"未知");
            userMapper.insert(user1);
            return true;
        }

    }
}
