package com.yaolong.government.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

/**
 * @author yaoLong
 * @date 2019-11-22  21:49
 */
@Data
@TableName("user")
public class User implements Serializable {


    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "用户id主键")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("yin7372175240000"));
    }
}
