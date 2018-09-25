package com.jifenke.weixin.cardCoupons.wxResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class WxCardConsumeResponse extends WxResponse{

    private Card card;

    @JsonProperty("openid")
    private String openId;

    static class Card {
        @JsonProperty("card_id")
        String cardId;
    }

    public Card getCard() {
        return card;
    }

    public WxCardConsumeResponse setCard(Card card) {
        this.card = card;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public WxCardConsumeResponse setOpenId(String openId) {
        this.openId = openId;
        return this;
    }
}
