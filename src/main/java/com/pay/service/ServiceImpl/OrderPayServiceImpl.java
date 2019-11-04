package com.pay.service.ServiceImpl;

import com.pay.dao.mapper.OrderPayMapper;
import com.pay.dao.mapper.ProcessMapper;
import com.pay.dao.model.OrderPay;
import com.pay.dao.model.Process;
import com.pay.dao.vo.serviceVo.AliPayOrder;
import com.pay.dao.vo.serviceVo.PayOrder;
import com.pay.dao.vo.serviceVo.WxPayOrder;
import com.pay.enums.PayConstant;
import com.pay.enums.TradeType;
import com.pay.service.OrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderPayServiceImpl implements OrderPayService {
    @Autowired
    private OrderPayMapper orderPayMapper;
    @Autowired
    private ProcessMapper processMapper;

    @Override
    @Transactional
    public long insertOrderPayService(PayOrder payOrder) {
        OrderPay orderPay = new OrderPay();
        orderPay.setOrderNo(payOrder.getOutTradeNo());
        orderPay.setUserId(payOrder.getBizModel().getUserId());
        orderPay.setTradeType(TradeType.getValue(payOrder.getTradeType()));
        orderPay.setStatus(0);
        orderPay.setClientIp(payOrder.getSpbillCreateIp());
        orderPay.setDevice(payOrder.getDeviceInfo());

        switch (payOrder.getTradeType()) {
            case PayConstant.WX_APP:
            case PayConstant.WX_NATIVE: {
                WxPayOrder wxPayOrder = (WxPayOrder) payOrder;
                orderPay.setTotalAmount(new BigDecimal(wxPayOrder.getTotalFee()).divide(new BigDecimal(100), 2));
                orderPay.setClientIp(wxPayOrder.getSpbillCreateIp());
                orderPay.setDevice(wxPayOrder.getDeviceInfo());
                orderPay.setTitle(wxPayOrder.getBody());
                orderPay.setCreateTime(new Date());
                break;
            }
            case PayConstant.ALI_APP:
            case PayConstant.ALI_PC: {
                AliPayOrder aliPayOrder = (AliPayOrder) payOrder;
                orderPay.setTotalAmount(aliPayOrder.getTotalAmount());
                orderPay.setTitle(aliPayOrder.getSubject());
                orderPay.setCreateTime(new Date());
                break;
            }
            default:
                throw new RuntimeException("支付类型异常");
        }
        orderPayMapper.insertSelective(orderPay);

        // 插入日志
        Process process = new Process();
        process.setOperatorUserId(payOrder.getBizModel().getUserId());
        process.setOperatorUserName(payOrder.getBizModel().getUserName());
        process.setOperateTime(orderPay.getCreateTime());
        process.setOrderNo(payOrder.getOutTradeNo());
        process.setDescription(payOrder.getTradeType() + "-预下单");
        processMapper.insertSelective(process);
        return orderPay.getId();
    }
}
