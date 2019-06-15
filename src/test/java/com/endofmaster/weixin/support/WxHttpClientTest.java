package com.endofmaster.weixin.support;

import com.endofmaster.weixin.basic.WxBasicApi;

public abstract class WxHttpClientTest {

    public static final WxHttpClient CLIENT = new WxHttpClient(null, null);
    public static final WxBasicApi BASIC_API = new WxBasicApi("********", "****************", CLIENT);

}