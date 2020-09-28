package com.endofmaster.weixin.pay;

import java.util.Map;

/**
 * @author ZM.Wang
 */
public class WxpayMiniAppChargeRequest extends WxPayRequest<WxpayJsapiChargeResponse>{
    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    Class<WxpayJsapiChargeResponse> responseClass() {
        return null;
    }

    @Override
    Map<String, String> buildRequestParams() {
        return null;
    }
}
