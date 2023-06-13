package com.example.springboot_.Service;

import com.example.springboot_.entity.User;
import com.example.springboot_.entity.UserDetailsImpl.LoginUser;
import com.example.springboot_.mapper.UserMapper;
import com.example.springboot_.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PersonalInfoService {

    @Autowired
    private UserMapper userMapper;

    public Result<?> getInfo(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Result.success(principal);
    }
}
