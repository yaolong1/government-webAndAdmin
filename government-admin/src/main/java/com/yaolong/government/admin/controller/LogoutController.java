package com.yaolong.government.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author yaoLong
 * @date 2019-12-07  14:29
 */
@RestController
public class LogoutController {

    private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/logouts")
    public Map<String,Object> logOuts() {
        Map<String,Object> map = new HashMap<>();
        Set<String> keys = stringRedisTemplate.keys("*");
        Iterator<String> it1 = keys.iterator();
        while (it1.hasNext()) {
            stringRedisTemplate.delete(it1.next());
        }
        map.put("code",200);
        map.put("msg","退出登录成功");

        return map;
    }
}