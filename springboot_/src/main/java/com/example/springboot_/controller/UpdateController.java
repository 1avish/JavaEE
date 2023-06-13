package com.example.springboot_.controller;

import com.example.springboot_.Service.UpdateService;
import com.example.springboot_.entity.User;
import com.example.springboot_.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {
    @Autowired
    private UpdateService updateService;
    @PutMapping("/update")
    public Result<?> update(@RequestBody User user){

        return updateService.update(user);
    }
}
