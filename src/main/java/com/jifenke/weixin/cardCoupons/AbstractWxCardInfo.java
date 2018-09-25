package com.jifenke.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.cardCoupons.basic.WxCardType;

/**
 * @author ZM.Wang
 */
public abstract class AbstractWxCardInfo {

    @JsonProperty("card_type")
    protected WxCardType type;

    public WxCardType getType() {
        return type;
    }

    public void setType(WxCardType type) {
        this.type = type;
    }
}
