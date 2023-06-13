package com.example.springboot_.controller;

import com.example.springboot_.Service.LoginService;
import com.example.springboot_.entity.User;
import com.example.springboot_.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<?> Login(@RequestBody User user){
        return loginService.Login(user);
    }

}
