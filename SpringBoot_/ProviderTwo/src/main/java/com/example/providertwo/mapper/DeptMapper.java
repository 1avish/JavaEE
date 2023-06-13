package com.example.providertwo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    //根据主键获取数据
    Dept selectByPrimaryKey(Integer deptNo);
    //获取表中的全部数据
    List<Dept> GetAll();
}