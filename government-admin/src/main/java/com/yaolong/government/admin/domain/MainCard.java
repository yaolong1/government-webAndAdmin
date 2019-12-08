package com.yaolong.government.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yaoLong
 * @date 2019-12-03  11:34
 */
@Data
@TableName("main_card")
@NoArgsConstructor
@AllArgsConstructor
public class MainCard implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "卡片id")
    private Integer cardId;
    @ApiModelProperty(value = "卡片标题类型")
    private String cardTitleType;

}
