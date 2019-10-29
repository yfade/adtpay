package com.pay.service;

import com.pay.dao.model.Order;
import com.pay.dao.model.OrderSub;
import com.pay.dao.vo.OrderVo;

public interface OrderService {
    int createOrder(Order order, OrderSub orderSub);

    OrderVo selectOrderDetail(Long id);
}
