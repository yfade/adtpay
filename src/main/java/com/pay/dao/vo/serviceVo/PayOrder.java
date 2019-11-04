package com.pay.dao.vo.serviceVo;

import java.io.Serializable;

public class PayOrder implements Serializable {
    private String body;
    private String outTradeNo;
    private String notifyUrl;
    private String tradeType;
    private String spbillCreateIp;
    private String deviceInfo;

    //业务字段,提取下单接口参数时要在getWxParamMap方法中过滤掉
    private BizModel bizModel; //必传

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public BizModel getBizModel() {
        return bizModel;
    }

    public void setBizModel(BizModel bizModel) {
        this.bizModel = bizModel;
    }
}