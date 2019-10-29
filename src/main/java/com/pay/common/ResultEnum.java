package com.pay.common;

/**
 * ucenter系统接口结果常量枚举类
 * Created by shuzheng on 2017/4/26.
 */
public enum ResultEnum {

    /**
     * 失败
     */
    FAILED(0, "失败"),

    /**
     * 成功
     */
    SUCCESS(1, "成功"),


    /**
     * 系统未知异常
     */
    SYSTEM_ERROR(100001, "系统未知异常"),
    /**
     * 缺少请求参数
     */
    PARAM_MISS_ERROR(100002, "缺少请求参数"),

    /**
     * 请求参数格式错误
     */
    PARAM_ERROR(100003, "请求参数错误"),

    /**
     * 请求服务器失败
     */
    REQUEST_ERROR(100020, "请求服务器失败"),


    //订单类
    GOODS_NOT_FOUND(100101, "未找到商品"),
    GOODS_OBTAINED(100102, "商品已下架");


    private final int code;

    private final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
