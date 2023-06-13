package com.example.provider.Controller;

import com.example.provider.Service.UpdateService;
import org.example.entity.User;
import com.example.provider.util.Result;
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
