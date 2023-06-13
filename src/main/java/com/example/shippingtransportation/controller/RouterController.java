package com.example.shippingtransportation.controller;

import com.example.shippingtransportation.Service.OrderService;
import com.example.shippingtransportation.entity.Orders;
import com.example.shippingtransportation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RouterController {
    @Autowired
    private OrderService orderService;
    @RequestMapping({"/toLogin","/"})
    public String login(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            String id = user.getId().toString();
            String username = user.getUsername();
            String sex = user.getSex();
            // 可以根据需要获取其他用户信息
            model.addAttribute("id",id);
            model.addAttribute("username",username);
            model.addAttribute("sex",sex);
        } else {
            throw new RuntimeException("用户名或密码错误");
        }
        return "index";
    }
    @RequestMapping("/order/{id}")
    public String in(Model model,@PathVariable int id){
        List<Orders> orders = orderService.getOrders(id);
        model.addAttribute("orders",orders);
        return "order";
    }
}

