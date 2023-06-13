package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("users")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String sex;

}
