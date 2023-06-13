package com.example.provider.Controller;

import com.example.provider.Service.OrderService;
import com.example.provider.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/order/{id}")
    public Result<?> find(@PathVariable int id){

        return orderService.GetOrders(id);        //将搜索到的数据全部返回给前端
    }
}
