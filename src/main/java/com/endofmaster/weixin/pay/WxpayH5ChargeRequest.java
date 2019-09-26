package com.endofmaster.weixin.pay;

import java.util.HashMap;
import java.util.Map;

import static com.endofmaster.weixin.Constant.UNIFIED_ORDER_URL;

/**
 * @author ZM.Wang
 */
public class WxpayH5ChargeRequest extends WxPayRequest<WxpayH5ChargeResponse> {

    private final String body;
    private final String outTradeNo;
    private final String totalFee;
    private final String notifyUrl;
    private final String ip;
    private final String url;

    public WxpayH5ChargeRequest(String body, String outTradeNo, String totalFee, String notifyUrl, String ip, String url) {
        this.body = body;
        this.outTradeNo = outTradeNo;
        this.totalFee = totalFee;
        this.notifyUrl = notifyUrl;
        this.ip = ip;
        this.url = url;
    }

    @Override
    protected String getUrl() {
        return UNIFIED_ORDER_URL;
    }

    @Override
    Class<WxpayH5ChargeResponse> responseClass() {
        return WxpayH5ChargeResponse.class;
    }

    @Override
    Map<String, String> buildRequestParams() {
        Map<String, String> params = new HashMap<>();
        params.put("body", body);
        params.put("out_trade_no", outTradeNo);
        params.put("total_fee", totalFee);
        params.put("spbill_create_ip", ip);
        params.put("notify_url", notifyUrl);
        params.put("trade_type", "MWEB");
        params.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"" + url + "\",\"wap_name\": \"为聚爱充值\"}}");
        return params;
    }
}
