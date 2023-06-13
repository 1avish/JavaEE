package com.example.provider.Controller;

import com.example.provider.Service.RegisterService;
import org.example.entity.User;
import com.example.provider.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
