package com.endofmaster.weixin.jssdk;

import com.endofmaster.weixin.WxApi;
import com.endofmaster.weixin.cardCoupons.wxResponse.WxCardApiTicket;
import com.endofmaster.weixin.support.WxHttpClient;
import com.endofmaster.weixin.support.WxHttpRequest;
import com.endofmaster.weixin.support.WxHttpResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author ZM.Wang
 */
public class WxJsApi extends WxApi {

    private final String appId;

    public WxJsApi(WxHttpClient client, String appId) {
        super(client);
        this.appId = appId;
    }

    public WxJsapiTicket getApiTicket(String accessToken) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket")
                .setArg("access_token", accessToken)
                .setArg("type", "jsapi");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxJsapiTicket.class);
    }

    public WxCardApiTicket getCardApiTicket(String accessToken) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket")
                .setArg("access_token", accessToken)
                .setArg("type", "wx_card");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxCardApiTicket.class);
    }

    public WxJsSign getSign(String ticket, String url) {
        String nonceStr = RandomStringUtils.randomAlphanumeric(16);
        long timestamp = System.currentTimeMillis();
        String preSignStr = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        String sign = DigestUtils.sha1Hex(preSignStr);
        return new WxJsSign(appId, timestamp, nonceStr, sign);
    }
}
