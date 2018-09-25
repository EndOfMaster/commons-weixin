package com.jifenke.weixin.cardCoupons.wxResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.cardCoupons.basic.WxCardCodeStatus;
import com.jifenke.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class QueryWxCardCodeResponse extends WxResponse {

    private Card card;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("can_consume")
    private boolean canConsume;     //是否可用

    @JsonProperty("user_card_status")
    private WxCardCodeStatus status;

    public static class Card {
        @JsonProperty("card_id")
        public String cardId;
        @JsonProperty("begin_time")
        public long beginTime;
        @JsonProperty("end_time")
        public long endTime;
    }

    public long getEndTime() {
        return card.endTime;
    }

    public Card getCard() {
        return card;
    }

    public boolean getCanConsume() {
        return canConsume;
    }

    public QueryWxCardCodeResponse setCard(Card card) {
        this.card = card;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public QueryWxCardCodeResponse setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public boolean isCanConsume() {
        return canConsume;
    }

    public QueryWxCardCodeResponse setCanConsume(boolean canConsume) {
        this.canConsume = canConsume;
        return this;
    }

    public WxCardCodeStatus getStatus() {
        return status;
    }

    public QueryWxCardCodeResponse setStatus(WxCardCodeStatus status) {
        this.status = status;
        return this;
    }
}
