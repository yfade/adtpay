package com.pay.dao.mapper;

import com.pay.dao.model.Order;
import com.pay.dao.vo.OrderVo;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    OrderVo selectOrderDetail(Long id);
}