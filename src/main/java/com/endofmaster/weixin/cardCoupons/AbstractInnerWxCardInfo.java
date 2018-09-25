package com.endofmaster.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public abstract class AbstractInnerWxCardInfo {
    @JsonProperty("base_info")
    protected WxCardBaseInfo baseInfo;

    @JsonProperty("advanced_info")
    protected WxCardAdvancedInfo advancedInfo;
}
