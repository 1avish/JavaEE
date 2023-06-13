package com.example.provider.Controller;

import com.example.provider.Service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 服务提供者的控制层
* author:c语言中文网 c.biancheng.net
*/
@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;
  
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") int id) {
        return deptService.get(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> list() {
        return deptService.selectAll();
    }
}