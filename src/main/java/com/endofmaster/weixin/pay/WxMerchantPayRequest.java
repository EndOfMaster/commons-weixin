package com.endofmaster.weixin.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class WxMerchantPayRequest extends WxPayRequest<WxMerchantPayResponse> {

    private final String chargeId;
    private final String openId;
    private final String amount;
    private final String desc;
    private final String ip;

    public WxMerchantPayRequest(String chargeId, String openId, String amount, String desc, String ip) {
        this.chargeId = chargeId;
        this.openId = openId;
        this.amount = amount;
        this.desc = desc;
        this.ip = ip;
    }

    @Override
    Map<String, String> buildRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put("partner_trade_no", chargeId);
        params.put("openid", openId);
        params.put("check_name", "NO_CHECK");
        params.put("amount", amount);
        params.put("desc", desc);
        params.put("spbill_create_ip", ip);
        return params;
    }

    @Override
    protected String getUrl() {
        return "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    }

    @Override
    Class<WxMerchantPayResponse> responseClass() {
        return WxMerchantPayResponse.class;
    }
}
