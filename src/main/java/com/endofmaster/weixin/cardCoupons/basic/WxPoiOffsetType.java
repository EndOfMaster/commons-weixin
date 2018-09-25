package com.endofmaster.weixin.cardCoupons.basic;

public enum WxPoiOffsetType {
    MARS(1),
    SOGOU(2),
    BAIDU(3),
    MAPBAR(4),
    GPS(5),
    SOGOU_MERCATOR(6);

    private final int value;

    WxPoiOffsetType(int value) {
        this.value = value;
    }

    public static WxPoiOffsetType valueOf(int value) {
        switch (value) {
            case 1:
                return MARS;
            case 2:
                return SOGOU;
            case 3:
                return BAIDU;
            case 4:
                return MAPBAR;
            case 5:
                return GPS;
            case 6:
                return SOGOU_MERCATOR;
            default:
                throw new IllegalArgumentException("没有该坐标类型：" + value);
        }
    }

    public int getValue() {
        return value;
    }
}
