package com.endofmaster.weixin.cardCoupons.basic;

public enum WxPoiUpdataStatus {

    /** 扩展字段没有在更新中或更新已生效，可以再次更新 */
    UPDATED(0),

    /** 扩展字段正在更新中，尚未生效，不允许再次更新 */
    UPDATING(1);

    private final int value;

    WxPoiUpdataStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WxPoiUpdataStatus valueOf(int value){
        switch (value){
            case 0 : return UPDATED;
            case 1 : return UPDATING;
            default: throw new IllegalArgumentException("没有该门店更新状态：" + value);
        }
    }
}
