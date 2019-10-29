package com.pay.service.ServiceImpl;

import com.pay.dao.mapper.GoodsMapper;
import com.pay.dao.model.Goods;
import com.pay.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods selectGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
}
