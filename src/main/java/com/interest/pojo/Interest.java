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
@TableName("interest")
public class Interest {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String code;
    private String name;
    private String sex;
    private String birthday;
    private String phone;
    private String type;
    private String course;
    private String registerTime;
    private String remaining;
    private String score;

    public Interest(String code, String name, String sex, String birthday, String phone, String type, String course, String registerTime, String remaining, String score) {
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.type = type;
        this.course = course;
        this.registerTime = registerTime;
        this.remaining = remaining;
        this.score = score;
    }
}
