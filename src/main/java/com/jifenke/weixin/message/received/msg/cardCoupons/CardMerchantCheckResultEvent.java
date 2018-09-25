package com.jifenke.weixin.message.received.msg.cardCoupons;

import com.jifenke.weixin.message.received.msg.WxEvent;
import com.jifenke.weixin.message.received.msg.WxEventTypes;

/**
 * @author ZM.Wang
 * 子商户审核事件推送
 */
public class CardMerchantCheckResultEvent extends WxEvent {

    public final String merchantId;     //子商户id
    public final boolean isPass;        //是否通过：1是通过
    public final String reason;         //驳回原因

    public CardMerchantCheckResultEvent(String toUserName, String fromUserName, String createTime, String merchantId, String isPass, String reason) {
        super(toUserName, fromUserName, createTime, WxEventTypes.CARD_MERCHANT_CHECK_RESULT);
        this.merchantId = merchantId;
        this.isPass = "1".equals(isPass);
        this.reason = reason;
    }
}
