package com.endofmaster.weixin.cardCoupons;

import com.endofmaster.weixin.cardCoupons.basic.WxCardType;

/**
 * @author ZM.Wang
 * 折扣券
 */
public class WxDiscountCardInfo extends AbstractWxCardInfo {

    private Discount discount;

    static class Discount extends AbstractInnerWxCardInfo {
        int discount;       //折扣券专用，表示打折额度（百分比）。填30就是七折。
    }

    public WxDiscountCardInfo(WxCardBaseInfo baseInfo, WxCardAdvancedInfo advancedInfo, int discount) {
        this.discount = new Discount();
        this.discount.baseInfo = baseInfo;
        this.discount.advancedInfo = advancedInfo;
        this.discount.discount = discount;
        this.type = WxCardType.DISCOUNT;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
