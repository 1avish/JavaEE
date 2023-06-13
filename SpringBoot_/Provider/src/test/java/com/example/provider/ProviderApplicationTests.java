package com.example.provider;

import com.example.provider.mapper.DeptMapper;
import org.example.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProviderApplicationTests {

    @Autowired
    private DeptMapper deptMapper;

    @Test
    void contextLoads() {
        List<Dept> depts = deptMapper.selectList(null);
        System.out.println(depts);
    }

}
