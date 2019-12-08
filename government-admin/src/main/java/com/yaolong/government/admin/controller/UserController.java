package com.yaolong.government.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaolong.government.admin.dao.UserMapper;
import com.yaolong.government.admin.domain.CardInfo;
import com.yaolong.government.admin.domain.MainCardVo;
import com.yaolong.government.admin.domain.User;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@SuppressWarnings("All")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户列表分页查询
     */
    @GetMapping("/getArticleInfoListPage/{current}/{size}")
    @ApiOperation(value = "用户列表分页查询")
    public Map<String,Object> selectAllPage(@PathVariable("current")long current, @PathVariable("size")long size){

            Map<String,Object> map = new HashMap<>();

            QueryWrapper<User> wrapper = new QueryWrapper<>();
            Page<User> page = new Page<>(current,size);
            IPage<User> iPage = userMapper.selectPage(page, wrapper);

            if (iPage.getRecords().size() == 0) {
                map.put("code", 400);
            } else {
                map.put("code", 200);
                map.put("data", iPage);
                map.put("total",iPage.getTotal());
            }
            return map;

    }
    @GetMapping("/getLikeUserListPage/{username}/{current}/{size}")
    @ApiOperation(value = "用户列表模糊查询")
    public Map<String,Object> getLikeUserListPage(@PathVariable("username") String username, @PathVariable("current")long current, @PathVariable("size")long size){

        Map<String,Object> map = new HashMap<>();

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name",username);

        Page<User> page = new Page<>(current,size);
        IPage<User> iPage = userMapper.selectPage(page,wrapper);

        if (iPage.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", iPage);
            map.put("total",iPage.getTotal());
        }
        return map;
    }
    @GetMapping("/delUserInfoById/{id}")
    @ApiOperation(value = "根据id删除用户信息")
    public Map<String,Object> delUserInfoById(@PathVariable("id") String id){
        Map<String,Object> map = new HashMap<>();

        int i = userMapper.deleteById(id);
        if (i!=0){
            map.put("code",200);
            map.put("msg","删除成功");
        }else {
            map.put("code",400);
            map.put("msg","删除失败");
        }
        return map;
    }
    @PostMapping("/delUserInfoList")
    @ApiOperation(value = "批量删除用户信息")
    public Map<String,Object> delUserInfoList(@RequestBody List<Integer> deleteIdList){
        Map<String,Object> map = new HashMap<>();

        int i = userMapper.deleteBatchIds(deleteIdList);
        if (i!=0){
            map.put("code",200);
            map.put("msg","删除成功");
        }else {
            map.put("code",400);
            map.put("msg","删除失败");
        }
        return map;
    }
    @ApiOperation("更新/修改、用户类容")
    @PutMapping("/updateUserInfo")
    public Map<String,Object> updateUserInfo(@RequestBody User user ){


        Map<String, Object> map = new HashMap<>();
        String password = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        logger.info("加密密码为：---------------->"+user.getPassword());
        int insert = userMapper.updateById(user);

        if (insert!=0) {
            map.put("code", 200);
            map.put("msg", "修改成功");
        } else {
            map.put("code", 400);
            map.put("msg", "修改失败");
        }
        return map;
    }
    @ApiOperation("添加用户")
    @PutMapping("/addUser")
    public Map<String,Object> addUser(@RequestBody User user ){

        Map<String, Object> map = new HashMap<>();
        String password = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        logger.info("加密后的密码："+user.getPassword());
        try {
            int insert = userMapper.insert(user);
            if (insert!=0) {
                map.put("code", 200);
                map.put("msg", "修改成功");
            } else {
                map.put("code", 400);
                map.put("msg", "修改失败");
            }
        }catch (Exception e){
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException){
                map.put("code", 500);
                map.put("msg", "用户已存在请重新添加！");
                logger.info("注册失败：---------------->用户已存在");
            }
        }

        return map;
    }

    @PostMapping("/eqPassword")
    @ApiOperation(value = "根据id获取分页卡片信息")
    public Map<String,Object> getPassword(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        User user1 = userMapper.selectById(user.getId());

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        boolean matches = encode.matches(user.getPassword(), user1.getPassword());
        logger.info(String.valueOf(matches));
        if (user1!=null){
            map.put("code",200);
            map.put("boolean",matches);
        }else {
            map.put("code",400);
        }
        return map;
    }
}
