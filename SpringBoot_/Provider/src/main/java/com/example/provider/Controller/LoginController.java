package com.example.provider.Controller;

import com.example.provider.Service.LoginService;
import com.example.provider.util.Result;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        return loginService.login(user);
    }
}
