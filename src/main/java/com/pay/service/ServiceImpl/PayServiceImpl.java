package com.pay.service.ServiceImpl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.pay.ali.AliDevPayConfig;
import com.pay.common.ResultEnum;
import com.pay.common.ResultMsg;
import com.pay.dao.mapper.OrderPayMapper;
import com.pay.dao.mapper.ProcessMapper;
import com.pay.dao.model.OrderPay;
import com.pay.dao.model.Process;
import com.pay.dao.vo.serviceVo.AliPayOrder;
import com.pay.dao.vo.serviceVo.PayOrder;
import com.pay.dao.vo.serviceVo.WxPayOrder;
import com.pay.enums.PayConstant;
import com.pay.service.PayService;
import com.pay.wx.MyConfig;
import com.pay.wx.WXPay;
import com.pay.wx.WXPayConstants;
import com.pay.wx.WXPayUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {
    private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
    @Autowired
    private OrderPayMapper orderPayMapper;
    @Autowired
    private ProcessMapper processMapper;

    @Override
    @Transactional
    public ResultMsg payOrder(PayOrder payOrder) {
        if (!validatePayOrder(payOrder)) {
            return ResultMsg.fail(ResultEnum.PARAM_ERROR);
        }
        // 插入order_pay记录、日志
        OrderPay orderPay = addPayOrder(payOrder);

        ResultMsg resultMsg = ResultMsg.fail();
        // 下单
        try {
            switch (payOrder.getTradeType()) {
                case PayConstant.WX_APP:
                case PayConstant.WX_NATIVE:
                    resultMsg = doWxPay(payOrder);
                    break;
                case PayConstant.ALI_APP:
                    resultMsg = doAliAppPay(payOrder);
                    break;
            }
        } catch (Exception e) {
            logger.error("payOrder error:", e);
        }

        if (resultMsg.getCode() == 1) {
            // 微信prepayId/支付宝签名 放入redis

        } else {
            //将order_pay状态改为 预下单失败
            OrderPay model = new OrderPay();
            model.setStatus(2);
            model.setId(orderPay.getId());
            orderPayMapper.updateByPrimaryKeySelective(model);
        }
        return resultMsg;
    }

    private ResultMsg doWxPay(PayOrder payOrder) {
        // 1.判断prepay_id/支付宝签名 是否有缓存
        // redis 如果有缓存直接返回

        try {
            // 2.提取支付参数
            Map<String, String> map = getWxParamMap(payOrder);
            // 3.微信支付统一下单
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);
            Map<String, String> response = wxpay.unifiedOrder(map);
            // 4.拼接返回参数
            return makeWxResult(response, payOrder.getTradeType(), config.getKey(), wxpay.getSignType());
        } catch (Exception e) {
            logger.error("doWxPay error:", e);
            return ResultMsg.fail();
        }
    }

    private ResultMsg doAliAppPay(PayOrder payOrder) {
        AliPayOrder aliPayOrder = (AliPayOrder) payOrder;
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(AliDevPayConfig.URL, AliDevPayConfig.APPID, AliDevPayConfig.RSA_PRIVATE_KEY,
                AliDevPayConfig.FORMAT, AliDevPayConfig.CHARSET, AliDevPayConfig.ALIPAY_PUBLIC_KEY, AliDevPayConfig.SIGNTYPE);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(aliPayOrder.getBody());
        model.setSubject(aliPayOrder.getSubject());
        model.setOutTradeNo(aliPayOrder.getOutTradeNo());
        model.setTimeoutExpress(aliPayOrder.getTimeoutExpress());
        model.setTotalAmount(aliPayOrder.getTotalAmount().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(aliPayOrder.getNotifyUrl());

        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            return ResultMsg.success(response.getBody());
        } catch (AlipayApiException e) {
            logger.error("doAliAppPay error:", e);
            return ResultMsg.fail();
        }
    }

    /**
     * 插入order_pay记录
     *
     * @param payOrder
     * @return
     */
    private OrderPay addPayOrder(PayOrder payOrder) {
        OrderPay orderPay = new OrderPay();
        switch (payOrder.getTradeType()) {
            case PayConstant.WX_APP:
            case PayConstant.WX_NATIVE: {
                WxPayOrder wxPayOrder = (WxPayOrder) payOrder;
                orderPay.setOrderNo(wxPayOrder.getOutTradeNo());
                orderPay.setUserId(wxPayOrder.getUserId());
                orderPay.setPayMode(0);
                orderPay.setTotalAmount(new BigDecimal(wxPayOrder.getTotalFee()).divide(new BigDecimal(100), 2));
                orderPay.setStatus(0);
                orderPay.setClientIp(wxPayOrder.getSpbillCreateIp());
                orderPay.setDevice(wxPayOrder.getDeviceInfo());
                orderPay.setTitle(wxPayOrder.getBody());
                orderPay.setCreateTime(new Date());
                break;
            }
            case PayConstant.ALI_APP: {
                AliPayOrder aliPayOrder = (AliPayOrder) payOrder;
                orderPay.setOrderNo(aliPayOrder.getOutTradeNo());
                orderPay.setUserId(aliPayOrder.getUserId());
                orderPay.setPayMode(1);
                orderPay.setTotalAmount(aliPayOrder.getTotalAmount().divide(new BigDecimal(100), 2));
                orderPay.setStatus(0);
                orderPay.setClientIp(aliPayOrder.getSpbillCreateIp());
                orderPay.setDevice(aliPayOrder.getDeviceInfo());
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
        process.setOperatorUserId(payOrder.getUserId());
        process.setOperatorUserName(payOrder.getUserName());
        process.setOperateTime(orderPay.getCreateTime());
        process.setOrderNo(payOrder.getOutTradeNo());
        process.setDescription("进行预下单");
        processMapper.insertSelective(process);
        return orderPay;
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
        if (PayConstant.SUCCESS.equals(response.get("return_code")) && PayConstant.SUCCESS.equals(response.get("result_code"))) {
            Map<String, String> paramMap = new HashMap<>();
            switch (tradeType) {
                case PayConstant.WX_APP: {
                    paramMap.put("appid", response.get("appid"));
                    paramMap.put("partnerid", response.get("mch_id"));
                    paramMap.put("prepayid", response.get("prepay_id"));
                    paramMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
                    paramMap.put("nonce_str", response.get("nonce_str"));
                    paramMap.put("prepay_id", "Sign=WXPay");
                    paramMap.put("sign", WXPayUtil.generateSignature(paramMap, key, signType));
                    return ResultMsg.success(paramMap);
                }
                case PayConstant.WX_NATIVE: {
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
        map.remove("user_id");
        map.remove("user_name");
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
            case PayConstant.WX_APP: {
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