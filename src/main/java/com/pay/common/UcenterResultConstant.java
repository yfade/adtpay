package com.pay.common;

/**
 * ucenter系统接口结果常量枚举类
 * Created by shuzheng on 2017/4/26.
 */
public enum UcenterResultConstant {

    /**
     * 失败
     */
    FAILED(0, "failed"),

    /**
     * 成功
     */
    SUCCESS(1, "success"),


    /**
     * 密码不能为空
     */
    EMPTY_PASSWORD(10102, "Password cannot be empty"),

    /**
     * 帐号不能为空
     */
    EMPTY_USERNAME(10101, "Account does not exist"),

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
    PRODUCT_PARAM_MISS(200001, "缺少商品参数"),
    PRODUCT_FORMAT_ERROR(200002, "商品参数格式异常"),
    PRODUCT_NOT_FOUND(200003, "未找到商品"),
    PRODUCT_OBTAINED(200004, "商品已下架");


    public int code;

    public String message;

    UcenterResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
