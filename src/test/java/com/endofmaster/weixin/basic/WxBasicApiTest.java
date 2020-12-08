package com.endofmaster.weixin.basic;

import com.endofmaster.weixin.support.WxHttpClientTest;
import org.junit.jupiter.api.Test;


public class WxBasicApiTest extends WxHttpClientTest {

    @Test
    public void getUserInfo() throws Exception {
        String accessToken = "dYg3Jxd0F4_ixamIuY5isouamucuoSfZKEAVjPnvRrGjYAoS4lS2ho-L_kQPwZfZIDkOYPFfPIxBMjnF-P0nTTqGlX8mY2s6sfl7RsX9cY2apL_wKj9-6Q5fXf13GBUtRVIgAAAHLY";
        WxAuthUserInfo info = BASIC_API.getUserInfo("oVmqjxC6EXehYK-rG90B3JISi5Pk", accessToken);
        System.err.println(info);
    }

}