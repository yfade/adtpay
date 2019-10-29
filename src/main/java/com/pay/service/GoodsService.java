package com.pay.service;

import com.pay.dao.model.Goods;

public interface GoodsService {
    Goods selectGoodsById(Long id);
}
