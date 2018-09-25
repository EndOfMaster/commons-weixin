package com.jifenke.weixin.cardCoupons.basic;

/**
 * @author ZM.Wang
 */
public enum WxCardType {
    GROUPON("groupon", "团购券"),
    CASH("cash", "代金券"),
    DISCOUNT("discount", "折扣券"),
    GIFT("gift", "兑换券"),
    GENERAL_COUPON("general_coupon", "优惠券");

    private final String jsonTree;
    private final String name;

    WxCardType(String jsonTree, String name) {
        this.jsonTree = jsonTree;
        this.name = name;
    }

    public String getJsonTree() {
        return jsonTree;
    }

    public String getName() {
        return name;
    }
}
