package com.example.provider.Service;

import org.example.entity.User;
import com.example.provider.mapper.UserMapper;
import com.example.provider.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoService {

    @Autowired
    private UserMapper userMapper;

    public Result<?> getInfo(int id){
        User user = userMapper.selectById(id);
        return Result.success(user);
    }
}
