package com.endofmaster.weixin.cardCoupons.basic;

/**
 * @author ZM.Wang
 */
public enum WxSubMerchantState {
    RECORD("已提交"),
    CHECKING("审核中"),
    APPROVED("已通过"),
    REJECTED("被驳回"),
    EXPIRED("协议已过期");

    private final String value;

    WxSubMerchantState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
