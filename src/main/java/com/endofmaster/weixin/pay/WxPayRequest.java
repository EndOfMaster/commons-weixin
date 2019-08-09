package com.endofmaster.weixin.pay;

import java.util.Map;

/**
 * @author ZM.Wang
 */
public abstract class WxPayRequest<T extends WxPayResponse> {

    protected abstract String getUrl();

    abstract Class<T> responseClass();

    abstract Map<String, String> buildRequestParams();
}
