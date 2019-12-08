package com.yaolong.government.admin.config.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yaolong.government.admin.dao.UserMapper;
import com.yaolong.government.admin.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author yaoLong
 * @date 2019-11-23  14:01
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        User users = userMapper.selectOne(wrapper);

        logger.info(users.getPassword()+"----------------------------->密码");

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(users.getUserName(), users.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("test")));

        return user;
    }
}