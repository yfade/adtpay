package com.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.pay.common.ResultEnum;
import com.pay.common.ResultMsg;
import com.pay.dao.model.Goods;
import com.pay.dao.model.Order;
import com.pay.dao.model.OrderSub;
import com.pay.dao.vo.OrderVo;
import com.pay.dao.vo.serviceVo.AliPayOrder;
import com.pay.dao.vo.serviceVo.WxPayOrder;
import com.pay.enums.FeeTypeConstant;
import com.pay.enums.StatusEnum;
import com.pay.enums.SystemEnum;
import com.pay.enums.PayConstant;
import com.pay.service.GoodsService;
import com.pay.service.OrderService;
import com.pay.service.PayService;
import com.pay.util.IpUtil;
import com.pay.util.OrderNoGenerator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/pay")
public class PayController {
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @RequestMapping("/toIndex")
    public String toIndex() {
        return "index";
    }

    //本地生成订单
    @ResponseBody
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public ResultMsg createOrder(@RequestBody JSONObject data, HttpServletRequest request) {
        String userId = "111111";
        String userName = "user111";

        //1 校验参数
        if (!validateParam(data)) {
            return ResultMsg.fail(ResultEnum.PARAM_MISS_ERROR);
        }
        Long goodsId;
        Integer buyCount;
        try {
            goodsId = Long.valueOf(data.getString("goodsId"));
            buyCount = Integer.valueOf(data.getString("buyCount"));
        } catch (NumberFormatException e) {
            return ResultMsg.fail(ResultEnum.PARAM_ERROR);
        }
        if (buyCount <= 0) {
            return ResultMsg.fail(ResultEnum.PARAM_ERROR);
        }

        //2 核对商品
        Goods goods = goodsService.selectGoodsById(goodsId);
        if (goods == null) {
            return ResultMsg.fail(ResultEnum.GOODS_NOT_FOUND);
        }
        if (goods.getStatus() == 1) {
            return ResultMsg.fail(ResultEnum.GOODS_OBTAINED);
        }

        //3 创建订单
        Date now = new Date();
        OrderSub orderSub = new OrderSub();
        orderSub.setGoodsId(goods.getId());
        orderSub.setGoodsName(goods.getGoodsName());
        orderSub.setBuyCount(buyCount);
        orderSub.setPrice(goods.getPrice());
        orderSub.setDiscount(goods.getDiscount());
        orderSub.setAmount(goods.getPrice().multiply(goods.getDiscount()).multiply(new BigDecimal(buyCount)));
        orderSub.setCreateUserId(Long.valueOf(userId));
        orderSub.setCreateUserName(userName);
        orderSub.setCreateTime(now);

        Order order = new Order();
        order.setOrderNo(OrderNoGenerator.getOrderNo(goods.getBizType()));
        order.setUserId(Long.valueOf(userId));
        order.setUserName(userName);
        order.setTotalAmount(orderSub.getAmount());
        order.setStatus(StatusEnum.ORDER_GENERATED.getCode());
        order.setBizType(goods.getBizType());
        order.setSysSource(SystemEnum.QTCAPP.getCode());
        order.setRemark((String) data.get("remark"));
        order.setCreateUserId(Long.valueOf(userId));
        order.setCreateUserName(userName);
        order.setCreateTime(now);

        ResultMsg resultMsg;
        try {
            orderService.createOrder(order, orderSub);
            resultMsg = ResultMsg.success(order.getId());
        } catch (Exception e) {
            logger.error("PayController createOrder error:", e);
            resultMsg = ResultMsg.fail(ResultEnum.SYSTEM_ERROR);
        }

        return resultMsg;
    }

    @ResponseBody
    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
    public ResultMsg payOrder(@RequestBody JSONObject data, HttpServletRequest request) {
        String id = (String) data.get("id");
        if (StringUtils.isBlank(id)) {
            return ResultMsg.fail(ResultEnum.PARAM_MISS_ERROR);
        }

        OrderVo orderVo = orderService.selectOrderDetail(Long.valueOf(id));
        if (orderVo == null) {
            return ResultMsg.fail();
        }
        try {
            WxPayOrder wxPayOrder = new WxPayOrder();
            wxPayOrder.setBody(SystemEnum.QTCAPP.getMsg() + orderVo.getGoodsName());
            wxPayOrder.setOutTradeNo(orderVo.getOrderNo());
            wxPayOrder.setDeviceInfo((String) data.get("deviceInfo"));
            wxPayOrder.setFeeType(FeeTypeConstant.CNY);
            wxPayOrder.setTotalFee(orderVo.getTotalAmount().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
//            wxPayOrder.setSpbillCreateIp(IpUtil.getIp(request));
            wxPayOrder.setSpbillCreateIp("127.0.0.1");
            wxPayOrder.setNotifyUrl("http://www.example.com/wxpay/notify");
            wxPayOrder.setTradeType(PayConstant.WX_NATIVE);

            wxPayOrder.setUserId(orderVo.getUserId());
            wxPayOrder.setUserName(orderVo.getUserName());
            return payService.payOrder(wxPayOrder);
        } catch (Exception e) {
            logger.error("PayController payOrder error:", e);
            return ResultMsg.fail();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/payOrderAli", method = RequestMethod.POST)
    public ResultMsg payOrderAli(@RequestBody JSONObject data, HttpServletRequest request) {
        String id = (String) data.get("id");
        if (StringUtils.isBlank(id)) {
            return ResultMsg.fail(ResultEnum.PARAM_MISS_ERROR);
        }

        OrderVo orderVo = orderService.selectOrderDetail(Long.valueOf(id));
        if (orderVo == null) {
            return ResultMsg.fail();
        }
        try {
            AliPayOrder aliPayOrder = new AliPayOrder();
            aliPayOrder.setSubject(orderVo.getGoodsName());
            aliPayOrder.setBody(orderVo.getDescription());
            aliPayOrder.setOutTradeNo(orderVo.getOrderNo());
            aliPayOrder.setTimeoutExpress("30m");
            aliPayOrder.setTotalAmount(orderVo.getTotalAmount());
            aliPayOrder.setNotifyUrl("http://www.example.com/wxpay/notify");
            aliPayOrder.setTradeType(PayConstant.ALI_APP);

            aliPayOrder.setUserId(orderVo.getUserId());
            aliPayOrder.setUserName(orderVo.getUserName());

            return payService.payOrder(aliPayOrder);
        } catch (Exception e) {
            logger.error("PayController payOrder error:", e);
            return ResultMsg.fail();
        }
    }


    /**
     * 校验参数
     *
     * @param data
     * @return
     */
    private boolean validateParam(JSONObject data) {
        if (data == null) {
            return false;
        }
        if (StringUtils.isBlank((String) data.get("goodsId"))) {
            return false;
        }
        if (StringUtils.isBlank((String) data.get("buyCount"))) {
            return false;
        }
        return true;
    }

}
