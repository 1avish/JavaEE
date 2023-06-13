package com.example.shippingtransportation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shippingtransportation.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
