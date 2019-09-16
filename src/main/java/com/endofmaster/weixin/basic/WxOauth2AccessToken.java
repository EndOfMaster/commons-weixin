package com.endofmaster.weixin.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.support.WxResponse;

/**
 * @author YQ.Huang
 */
public class WxOauth2AccessToken extends WxResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("unionid")
    private String unionId;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("session_key")
    private String sessionKey;

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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionId() {
        return unionId;
    }

    public WxOauth2AccessToken setUnionId(String unionId) {
        this.unionId = unionId;
        return this;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public WxOauth2AccessToken setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }
}
