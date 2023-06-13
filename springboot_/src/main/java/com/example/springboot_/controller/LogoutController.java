package com.example.springboot_.controller;

import com.example.springboot_.config.SecurityConfig;
import com.example.springboot_.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LogoutController {
    @Autowired
    private SecurityConfig securityConfig;

    @GetMapping("/Logout")
    public Result<?> logout(HttpServletRequest request){
        securityConfig.getUserList().remove(0);
        return Result.success();
    }
}
