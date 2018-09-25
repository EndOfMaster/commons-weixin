package com.jifenke.weixin.cardCoupons;

import com.jifenke.weixin.cardCoupons.basic.WxCardType;

/**
 * @author ZM.Wang
 * 兑换券
 */
public class WxGiftCardInfo extends AbstractWxCardInfo {

    private Gift gift;

    static class Gift extends AbstractInnerWxCardInfo {
        String gift;        //兑换券专用，填写兑换内容的名称。
    }

    public WxGiftCardInfo(WxCardBaseInfo baseInfo, WxCardAdvancedInfo advancedInfo, String gift) {
        this.gift = new Gift();
        this.gift.baseInfo = baseInfo;
        this.gift.advancedInfo = advancedInfo;
        this.gift.gift = gift;
        this.type = WxCardType.GIFT;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }
}
