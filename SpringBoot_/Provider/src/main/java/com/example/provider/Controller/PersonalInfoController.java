package com.example.provider.Controller;

import com.example.provider.Service.PersonalInfoService;
import com.example.provider.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService personalInfoService;

    @GetMapping("/getInfo/{id}")
    public Result<?> info(@PathVariable("id") int id){
        return personalInfoService.getInfo(id);
    }
}
