package org.example.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor //无参构造函数
@Data // 提供类的get、set、equals、hashCode、canEqual、toString 方法
@TableName("dept")
public class Dept{
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("deptName")
    private String deptName;

    @TableField("dbSource")
    private String dbSource;
}