package com.pay.enums;

public class PayConstant {
    //微信支付类型
    public static final String WX_JSAPI = "WX_JSAPI";//JSAPI支付（或小程序支付）
    public static final String WX_NATIVE = "WX_NATIVE";//Native支付
    public static final String WX_APP = "WX_APP";//app支付
    public static final String WX_MWEB = "WX_MWEB";//H5支付

    public static final String RETURN_CODE = "return_code";
    public static final String RESULT_CODE = "result_code";

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    //支付宝支付类型
    public static final String ALI_APP = "ALI_APP";//APP支付
    public static final String ALI_PC = "WX_PC";//电脑端支付

    //支付宝交易状态
    public final static String TRADE_STATUS_WAIT = "WAIT_BUYER_PAY";        // 交易创建,等待买家付款
    public final static String TRADE_STATUS_CLOSED = "TRADE_CLOSED";        // 交易关闭
    public final static String TRADE_STATUS_SUCCESS = "TRADE_SUCCESS";        // 交易成功
    public final static String TRADE_STATUS_FINISHED = "TRADE_FINISHED";    // 交易成功且结束
}
