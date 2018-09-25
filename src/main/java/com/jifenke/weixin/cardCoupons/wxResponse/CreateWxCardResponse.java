package com.jifenke.weixin.cardCoupons.wxResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.support.WxResponse;

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
