package com.yaolong.government.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yaoLong
 * @date 2019-12-03  11:36
 */
@Data
@TableName("card_info")
@NoArgsConstructor
@AllArgsConstructor
public class CardInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "卡片id主键")
    private Integer infoId;
    @ApiModelProperty(value = "卡片内容标题")
    private String infoTitle;
    @ApiModelProperty(value = "卡片内容")
    private String infoContent;
    @ApiModelProperty(value = "卡片内容html")
    private String infoContentHtml;
    @ApiModelProperty(value = "卡片内容时间")
    private Date infoDate;
    @TableField("info_card_id")
    @ApiModelProperty(value = "卡片类型id 外键")
    private Integer infoCardId;

}
