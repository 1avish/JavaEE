package com.example.springboot_.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springboot_.entity.User;
import com.example.springboot_.entity.UserDetailsImpl.LoginUser;
import com.example.springboot_.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        User user = userMapper.selectOne(wrapper.eq(User::getUsername, username));
        if(user==null){
            throw new RuntimeException("用户名或密码错误");
        }

//        查询用户权限信息
        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        user.setPermissions(list);   //将权限信息存入user中

        return user;
    }
}
