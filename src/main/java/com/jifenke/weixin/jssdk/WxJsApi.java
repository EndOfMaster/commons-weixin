package com.jifenke.weixin.jssdk;

import com.jifenke.weixin.WxApi;
import com.jifenke.weixin.WxException;
import com.jifenke.weixin.cardCoupons.wxResponse.WxCardApiTicket;
import com.jifenke.weixin.support.WxHttpClient;
import com.jifenke.weixin.support.WxHttpRequest;
import com.jifenke.weixin.support.WxHttpResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
