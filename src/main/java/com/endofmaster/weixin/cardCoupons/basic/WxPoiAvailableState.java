package com.endofmaster.weixin.cardCoupons.basic;

public enum WxPoiAvailableState {

    /** 系统错误 */
    ERROR(1, "系统错误"),

    /** 审核中 */
    DURING(2, "审核中"),

    /** 审核通过 */
    PASSED(3, "审核通过"),

    /** 审核驳回 */
    REJECT(4, "审核驳回");

    private final int value;
    private final String name;

    WxPoiAvailableState(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static WxPoiAvailableState valueOf(int value){
        switch (value) {
            case 1:
                return ERROR;
            case 2:
                return DURING;
            case 3:
                return PASSED;
            case 4:
                return REJECT;
            default:
                throw new IllegalArgumentException("没有该门店审核状态：" + value);
        }
    }

}
