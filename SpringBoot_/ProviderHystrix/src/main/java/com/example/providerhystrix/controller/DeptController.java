package com.example.providerhystrix.controller;

import com.example.providerhystrix.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/hystrix/ok/{id}")
    public String deptInfo_Ok(@PathVariable("id") Integer id) {
        String result = deptService.deptInfo_Ok(id);
        log.info("端口号：" + serverPort + " result:" + result);
        return result + "，   端口号：" + serverPort;
    }

    // Hystrix 服务超时降级
    @GetMapping("/hystrix/timeout/{id}")
    public String deptInfo_Timeout(@PathVariable("id") Integer id) {
        String result = deptService.deptInfo_Timeout(id);
        log.info("端口号：" + serverPort + " result:" + result);
        return result + "，   端口号：" + serverPort;
    }
}