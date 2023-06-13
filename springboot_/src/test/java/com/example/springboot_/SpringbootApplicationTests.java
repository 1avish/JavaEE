package com.example.springboot_;

import com.example.springboot_.entity.Orders;
import com.example.springboot_.entity.User;
import com.example.springboot_.mapper.OrderMapper;
import com.example.springboot_.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Test
    void contextLoads() {
        User user = userMapper.selectById(2);

        List<Orders> orders = orderMapper.selectList(null);
        System.out.println(user.getUsername());
    }

}
