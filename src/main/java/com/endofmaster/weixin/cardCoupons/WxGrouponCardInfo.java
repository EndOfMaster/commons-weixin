package com.endofmaster.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.cardCoupons.basic.WxCardType;

/**
 * @author ZM.Wang
 * 团购券
 */
public class WxGrouponCardInfo extends AbstractWxCardInfo {

    private Groupon groupon;

    static class Groupon extends AbstractInnerWxCardInfo {
        @JsonProperty("deal_detail")
        String dealDetail;
    }

    public WxGrouponCardInfo(WxCardBaseInfo baseInfo, WxCardAdvancedInfo advancedInfo, String dealDetail) {
        this.groupon = new Groupon();
        this.groupon.baseInfo = baseInfo;
        this.groupon.advancedInfo = advancedInfo;
        this.groupon.dealDetail = dealDetail;
        this.type = WxCardType.GROUPON;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }
}
