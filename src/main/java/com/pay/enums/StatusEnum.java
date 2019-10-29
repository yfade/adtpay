package com.pay.enums;

public enum StatusEnum {
    WAIT_CONFIRM(0, "待确认"),
    RECEIVED(1, "客服已接收"),
    CONFIRMED(5, "与客户已确认"),
    CANCEL_REQUIREMENT(10, "客户取消需求"),
    ORDERED(15, "客户已下单"),
    ORDER_GENERATED(18, "订单生成"),
    PAY_FAIL(20, "客户付款失败"),
    PAY_SUCCESS(25, "客户已付款"),
    THIRD_PARTY_COMMUNICATE(30, "与第三方沟通"),
    THIRD_PARTY_DELIVERY(35, "第三方已反馈交付"),
    DELIVERY_USER(40, "数据已交付客户"),
    ORDER_FINISHED(45, "订单完成"),
    REFUNDING(90, "退款中"),
    REFUND_FINISHED(91, "退款完成"),
    UNKNOWN(99, "未知");

    private final int code;
    private final String name;

    StatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static StatusEnum getStatusCode(int code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (code == statusEnum.getCode()) {
                return statusEnum;
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (code == statusEnum.getCode()) {
                return statusEnum.getName();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
