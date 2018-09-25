package com.endofmaster.weixin.support;

import com.endofmaster.weixin.basic.WxBasicApi;

public abstract class WxHttpClientTest {

    public static final WxHttpClient CLIENT = new WxHttpClient(null, null);
    public static final WxBasicApi LIFE_BASIC_API = new WxBasicApi("wxe2190d22ce025e4f", "4a3f22e2ac05822b8b284e5a7c93b280", CLIENT);
    public static final WxBasicApi LEPAY_BASIC_API = new WxBasicApi("wx16edfa0dda02edd5", "2ff98b94441224bf584181e844a8af66", CLIENT);
    public static final WxBasicApi TEST_BASIC_API = new WxBasicApi("wxf730d02eb8848c9e", "d91940ff8184fa3031bc7c95e658a034", CLIENT);

}