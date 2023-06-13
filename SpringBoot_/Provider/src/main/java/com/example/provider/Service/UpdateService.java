package com.example.provider.Service;

import org.example.entity.User;
import com.example.provider.mapper.UserMapper;
import com.example.provider.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {
    @Autowired
    private UserMapper userMapper;
    public Result<?> update(User user){
        userMapper.updateById(user);
        return Result.success();
    }
}
