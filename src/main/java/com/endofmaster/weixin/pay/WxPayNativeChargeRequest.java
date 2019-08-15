package com.endofmaster.weixin.pay;

import com.endofmaster.weixin.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class WxPayNativeChargeRequest extends WxPayRequest<WxPayNativeChargeResponse> {

    private final String outTradeNo;
    private final String body;
    private final String totalFee;
    private final String notifyUrl;
    private final String productId;
    private final String ip;

    public WxPayNativeChargeRequest(String outTradeNo, String body, String totalFee, String notifyUrl, String productId, String ip) {
        this.outTradeNo = outTradeNo;
        this.body = body;
        this.totalFee = totalFee;
        this.notifyUrl = notifyUrl;
        this.productId = productId;
        this.ip = ip;
    }

    @Override
    Map<String, String> buildRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", outTradeNo);
        params.put("body", body);
        params.put("total_fee", totalFee);
        params.put("notify_url", notifyUrl);
        params.put("product_id", productId);
        params.put("spbill_create_ip", ip);
        params.put("trade_type", "NATIVE");
        return params;
    }

    @Override
    protected String getUrl() {
        return Constant.UNIFIED_ORDER_URL;
    }

    @Override
    Class<WxPayNativeChargeResponse> responseClass() {
        return WxPayNativeChargeResponse.class;
    }
}
