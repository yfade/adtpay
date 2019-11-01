package com.pay.service.ServiceImpl;

import com.alibaba.fastjson.JSONObject;
import com.pay.dao.mapper.OrderMapper;
import com.pay.dao.mapper.OrderPayMapper;
import com.pay.dao.mapper.OrderSubMapper;
import com.pay.dao.mapper.ProcessMapper;
import com.pay.dao.model.Order;
import com.pay.dao.model.OrderPay;
import com.pay.dao.model.OrderSub;
import com.pay.dao.model.Process;
import com.pay.dao.vo.OrderVo;
import com.pay.enums.StatusEnum;
import com.pay.service.OrderService;
import com.pay.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderSubMapper orderSubMapper;
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private OrderPayMapper orderPayMapper;

    @Override
    @Transactional
    public int createOrder(Order order, OrderSub orderSub) {
        orderMapper.insertSelective(order);
        orderSub.setOrderId(order.getId());
        orderSubMapper.insertSelective(orderSub);

        //日志
        Process process = new Process();
        process.setOperatorUserId(order.getCreateUserId());
        process.setOperatorUserName(order.getCreateUserName());
        process.setOrderId(order.getId());
        process.setOperateTime(order.getCreateTime());
        process.setStatus(order.getStatus());
        process.setDescription(StatusEnum.getName(order.getStatus()));
        return processMapper.insertSelective(process);
    }

    @Override
    public OrderVo selectOrderDetail(Long id) {
        return orderMapper.selectOrderDetail(id);
    }

    @Override
    public Order selectOrderByOrderNo(String outTradeNo) {
        return orderMapper.selectOrderByOrderNo(outTradeNo);
    }

    @Transactional
    @Override
    public int updateSuccessByWxNotify(Long id, Map<String, String> map) throws ParseException {
        Date timeEnd = DateUtils.getDateFromString(map.get("time_end"), "yyyyMMddHHmmss");
        Order order = new Order();
        order.setId(id);
        order.setThirdOrderNo(map.get("transaction_id"));
        order.setPaySuccessTime(timeEnd);
        order.setStatus(StatusEnum.PAY_SUCCESS.getCode());
        orderMapper.updateByPrimaryKeySelective(order);

        OrderPay orderPay = new OrderPay();
        JSONObject object = JSONObject.parseObject(map.get("attach"));
        orderPay.setId(Long.valueOf(object.get("orderPayId").toString()));
        orderPay.setStatus(2);
        orderPay.setLastNotifyTime(new Date());
        orderPay.setPaySuccessTime(timeEnd);
        int num = orderPayMapper.updateByNotify(orderPay);
        if (num != 1) {
            logger.error("updateSuccessByWxNotify num error id={},orderNo={}", orderPay.getId(), map.get("out_trade_no"));
        }
        return num;
    }

    @Transactional
    @Override
    public int updateFailByWxNotify(Long id, Map<String, String> map) {
        Order order = new Order();
        order.setId(id);
        order.setThirdOrderNo(map.get("transaction_id"));
        order.setStatus(StatusEnum.PAY_FAIL.getCode());
        orderMapper.updateByPrimaryKeySelective(order);

        OrderPay orderPay = new OrderPay();
        JSONObject object = JSONObject.parseObject(map.get("attach"));
        orderPay.setId(Long.valueOf(object.get("orderPayId").toString()));
        orderPay.setStatus(3);
        orderPay.setErrCode(map.get("err_code"));
        orderPay.setErrMsg(map.get("err_code_des"));
        orderPay.setLastNotifyTime(new Date());
        int num = orderPayMapper.updateByNotify(orderPay);
        if (num != 1) {
            logger.error("updateFailByWxNotify num error id={},orderNo={}", orderPay.getId(), map.get("out_trade_no"));
        }
        return num;
    }

    @Transactional
    @Override
    public int updateSuccessByAliNotify(Long id, Map<String, String> map) throws ParseException {
        Date timeEnd = DateUtils.getDateFromString(map.get("gmt_payment"), "yyyy-MM-dd HH:mm:ss");
        Order order = new Order();
        order.setId(id);
        order.setThirdOrderNo(map.get("trade_no"));
        order.setPaySuccessTime(timeEnd);
        order.setStatus(StatusEnum.PAY_SUCCESS.getCode());
        orderMapper.updateByPrimaryKeySelective(order);

        OrderPay orderPay = new OrderPay();
        JSONObject object = JSONObject.parseObject(map.get("passback_params"));
        orderPay.setId(Long.valueOf(object.get("orderPayId").toString()));
        orderPay.setStatus(2);
        orderPay.setLastNotifyTime(new Date());
        orderPay.setPaySuccessTime(timeEnd);
        int num = orderPayMapper.updateByNotify(orderPay);
        if (num != 1) {
            logger.error("updateSuccessByAliNotify num error id={},orderNo={}", orderPay.getId(), map.get("out_trade_no"));
        }
        return num;
    }

    @Transactional
    @Override
    public int updateFailByAliNotify(Long id, Map<String, String> map) {
        Order order = new Order();
        order.setId(id);
        order.setThirdOrderNo(map.get("trade_no"));
        order.setStatus(StatusEnum.PAY_FAIL.getCode());
        orderMapper.updateByPrimaryKeySelective(order);

        OrderPay orderPay = new OrderPay();
        JSONObject object = JSONObject.parseObject(map.get("passback_params"));
        orderPay.setId(Long.valueOf(object.get("orderPayId").toString()));
        orderPay.setStatus(3);
        orderPay.setLastNotifyTime(new Date());
        int num = orderPayMapper.updateByNotify(orderPay);
        if (num != 1) {
            logger.error("updateFailByAliNotify num error id={},orderNo={}", orderPay.getId(), map.get("out_trade_no"));
        }
        return num;
    }
}
