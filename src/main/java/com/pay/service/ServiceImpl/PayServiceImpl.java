package com.pay.service.ServiceImpl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.pay.common.ResultEnum;
import com.pay.common.ResultMsg;
import com.pay.dao.model.OrderPay;
import com.pay.dao.vo.serviceVo.PayOrder;
import com.pay.dao.vo.serviceVo.WxPayOrder;
import com.pay.enums.WxPayConstant;
import com.pay.service.OrderPayService;
import com.pay.service.PayService;
import com.pay.wxsdk.MyConfig;
import com.pay.wxsdk.WXPay;
import com.pay.wxsdk.WXPayConstants;
import com.pay.wxsdk.WXPayUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
    @Autowired
    private OrderPayService orderPayService;

    @Override
    public ResultMsg payOrder(PayOrder payOrder) throws Exception {

        if (!validatePayOrder(payOrder)) {
            return ResultMsg.fail(ResultEnum.PARAM_ERROR);
        }
        if (payOrder.getTradeType().startsWith("WX")) {
            // 1.判断prepay_id是否有缓存
            // redis缓存取

            // 2.插入order_pay记录
            addPayOrder(payOrder);
            // 3.提取支付参数
            Map<String, String> map = getWxParamMap(payOrder);
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);
            // 4.微信支付同意下单
            Map<String, String> response = wxpay.unifiedOrder(map);
            // 5.拼接返回参数
            return makeWxResult(response, payOrder.getTradeType(), config.getKey(), wxpay.getSignType());
        }
        return ResultMsg.fail();
    }

    /**
     * 插入order_pay记录
     *
     * @param payOrder
     * @return
     */
    private int addPayOrder(PayOrder payOrder) {
        WxPayOrder wxPayOrder = (WxPayOrder) payOrder;
        OrderPay orderPay = new OrderPay();
        orderPay.setOrderNo(wxPayOrder.getOutTradeNo());
        orderPay.setPayMode(0);
        orderPay.setTotalAmount(new BigDecimal(wxPayOrder.getTotalFee()).divide(new BigDecimal(100), 2));
        orderPay.setStatus(0);
        orderPay.setClientIp(wxPayOrder.getSpbillCreateIp());
        orderPay.setDevice(wxPayOrder.getDeviceInfo());
        orderPay.setTitle(wxPayOrder.getBody());
        orderPay.setCreateTime(new Date());
        return orderPayService.insertOrderPayService(orderPay);
    }

    /**
     * 封装微信支付返回数据
     *
     * @param response
     * @param tradeType
     * @param key
     * @param signType
     * @return
     * @throws Exception
     */
    private ResultMsg makeWxResult(Map<String, String> response, String tradeType, String key, WXPayConstants.SignType signType) throws Exception {
        if (WxPayConstant.SUCCESS.equals(response.get("return_code")) && WxPayConstant.SUCCESS.equals(response.get("result_code"))) {
            Map<String, String> paramMap = new HashMap<>();
            switch (tradeType) {
                case WxPayConstant.WX_APP: {
                    paramMap.put("appid", response.get("appid"));
                    paramMap.put("partnerid", response.get("mch_id"));
                    paramMap.put("prepayid", response.get("prepay_id"));
                    paramMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
                    paramMap.put("nonce_str", response.get("nonce_str"));
                    paramMap.put("prepay_id", "Sign=WXPay");
                    paramMap.put("sign", WXPayUtil.generateSignature(paramMap, key, signType));
                    return ResultMsg.success(paramMap);
                }
                case WxPayConstant.WX_NATIVE: {
                    paramMap.put("prepay_id", response.get("prepay_id"));
                    paramMap.put("code_url", response.get("code_url"));
                    return ResultMsg.success(paramMap);
                }
            }
        }
        logger.error("wx unifiedOrder fail:" + response.toString());
        return ResultMsg.fail(response);
    }


    /**
     * 封装微信支付map
     *
     * @param payOrder
     * @return
     */
    private Map<String, String> getWxParamMap(PayOrder payOrder) throws JsonProcessingException {
        WxPayOrder wxPayOrder = (WxPayOrder) payOrder;
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String reqJson = mapper.writeValueAsString(wxPayOrder);
        Map<String, String> map = JSONObject.parseObject(reqJson, Map.class);
        map.put("trade_type", map.get("trade_type").substring(3));
        return map;
    }

    /**
     * 校验下单参数
     *
     * @param payOrder
     * @return
     */
    private boolean validatePayOrder(PayOrder payOrder) {
        if (StringUtils.isBlank(payOrder.getOutTradeNo())) {
            return false;
        }
        if (StringUtils.isBlank(payOrder.getTradeType())) {
            return false;
        }
        switch (payOrder.getTradeType()) {
            case WxPayConstant.WX_APP: {
                WxPayOrder wxPayOrder = (WxPayOrder) payOrder;
                if (StringUtils.isBlank(wxPayOrder.getBody())) {
                    return false;
                }
                if (StringUtils.isBlank(wxPayOrder.getTotalFee())) {
                    return false;
                }
                if (StringUtils.isBlank(wxPayOrder.getSpbillCreateIp())) {
                    return false;
                }
            }
        }
        return true;
    }
}