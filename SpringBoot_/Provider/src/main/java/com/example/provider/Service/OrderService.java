package com.example.provider.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.entity.Orders;
import com.example.provider.mapper.OrderMapper;
import com.example.provider.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public Result<?> GetOrders(int id){
        List<Orders> orders = orderMapper.selectList(Wrappers.<Orders>lambdaQuery()
                .eq(Orders::getUid,id));
        return Result.success(orders);
    }
}
