package com.endofmaster.weixin.cardCoupons.wxResponse;

import com.endofmaster.weixin.support.WxResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public class CreateCardQrCodeResponse extends WxResponse {
    private String ticket;
    @JsonProperty("expire_seconds")
    private Integer expireSeconds;
    private String url;
    @JsonProperty("show_qrcode_url")
    private String showQrcodeUrl;

    public String getTicket() {
        return ticket;
    }

    public CreateCardQrCodeResponse setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public CreateCardQrCodeResponse setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CreateCardQrCodeResponse setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getShowQrcodeUrl() {
        return showQrcodeUrl;
    }

    public CreateCardQrCodeResponse setShowQrcodeUrl(String showQrcodeUrl) {
        this.showQrcodeUrl = showQrcodeUrl;
        return this;
    }

    @Override
    public String toString() {
        return "CreateCardQrCodeResponse{" +
                "ticket='" + ticket + '\'' +
                ", expireSeconds=" + expireSeconds +
                ", url='" + url + '\'' +
                ", showQrcodeUrl='" + showQrcodeUrl + '\'' +
                '}';
    }
}
