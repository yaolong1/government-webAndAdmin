package com.yaolong.government.admin.controller;

import com.yaolong.government.admin.domain.MainCardVo;
import com.yaolong.government.admin.service.IMainCardService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yaoLong
 * @date 2019-12-03  14:35
 */
@RestController
@EnableSwagger2
@CrossOrigin
public class MainCardController {

    @Autowired
    private IMainCardService iMainCardService;

    @GetMapping("/get/ByCardId/{card_id}")
    @ApiOperation(value = "根据id获取分页卡片信息")
    public Map<String,Object> getMainCardByCardId(@PathVariable("card_id") String card_id){
        Map<String,Object> map = new HashMap<>();
        List<MainCardVo> mainCardByCardId = iMainCardService.getMainCardByCardId(card_id);
        if (!mainCardByCardId.isEmpty()){
            map.put("mainCard",mainCardByCardId);
            map.put("code",0);
        }else {
            map.put("code",1);
            map.put("msg","数据为空");
        }
        return map;
    }


}
