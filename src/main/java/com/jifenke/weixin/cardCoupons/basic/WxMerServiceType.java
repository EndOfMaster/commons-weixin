package com.jifenke.weixin.cardCoupons.basic;

/**
 * @author ZM.Wang
 */
public enum WxMerServiceType {
    DELIVER("BIZ_SERVICE_DELIVER"),
    FREE_PARK("BIZ_SERVICE_FREE_PARK"),
    WITH_PET("BIZ_SERVICE_WITH_PET"),
    FREE_WIFI("BIZ_SERVICE_FREE_WIFI");

    private final String value;

    WxMerServiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
