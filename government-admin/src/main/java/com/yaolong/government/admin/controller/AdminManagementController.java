package com.yaolong.government.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaolong.government.admin.dao.CardInfoMapper;
import com.yaolong.government.admin.dao.MainCardMapper;
import com.yaolong.government.admin.domain.CardInfo;
import com.yaolong.government.admin.domain.MainCardVo;
import com.yaolong.government.admin.service.IMainCardService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yaoLong
 * @date 2019-12-04  21:05
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
@SuppressWarnings("All")
public class AdminManagementController {

    private final Logger logger = LoggerFactory.getLogger(AdminManagementController.class);

    @Autowired
    private CardInfoMapper cardInfoMapper;

    @Autowired
    private IMainCardService iMainCardService;

    /**
     * 文章列表分页查询
     */
    @GetMapping("/getArticleInfoListPage/{current}/{size}")
    @ApiOperation(value = "文章列表查询")
    public Map<String,Object> getArticleInfoListPage(@PathVariable("current")long current, @PathVariable("size")long size){
        Map<String,Object> map = new HashMap<>();

        QueryWrapper<CardInfo> wrapper = new QueryWrapper<>();
        Page<CardInfo> page = new Page<>(current,size);
        IPage<CardInfo> iPage = cardInfoMapper.selectPage(page, wrapper);

        if (iPage.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", iPage);
            map.put("total",iPage.getTotal());
        }
        return map;
    }




    @GetMapping("/getLikeListPage/{infoTitle}/{current}/{size}")
    @ApiOperation(value = "文章列表模糊查询")
    public Map<String,Object> getLikeListPage(@PathVariable("infoTitle") String infoTitle, @PathVariable("current")long current, @PathVariable("size")long size){

        Map<String,Object> map = new HashMap<>();

        QueryWrapper<CardInfo> wrapper = new QueryWrapper<>();
        wrapper.like("info_title",infoTitle);

        Page<CardInfo> page = new Page<>(current,size);
        IPage<CardInfo> iPage = cardInfoMapper.selectPage(page, wrapper);

        if (iPage.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", iPage);
            map.put("total",iPage.getTotal());
        }
        return map;
    }

    @GetMapping("/delArticleInfoById/{infoId}")
    @ApiOperation(value = "根据id删除文章")
    public Map<String,Object> delArticleInfoById(@PathVariable("infoId") String infoId){
        Map<String,Object> map = new HashMap<>();

        int i = cardInfoMapper.deleteById(infoId);
        if (i!=0){
            map.put("code",200);
            map.put("msg","删除成功");
        }else {
            map.put("code",400);
            map.put("msg","删除失败");
        }
        return map;
    }

    @RequestMapping("/delArticleInfoList")
    @ApiOperation(value = "批量删除文章")
    public Map<String,Object> delArticleInfoList(@RequestBody List<Integer> deleteIdList){
        Map<String,Object> map = new HashMap<>();

        int i = cardInfoMapper.deleteBatchIds(deleteIdList);
        if (i!=0){
            map.put("code",200);
            map.put("msg","删除成功");
        }else {
            map.put("code",400);
            map.put("msg","删除失败");
        }
        return map;
    }
    @ApiOperation("更新/修改、文章类容")
    @PutMapping("/updateArticleInfo")
    public Map<String,Object> updateArticleInfo(@RequestBody CardInfo cardInfo ){
        logger.info(cardInfo.toString()+"-------------------------------------->");
        Map<String, Object> map = new HashMap<>();
        int insert = cardInfoMapper.updateById(cardInfo);
        logger.info(String.valueOf(insert));
        if (insert!=0) {
            map.put("code", 200);
            map.put("msg", "修改成功");
        } else {
            map.put("code", 400);
            map.put("msg", "修改失败");
        }
        return map;
    }



    @GetMapping("/getArticleListPage/{current}/{size}")
    @ApiOperation(value = "根据id获取分页卡片信息")
    public Map<String,Object> getArticleListPage(@PathVariable("current")long current, @PathVariable("size")long size){
        Map<String,Object> map = new HashMap<>();
        Page<MainCardVo> page = iMainCardService.getArticleListPage(new Page<>(current, size));
        if (page.getRecords().size() != 0){
            map.put("mainCard",page);
            map.put("code",200);
            map.put("total",page.getTotal());
        }else {
            map.put("code",400);
            map.put("msg","数据为空");
        }
        return map;
    }


}
