package com.jifenke.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.cardCoupons.basic.WxCardType;

/**
 * @author ZM.Wang
 * 代金券
 */
public class WxCashCardInfo extends AbstractWxCardInfo {

    private Cash cash;

    static class Cash extends AbstractInnerWxCardInfo {
        @JsonProperty("least_cost")
        int leastCost;              //代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。

        @JsonProperty("reduce_cost")
        int reduceCost;             //代金券专用，表示减免金额。（单位为分）
    }

    public WxCashCardInfo(WxCardBaseInfo baseInfo, WxCardAdvancedInfo advancedInfo, int leastCost, int reduceCost) {
        this.cash = new Cash();
        this.cash.baseInfo = baseInfo;
        this.cash.advancedInfo = advancedInfo;
        this.cash.leastCost = leastCost;
        this.cash.reduceCost = reduceCost;
        this.type = WxCardType.CASH;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }
}
