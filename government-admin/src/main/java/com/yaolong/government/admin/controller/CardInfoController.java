package com.yaolong.government.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaolong.government.admin.dao.CardInfoMapper;
import com.yaolong.government.admin.domain.CardInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yaoLong
 * @date 2019-12-04  10:02
 */
@RestController
@CrossOrigin
public class CardInfoController {

    private final Logger logger = LoggerFactory.getLogger(CardInfoController.class);

    @Autowired
    private CardInfoMapper cardInfoMapper;

    /**
     * 获取某个卡片的详情信息
     * @param infoId
     * @return
     */
    @ApiOperation(value = "获取某个卡片的详情信息")
    @GetMapping("/getCardInfoById/{infoId}")
    public Map<String, Object> getCardInfoById(@PathVariable("infoId") Integer infoId) {

        Map<String, Object> map = new HashMap<>();
        CardInfo DynamicDetailsInfo = cardInfoMapper.selectById(infoId);
        if (DynamicDetailsInfo != null) {
            map.put("code", 0);
            map.put("DynamicDetailsInfo", DynamicDetailsInfo);
        } else {
            map.put("code", 1);
            map.put("msg", "数据为空");
        }
        return map;
    }

    /**
     * 获取分页卡片信息
     * @param infoCardId
     * @param current
     * @param size
     * @return
     */
    @ApiOperation(value = "获取分页卡片信息")
    @GetMapping("/getCardInfoListById/{infoCardId}/{current}/{size}")
    public Map<String, Object> getCardInfoList(@PathVariable("infoCardId") Integer infoCardId ,@PathVariable("current")long current, @PathVariable("size")long size) {

        logger.info(infoCardId.toString()+"---------------------------------------------------------->");
        Map<String, Object> map = new HashMap<>();

        QueryWrapper<CardInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("info_card_id",infoCardId);

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


    @ApiOperation(value = "发布文章")
    @PostMapping("/saveCardInfo")
    public Map<String,Object> saveCardInfo(@RequestBody CardInfo cardInfo){
        Map<String, Object> map = new HashMap<>();
        int insert = cardInfoMapper.insert(cardInfo);
        if (insert!=0) {
            map.put("code", 200);
            map.put("msg", "发布成功");
        } else {
            map.put("code", 400);
        }
        return map;
    }
}
