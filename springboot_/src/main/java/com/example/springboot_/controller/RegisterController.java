package com.example.springboot_.controller;

import com.example.springboot_.Service.RegisterService;
import com.example.springboot_.entity.User;
import com.example.springboot_.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        if (!registerService.register(user))
            return Result.error("404","注册失败");

        else return Result.success();

    }
}
