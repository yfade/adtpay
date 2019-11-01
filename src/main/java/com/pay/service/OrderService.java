package com.pay.service;

import com.pay.dao.model.Order;
import com.pay.dao.model.OrderSub;
import com.pay.dao.vo.OrderVo;

import java.text.ParseException;
import java.util.Map;

public interface OrderService {
    int createOrder(Order order, OrderSub orderSub);

    OrderVo selectOrderDetail(Long id);

    Order selectOrderByOrderNo(String outTradeNo);

    int updateSuccessByWxNotify(Long id, Map<String,String> map) throws ParseException;

    int updateFailByWxNotify(Long id, Map<String, String> map);

    int updateSuccessByAliNotify(Long id, Map<String,String> map) throws ParseException;

    int updateFailByAliNotify(Long id, Map<String, String> map);
}
