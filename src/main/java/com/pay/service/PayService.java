package com.pay.service;

import com.pay.common.ResultMsg;
import com.pay.dao.vo.OrderVo;
import com.pay.dao.vo.serviceVo.PayOrder;

public interface PayService {
    ResultMsg payOrder(PayOrder payOrder) throws Exception;
}
