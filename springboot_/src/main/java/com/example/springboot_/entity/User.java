package com.example.springboot_.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.springboot_.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User implements UserDetails{

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String sex;



    public User(String username,String password,String sex){
        this.username=username;
        this.password=password;
        this.sex=sex;
    }

    @TableField(exist = false)
    private List<String> permissions;  //权限信息

    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<SimpleGrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (authorities!=null){
            return authorities;
        }

        //把permission中的权限信息封装成SimpleGrantedAuthority对象

        //方法一
//        authorities = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            newList.add(authority);
//
//        }

        //方法二。。。。函数式编程
        authorities =
                permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
