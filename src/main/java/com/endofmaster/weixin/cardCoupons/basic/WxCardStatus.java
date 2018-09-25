package com.endofmaster.weixin.cardCoupons.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public enum WxCardStatus {

    NOT_VERIFY("CARD_STATUS_NOT_VERIFY", "待审核"),
    VERIFY_FAIL("CARD_STATUS_VERIFY_FAIL", "审核失败"),
    VERIFY_OK("CARD_STATUS_VERIFY_OK", "通过审核"),
    DELETE("CARD_STATUS_DELETE", "卡券被商户删除"),
    DISPATCH("CARD_STATUS_DISPATCH", "在公众平台投放过的卡券");

    private final static Map<String, WxCardStatus> statuses = new HashMap<>();

    static {
        statuses.put("CARD_STATUS_NOT_VERIFY", NOT_VERIFY);
        statuses.put("CARD_STATUS_VERIFY_FAIL", VERIFY_FAIL);
        statuses.put("CARD_STATUS_VERIFY_OK", VERIFY_OK);
        statuses.put("CARD_STATUS_DELETE", DELETE);
        statuses.put("CARD_STATUS_DISPATCH", DISPATCH);
    }

    private final String name;
    private final String value;

    WxCardStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static WxCardStatus valueOfByName(String name) {
        return statuses.get(name);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
