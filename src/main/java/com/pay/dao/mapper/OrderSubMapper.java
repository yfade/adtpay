package com.pay.dao.mapper;

import com.pay.dao.model.OrderSub;
import com.pay.dao.vo.OrderVo;

public interface OrderSubMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderSub record);

    int insertSelective(OrderSub record);

    OrderSub selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderSub record);

    int updateByPrimaryKeyWithBLOBs(OrderSub record);

    int updateByPrimaryKey(OrderSub record);

}