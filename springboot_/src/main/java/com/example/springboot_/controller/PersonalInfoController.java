package com.example.springboot_.controller;

import com.example.springboot_.Service.PersonalInfoService;
import com.example.springboot_.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    @GetMapping("/getInfo")
    public Result<?> info(){
        return personalInfoService.getInfo();
    }
}
