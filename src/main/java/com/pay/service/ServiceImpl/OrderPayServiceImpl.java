package com.pay.service.ServiceImpl;

import com.pay.dao.mapper.OrderPayMapper;
import com.pay.dao.model.OrderPay;
import com.pay.service.OrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPayServiceImpl implements OrderPayService {
    @Autowired
    private OrderPayMapper orderPayMapper;

    @Override
    public int insertOrderPayService(OrderPay orderPay) {
        return orderPayMapper.insertSelective(orderPay);
    }
}
