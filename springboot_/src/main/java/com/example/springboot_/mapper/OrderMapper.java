package com.example.springboot_.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}
