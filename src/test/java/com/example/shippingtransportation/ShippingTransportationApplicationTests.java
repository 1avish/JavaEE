package com.example.shippingtransportation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shippingtransportation.entity.Orders;
import com.example.shippingtransportation.entity.User;
import com.example.shippingtransportation.mapper.OrderMapper;
import com.example.shippingtransportation.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShippingTransportationApplicationTests {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Test
    void order() {
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        List<Orders> orders = orderMapper.selectList(wrapper.eq(Orders::getUid, 1));
        System.out.println(orders);
    }

    @Test
    void user(){
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
