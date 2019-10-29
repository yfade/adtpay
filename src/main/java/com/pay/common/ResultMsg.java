package com.pay.common;


public class ResultMsg<T> {
    private int code;
    private String msg;
    private T data;

    public static ResultMsg success() {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(ResultEnum.SUCCESS.getCode());
        resultMsg.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultMsg;
    }

    public static <T> ResultMsg<T> success(T t) {
        ResultMsg<T> resultMsg = new ResultMsg<>();
        resultMsg.setCode(ResultEnum.SUCCESS.getCode());
        resultMsg.setMsg(ResultEnum.SUCCESS.getMsg());
        resultMsg.setData(t);
        return resultMsg;
    }

    public static ResultMsg fail() {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(ResultEnum.FAILED.getCode());
        resultMsg.setMsg(ResultEnum.FAILED.getMsg());
        return resultMsg;
    }

    public static <T> ResultMsg<T> fail(T t) {
        ResultMsg<T> resultMsg = new ResultMsg<>();
        resultMsg.setCode(ResultEnum.FAILED.getCode());
        resultMsg.setMsg(ResultEnum.FAILED.getMsg());
        resultMsg.setData(t);
        return resultMsg;
    }

    public static ResultMsg fail(ResultEnum resultEnum) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(resultEnum.getCode());
        resultMsg.setMsg(resultEnum.getMsg());
        return resultMsg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
