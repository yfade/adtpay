package com.pay.enums;

public enum SystemEnum {

    QTCAPP(1, "企通查APP"),
    JSAPP(2, "锦时APP"),
    ENT(10, "企业大数据平台"),
    RUISI(20, "锐思");

    private final int code;
    private final String msg;

    SystemEnum(int code, String msg) {
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
