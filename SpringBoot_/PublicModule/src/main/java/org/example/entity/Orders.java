package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("orders")
@Data
public class Orders {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String address;
    private String consigneeName;
    private String telephone;
    private Integer uid;
    private Integer worth;
}
