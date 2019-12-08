package com.yaolong.government.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yaolong.government.admin.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yaoLong
 * @date 2019-11-22  21:51
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
