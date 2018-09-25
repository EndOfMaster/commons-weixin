package com.endofmaster.weixin.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.support.WxResponse;

/**
 * @author YQ.Huang
 */
public class WxAccessToken extends WxResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn; // 单位：秒

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "WeixinAccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                "} " + super.toString();
    }
}
