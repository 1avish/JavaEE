package com.example.provider.Service;

import com.example.provider.mapper.DeptMapper;
import org.example.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public Dept get(int id) {
        return deptMapper.selectById(id);
    }

    public List<Dept> selectAll(){
        return deptMapper.selectList(null);
    }
}