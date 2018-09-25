package com.endofmaster.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.cardCoupons.basic.WxCardType;

/**
 * @author ZM.Wang
 * 优惠券
 */
public class WxGeneralCouponCardInfo extends AbstractWxCardInfo {

    @JsonProperty("general_coupon")
    private GeneraCoupon generaCoupon;

    static class GeneraCoupon extends AbstractInnerWxCardInfo {
        @JsonProperty("default_detail")
        String defaultDetail;       //优惠券专用，填写优惠详情。
    }

    public WxGeneralCouponCardInfo(WxCardBaseInfo baseInfo, WxCardAdvancedInfo advancedInfo, String dealDetail) {
        this.generaCoupon = new GeneraCoupon();
        this.generaCoupon.baseInfo = baseInfo;
        this.generaCoupon.advancedInfo = advancedInfo;
        this.generaCoupon.defaultDetail = dealDetail;
        this.type = WxCardType.GENERAL_COUPON;
    }

    public GeneraCoupon getGeneraCoupon() {
        return generaCoupon;
    }

    public void setGeneraCoupon(GeneraCoupon generaCoupon) {
        this.generaCoupon = generaCoupon;
    }
}
