package com.example.springboot_.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot_.Service.OrderService;
import com.example.springboot_.utils.Result;
import com.example.springboot_.entity.Orders;
import com.example.springboot_.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/order/{id}")
    public Result<?> find(@PathVariable int id){

        return orderService.GetOrders(id);        //将搜索到的数据全部返回给前端
    }
}
