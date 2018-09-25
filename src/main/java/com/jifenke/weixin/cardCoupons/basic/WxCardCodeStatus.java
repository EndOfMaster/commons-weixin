package com.jifenke.weixin.cardCoupons.basic;

public enum WxCardCodeStatus {
    NORMAL("正常"),
    CONSUMED("已核销"),
    EXPIRE("已过期"),
    GIFTING("转赠中"),
    GIFT_TIMEOUT("转赠超时"),
    DELETE("已删除"),
    UNAVAILABLE("已失效");

    private final String name;

    WxCardCodeStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
