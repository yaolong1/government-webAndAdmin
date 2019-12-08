package com.yaolong.government.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yaolong.government.admin.dao.MainCardMapper;
import com.yaolong.government.admin.domain.MainCard;
import com.yaolong.government.admin.domain.MainCardVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yaoLong
 * @date 2019-12-03  14:32
 */
@Service
public class MainCardServiceImpl extends ServiceImpl<MainCardMapper, MainCard> implements IMainCardService {

    @Override
    public List<MainCardVo> getMainCardByCardId(String card_id) {
        return this.baseMapper.getMainCardByCardId(card_id);
    }

    @Override
    public Page<MainCardVo> getArticleListPage(Page<MainCardVo> page) {
        return page.setRecords(this.baseMapper.getArticleListPage(page));
    }

}
