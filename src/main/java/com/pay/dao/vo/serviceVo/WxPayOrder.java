package com.pay.dao.vo.serviceVo;

public class WxPayOrder extends PayOrder {
    private String totalFee; //订单总金额，单位为分
    private String detail; //商品详细描述
    private String attach; //附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
    private String feeType; //货币类型
    private String timeStart; //交易起始时间
    private String timeExpire; //交易结束时间
    private String goodsTag; //订单优惠标记

    private String productId; //商品ID,trade_type=NATIVE时，此参数必传。

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}