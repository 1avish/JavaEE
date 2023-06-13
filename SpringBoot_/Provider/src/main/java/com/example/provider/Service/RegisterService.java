package com.example.provider.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.entity.User;
import com.example.provider.mapper.UserMapper;
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
            userMapper.insert(user);
            return true;
        }

    }
}
