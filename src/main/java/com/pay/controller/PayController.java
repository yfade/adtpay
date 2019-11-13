package com.pay.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.pay.ali.AliDevPayConfig;
import com.pay.common.ResultEnum;
import com.pay.common.ResultMsg;
import com.pay.dao.model.Goods;
import com.pay.dao.model.Order;
import com.pay.dao.model.OrderSub;
import com.pay.dao.vo.OrderVo;
import com.pay.dao.vo.serviceVo.AliPayOrder;
import com.pay.dao.vo.serviceVo.BizModel;
import com.pay.dao.vo.serviceVo.WxPayOrder;
import com.pay.enums.FeeTypeConstant;
import com.pay.enums.PayConstant;
import com.pay.enums.StatusEnum;
import com.pay.enums.SystemEnum;
import com.pay.service.GoodsService;
import com.pay.service.OrderService;
import com.pay.service.PayService;
import com.pay.util.OrderNoGenerator;
import com.pay.wx.MyConfig;
import com.pay.wx.WXPay;
import com.pay.wx.WXPayUtil;
import org.apache.commons.io.IOUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        logger.info("enter index...");
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

            BizModel bizModel = new BizModel();
            bizModel.setUserId(orderVo.getUserId());
            bizModel.setUserName(orderVo.getUserName());
            wxPayOrder.setBizModel(bizModel);
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
            aliPayOrder.setSubject(SystemEnum.QTCAPP.getMsg() + orderVo.getGoodsName() + orderVo.getGoodsName());
            aliPayOrder.setBody(orderVo.getDescription());
            aliPayOrder.setOutTradeNo(orderVo.getOrderNo());
            aliPayOrder.setTimeoutExpress("30m");
            aliPayOrder.setTotalAmount(orderVo.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            aliPayOrder.setNotifyUrl("http://www.example.com/wxpay/notify");
            aliPayOrder.setTradeType(PayConstant.ALI_APP);
            aliPayOrder.setSpbillCreateIp("127.0.0.1");
            aliPayOrder.setDeviceInfo((String) data.get("deviceInfo"));

            BizModel bizModel = new BizModel();
            bizModel.setUserId(orderVo.getUserId());
            bizModel.setUserName(orderVo.getUserName());
            aliPayOrder.setBizModel(bizModel);

            return payService.payOrder(aliPayOrder);
        } catch (Exception e) {
            logger.error("PayController payOrder error:", e);
            return ResultMsg.fail();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/payAliPC")
    public void payAliPC(String id, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(id)) {
            return;
        }

        OrderVo orderVo = orderService.selectOrderDetail(Long.valueOf(id));
        if (orderVo == null) {
            return;
        }
        try {
            AliPayOrder aliPayOrder = new AliPayOrder();
            aliPayOrder.setOutTradeNo(orderVo.getOrderNo());
            aliPayOrder.setProductCode("FAST_INSTANT_TRADE_PAY");
            aliPayOrder.setTotalAmount(orderVo.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
            aliPayOrder.setSubject(SystemEnum.QTCAPP.getMsg() + orderVo.getGoodsName() + orderVo.getGoodsName());
            aliPayOrder.setBody(orderVo.getDescription());
            aliPayOrder.setTimeoutExpress("30m");
            aliPayOrder.setTradeType(PayConstant.ALI_PC);
            aliPayOrder.setDeviceInfo("honor9");
            aliPayOrder.setSpbillCreateIp("127.0.0.1");

            BizModel bizModel = new BizModel();
            bizModel.setUserId(orderVo.getUserId());
            bizModel.setUserName(orderVo.getUserName());
            aliPayOrder.setBizModel(bizModel);
            ResultMsg resultMsg = payService.payOrder(aliPayOrder);
            if (resultMsg.getCode() == 1) {
                String result = resultMsg.getData().toString();
                response.setContentType("text/html;charset=" + AliDevPayConfig.CHARSET);
                response.getWriter().write(result);//直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();
            }
        } catch (Exception e) {
            logger.error("PayController payAliPC error:", e);
        }
    }


    /**
     * 微信支付异步通知
     *
     * @param request
     * @return
     */
    @RequestMapping("/wxNotify")
    public String wxNotify(HttpServletRequest request) {
        String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        try {
            String notifyXml = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyXml);

            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);
            // 校验签名
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                if (!PayConstant.SUCCESS.equals(notifyMap.get(PayConstant.RETURN_CODE))) {
                    logger.error("wxNotify returnCode={}", notifyMap.get(PayConstant.RETURN_CODE));
                    return result;
                }
                String outTradeNo = notifyMap.get("out_trade_no");
                Order order = orderService.selectOrderByOrderNo(outTradeNo);
                // 校验订单金额、状态信息
                if (!wxVerifyOrder(order, notifyMap)) {
                    logger.error("wxNotify wxVerifyOrder fail, orderNo={}", outTradeNo);
                    return result;
                }
                // 如果订单已处理过，则直接返回success
                if (order.getStatus() >= StatusEnum.PAY_SUCCESS.getCode()) {
                    logger.error("wxNotify order status already is " + order.getStatus());
                    result = "<xml>\n" + "  <return_code><![CDATA[SUCCESS]]></return_code>\n" + "  <return_msg><![CDATA[OK]]></return_msg>\n" + "</xml>";
                    return result;
                }

                if (PayConstant.SUCCESS.equals(notifyMap.get(PayConstant.RESULT_CODE))) {
                    // 支付成功，修改订单状态
                    orderService.updateSuccessByWxNotify(order.getId(), notifyMap);
                    result = "<xml>\n" + "  <return_code><![CDATA[SUCCESS]]></return_code>\n" + "  <return_msg><![CDATA[OK]]></return_msg>\n" + "</xml>";
                } else {
                    //支付失败，修改订单状态
                    logger.error("wxNotify resultCode={}", notifyMap.get(PayConstant.RESULT_CODE));
                    orderService.updateFailByWxNotify(order.getId(), notifyMap);
                }
            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                logger.error("wxNotify sign error");
            }
        } catch (Exception e) {
            logger.error("wxNotify error:", e);
        }
        return result;
    }

    /**
     * 支付宝支付异步通知
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/aliNotify", method = RequestMethod.POST)
    public String aliNotify(HttpServletRequest request) {
        // 提取参数
        Map<String, String> params = getAliNotifyParams(request);
        String result = "fail";
        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, AliDevPayConfig.ALIPAY_PUBLIC_KEY, AliDevPayConfig.CHARSET, AliDevPayConfig.SIGNTYPE);
            if (flag) {
                String outTradeNo = params.get("out_trade_no");
                String tradeStatue = params.get("trade_status");
                Order order = orderService.selectOrderByOrderNo(outTradeNo);
                // 校验订单金额、状态信息
                if (!aliVerifyOrder(order, params)) {
                    logger.error("aliNotify aliVerifyOrder fail, orderNo={}", outTradeNo);
                    return result;
                }
                // 如果订单已处理过，则直接返回success
                if (order.getStatus() >= StatusEnum.PAY_SUCCESS.getCode()) {
                    logger.error("aliNotify order status already is " + order.getStatus());
                    result = "success";
                    return result;
                }

                if (tradeStatue.equals(PayConstant.TRADE_STATUS_SUCCESS) || tradeStatue.equals(PayConstant.TRADE_STATUS_FINISHED)) {
                    orderService.updateSuccessByAliNotify(order.getId(), params);
                    result = "success";
                } else {
                    //支付失败，修改订单状态
                    logger.error("aliNotify tradeStatus={}", tradeStatue);
                    orderService.updateFailByAliNotify(order.getId(), params);
                }

            } else {
                // 签名错误
                logger.error("aliNotify sign error");
            }
        } catch (Exception e) {
            logger.error("aliNotify error:", e);
        }
        return result;
    }

    /**
     * 获取支付宝支付异步通知参数
     *
     * @param request
     * @return
     */
    private Map<String, String> getAliNotifyParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * 支付宝异步通知验证订单信息
     *
     * @param order
     * @param params
     * @return
     */
    private boolean aliVerifyOrder(Order order, Map<String, String> params) {
        String outTradeNo = params.get("out_trade_no");
        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        if (order == null) {
            logger.error("wxNotify order is null " + outTradeNo);
            return false;
        }

        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        BigDecimal totalAmount = order.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        if (totalAmount.compareTo(new BigDecimal(params.get("total_amount"))) == 0) {
            logger.error("wxNotify totalFee not equal " + outTradeNo);
            return false;
        }

        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(AliDevPayConfig.APPID)) {
            return false;
        }
        return true;
    }

    /**
     * 微信异步通知验证订单信息
     *
     * @param order
     * @param notifyMap
     * @return
     */
    private boolean wxVerifyOrder(Order order, Map<String, String> notifyMap) {
        String outTradeNo = notifyMap.get("out_trade_no");
        // 验证订单号
        if (order == null) {
            logger.error("wxNotify order is null " + outTradeNo);
            return false;
        }
        //验证订单金额是否一致
        BigDecimal totalAmount = order.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        if (totalAmount.compareTo(new BigDecimal(notifyMap.get("total_fee"))) == 0) {
            logger.error("wxNotify totalFee not equal " + outTradeNo);
            return false;
        }
        return true;
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
