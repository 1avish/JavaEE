package com.example.providertwo.Service;

import org.example.entity.Dept;

import java.util.List;
public interface DeptService {
    Dept get(Integer deptNo);
    List<Dept> selectAll();
}