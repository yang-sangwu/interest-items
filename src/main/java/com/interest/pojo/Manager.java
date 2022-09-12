package com.interest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author a1002
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("manager")
public class Manager {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String code;
    private String password;
}
