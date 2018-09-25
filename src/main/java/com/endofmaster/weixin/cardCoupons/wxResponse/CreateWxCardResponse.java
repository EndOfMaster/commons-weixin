package com.endofmaster.weixin.cardCoupons.wxResponse;

import com.endofmaster.weixin.support.WxResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public class CreateWxCardResponse extends WxResponse {

    @JsonProperty("card_id")
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public CreateWxCardResponse setCardId(String cardId) {
        this.cardId = cardId;
        return this;
    }
}
