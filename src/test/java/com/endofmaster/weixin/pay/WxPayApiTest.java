package com.endofmaster.weixin.pay;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author ZM.Wang
 */
public class WxPayApiTest {

    private final WxPayApi wxPayApi;

    public WxPayApiTest(){
        this.wxPayApi=new WxPayApi("*******","wx94581d0e2c01d1b0","1550785821");
    }

    @Test
    public void test(){
        String id=RandomStringUtils.randomAlphanumeric(32);
        WxPayNativeChargeRequest request=new WxPayNativeChargeRequest(id,"测试",
                "100","http://127.0.0.1",id,"127.0.0.1");
        WxPayNativeChargeResponse response = wxPayApi.execute(request);
        System.err.println(response.getCodeUrl());
    }
}
