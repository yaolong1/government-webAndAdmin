package com.yaolong.government.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yaolong.government.admin.domain.MainCard;
import com.yaolong.government.admin.domain.MainCardVo;

import java.util.List;

/**
 * @author yaoLong
 * @date 2019-12-03  14:28
 */
public interface IMainCardService extends IService<MainCard> {

    List<MainCardVo> getMainCardByCardId(String card_id);

    Page<MainCardVo> getArticleListPage(Page<MainCardVo> page);
}
