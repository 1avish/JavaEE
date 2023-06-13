package com.example.shippingtransportation.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.shippingtransportation.Service.RegisterService;
import com.example.shippingtransportation.entity.User;
import com.example.shippingtransportation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @ResponseBody
    @RequestMapping("/register")
    public String login(@RequestParam("username") String username, @RequestParam("password")String password, Model model) {
        boolean register = registerService.register(username,password);
        if (!register) {
            return "<script> alert('用户名已存在');window.location.href='/toLogin'</script>";
        }else{
            return "<script> alert('注册成功');window.location.href='/toLogin'</script>";
        }

    }
}
