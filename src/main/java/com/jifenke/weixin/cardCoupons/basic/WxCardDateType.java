package com.jifenke.weixin.cardCoupons.basic;

public enum WxCardDateType {

    /**
     * 固定的时间范围，比如2017.10.1到2017.10.9
     */
    TIME_RANGE("DATE_TYPE_FIX_TIME_RANGE"),

    /**
     * 领取后有效期，比如领取后3天内有效
     */
    FIX_TERM("DATE_TYPE_FIX_TERM");

    private final String value;

    WxCardDateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
