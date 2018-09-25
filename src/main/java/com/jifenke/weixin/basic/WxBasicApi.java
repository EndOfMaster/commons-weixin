package com.jifenke.weixin.basic;


import com.jifenke.weixin.WxException;
import com.jifenke.weixin.support.WxHttpClient;
import com.jifenke.weixin.support.WxHttpRequest;
import com.jifenke.weixin.support.WxHttpResponse;
import com.jifenke.weixin.support.WxUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author YQ.Huang
 */
public class WxBasicApi {
    private final String appId;
    private final String appSecret;
    private final WxHttpClient client;

    public WxBasicApi(String appId, String appSecret, WxHttpClient client) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.client = client;
    }

    public String getBaseAuthorizeUrl(String redirectUrl, String state) throws UnsupportedEncodingException {
        return WxUtils.getAuthorizeUrl(appId, redirectUrl, "snsapi_base", state);
    }

    public String getUserAuthorizeUrl(String redirectUrl, String state) throws UnsupportedEncodingException {
        return WxUtils.getAuthorizeUrl(appId, redirectUrl, "snsapi_userinfo", state);
    }

    public String getLoginAuthorizeUrl(String redirectUrl, String state) throws UnsupportedEncodingException {
        return WxUtils.getAuthorizeUrl(appId, redirectUrl, "snsapi_login", state);
    }

    public WxAccessToken getAccessToken() throws WxException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/token")
                .setArg("grant_type", "client_credential")
                .setArg("appid", appId)
                .setArg("secret", appSecret);
        WxHttpResponse response = client.execute(request);
        return response.parse(WxAccessToken.class);
    }

    public WxAuthUserInfo getUserInfo(String openId, String accessToken) throws WxException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/user/info")
                .setArg("access_token", accessToken)
                .setArg("openid", openId)
                .setArg("lang", "zh_CN");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxAuthUserInfo.class);
    }

    public WxOauth2AccessToken getOauth2AccessToken(String code, String appSecret) throws WxException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/sns/oauth2/access_token")
                .setArg("appid", appId)
                .setArg("secret", appSecret)
                .setArg("code", code)
                .setArg("grant_type", "authorization_code");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxOauth2AccessToken.class);
    }

    public WxAuthUserInfo getOauth2UserInfo(String openId, String oauth2AccessToken) throws WxException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/sns/userinfo")
                .setArg("access_token", oauth2AccessToken)
                .setArg("openid", openId)
                .setArg("lang", "zh_CN");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxAuthUserInfo.class);
    }

    public WxIpList getIpList(String accessToken) throws WxException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/getcallbackip")
                .setArg("access_token", accessToken);
        WxHttpResponse response = client.execute(request);
        return response.parse(WxIpList.class);
    }

    public String getAppId() {
        return appId;
    }
}
