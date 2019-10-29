package com.pay.service.ServiceImpl;

import com.pay.dao.mapper.OrderMapper;
import com.pay.dao.mapper.OrderSubMapper;
import com.pay.dao.mapper.ProcessMapper;
import com.pay.dao.model.Order;
import com.pay.dao.model.OrderSub;
import com.pay.dao.model.Process;
import com.pay.dao.vo.OrderVo;
import com.pay.enums.StatusEnum;
import com.pay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderSubMapper orderSubMapper;
    @Autowired
    private ProcessMapper processMapper;

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
}
