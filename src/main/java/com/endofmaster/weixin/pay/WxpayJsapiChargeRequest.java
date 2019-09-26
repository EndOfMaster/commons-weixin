package com.endofmaster.weixin.pay;

import java.util.HashMap;
import java.util.Map;

import static com.endofmaster.weixin.Constant.UNIFIED_ORDER_URL;

/**
 * @author YQ.Huang
 * @update ZM.Wang
 */
public class WxpayJsapiChargeRequest extends WxPayRequest<WxpayJsapiChargeResponse> {

    private final String body;
    private final String outTradeNo;
    private final String totalFee;
    private final String notifyUrl;
    private final String openId;
    private final String ip;

    public WxpayJsapiChargeRequest(String subject, String outTradeNo, long amount, String notifyUrl, String openId, String ip) {
        this.body = subject;
        this.outTradeNo = outTradeNo;
        this.totalFee = amount + "";
        this.notifyUrl = notifyUrl;
        this.openId = openId;
        this.ip = ip;
    }

    @Override
    protected String getUrl() {
        return UNIFIED_ORDER_URL;
    }

    @Override
    Class<WxpayJsapiChargeResponse> responseClass() {
        return WxpayJsapiChargeResponse.class;
    }

    @Override
    protected Map<String, String> buildRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put("body", body);
        params.put("out_trade_no", outTradeNo);
        params.put("total_fee", totalFee);
        params.put("spbill_create_ip", ip);
        params.put("notify_url", notifyUrl);
        params.put("trade_type", "JSAPI");
        params.put("openid", openId);
        return params;
    }

}
