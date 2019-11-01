package com.pay.dao.mapper;

import com.pay.dao.model.OrderPay;

public interface OrderPayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    OrderPay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);

    int updateByNotify(OrderPay orderPay);
}