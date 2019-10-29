package com.pay.dao.mapper;

import com.pay.dao.model.OrderTradebill;

public interface OrderTradebillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderTradebill record);

    int insertSelective(OrderTradebill record);

    OrderTradebill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderTradebill record);

    int updateByPrimaryKey(OrderTradebill record);
}