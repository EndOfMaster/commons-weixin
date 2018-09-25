package com.endofmaster.weixin.cardCoupons.wxResponse;

import com.endofmaster.weixin.support.WxResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WxCardApiTicket extends WxResponse {
    /**
     * api_ticket，卡券接口中签名所需凭证
     */
    private String ticket;

    /**
     * 有效时间
     */
    @JsonProperty("expires_in")
    private String expiresIn;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
