package com.endofmaster.weixin.jssdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class WxJsapiTicket extends WxResponse {

    private String ticket;

    @JsonProperty("expires_in")
    private int expiresIn;

    public String getTicket() {
        return ticket;
    }

    public int getExpiresIn() {
        return expiresIn;
    }
}
