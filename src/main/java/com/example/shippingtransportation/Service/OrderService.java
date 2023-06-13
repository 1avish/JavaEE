package com.example.shippingtransportation.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shippingtransportation.entity.Orders;
import com.example.shippingtransportation.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    public List<Orders> getOrders(int id){
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        return orderMapper.selectList(wrapper.eq(Orders::getUid, id));
    }
}
