package com.yaolong.government.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yaolong.government.admin.domain.MainCard;
import com.yaolong.government.admin.domain.MainCardVo;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yaoLong
 * @date 2019-12-03  11:47
 */
@Mapper
public interface MainCardMapper extends BaseMapper<MainCard> {

    @Select("SELECT main_card.*,card_info.info_title,card_info.info_date,card_info.info_id FROM main_card,card_info WHERE card_id=info_card_id AND card_id=#{card_id}")
    List<MainCardVo> getMainCardByCardId(@Param("card_id") String card_id);

    @Select("SELECT main_card.*,card_info.info_title,card_info.info_content,card_info.info_date,card_info.info_id FROM main_card,card_info WHERE info_card_id=card_id ORDER BY card_info.info_id")
    List<MainCardVo> getArticleListPage(Page<MainCardVo> page);
}
