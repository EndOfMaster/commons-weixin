package com.jifenke.weixin.cardCoupons.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public class CreateCardQrCodeRequest implements WxRequest {

    private String actionName = "QR_CARD";
    private Integer expireSeconds;
    private ActionInfo actionInfo;

    public CreateCardQrCodeRequest(Integer expireSeconds, String cardId, String outerStr) {
        this.expireSeconds = expireSeconds;
        this.actionInfo = new ActionInfo();
        this.actionInfo.card = new Card();
        this.actionInfo.card.cardId = cardId;
        this.actionInfo.card.outerStr = outerStr;
    }

    CreateCardQrCodeRequest() {
    }

    public static class Card {
        @JsonProperty("card_id")
        String cardId;
        String code;
        @JsonProperty("openid")
        String openId;
        @JsonProperty("is_unique_code")
        Boolean isUniqueCode;
        @JsonProperty("outer_id")
        Integer outerId;
        @JsonProperty("outer_str")
        String outerStr;

        public String getCardId() {
            return cardId;
        }

        public String getCode() {
            return code;
        }

        public String getOpenId() {
            return openId;
        }

        public Boolean getUniqueCode() {
            return isUniqueCode;
        }

        public Integer getOuterId() {
            return outerId;
        }

        public String getOuterStr() {
            return outerStr;
        }
    }

    public static class ActionInfo {
       Card card;

        public Card getCard() {
            return card;
        }
    }

    public String getActionName() {
        return actionName;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public ActionInfo getActionInfo() {
        return actionInfo;
    }
}
