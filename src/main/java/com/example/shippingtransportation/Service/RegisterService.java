package com.example.shippingtransportation.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.shippingtransportation.entity.User;
import com.example.shippingtransportation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegisterService {

    @Autowired
    private UserMapper userMapper;

    public boolean register(String username,String password){
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        if(!Objects.isNull(user)) return false;
        else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encode = bCryptPasswordEncoder.encode(password);
            User users = new User(username,encode,"未知");
            userMapper.insert(users);
            return true;
        }

    }
}
