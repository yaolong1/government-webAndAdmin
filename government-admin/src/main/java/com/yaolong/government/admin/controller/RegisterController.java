package com.yaolong.government.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import com.yaolong.government.admin.dao.UserMapper;
import com.yaolong.government.admin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaoLong
 * @date 2019-11-22  21:52
 */
@RestController
public class RegisterController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public R register(@RequestBody User user) {

        String password = user.getPassword();
        String uername = user.getUserName();

        User user1 = new User();
        user1.setPassword(new BCryptPasswordEncoder().encode(password));
        user1.setUserName(uername);

        try {
            int insert = userMapper.insert(user1);
            if (insert != 0)
                return R.ok("注册成功");

        } catch (Exception e) {
            Throwable cause = e.getCause();
            e.printStackTrace();
            if (cause instanceof MySQLQueryInterruptedException){
                return R.failed("注册失败，用户名已存在");
            }

        }

        return  null;
    }

    @RequestMapping("/test")
    public R getMsg(){
        return R.ok("test");
    }
}