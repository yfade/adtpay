package com.pay.enums;

public enum TradeType {
    WX_APP("WX_APP", 0),
    WX_NATIVE("WX_NATIVE", 1),

    ALI_APP("ALI_APP", 5),
    ALI_PC("ALI_PC", 6);


    private final String name;
    private final int value;

    public static int getValue(String name) {
        for (TradeType tradeType : TradeType.values()) {
            if (name == tradeType.getName()) {
                return tradeType.getValue();
            }
        }
        throw new RuntimeException("tradeType not found:" + name);
    }

    TradeType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
