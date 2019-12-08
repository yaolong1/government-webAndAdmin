package com.yaolong.government.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaoLong
 * @date 2019-12-03  12:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainCardVo implements Serializable {

    @ApiModelProperty(value = "卡片id")
    private Integer infoCardId;
    @ApiModelProperty(value = "卡片标题类型")
    private String cardTitleType;

    @ApiModelProperty(value = "卡片内容id")
    private Integer infoId;

    @ApiModelProperty(value = "卡片内容标题")
    private String infoTitle;

    @ApiModelProperty(value = "卡片内容发布时间")
    private Date infoDate;

    @ApiModelProperty(value = "卡片内容")
    private String infoContent;
}
