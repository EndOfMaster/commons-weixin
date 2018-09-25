package com.endofmaster.weixin.message.received.msg.cardCoupons;

import com.endofmaster.weixin.message.received.msg.WxEvent;
import com.endofmaster.weixin.message.received.msg.WxEventTypes;

/**
 * @author ZM.Wang
 * 卡券审核成功事件
 */
public class CardPassCheckEvent extends WxEvent {
    public final String cardId;         //卡券id
    public final String refuseReason;   //审核不通过原因

    public CardPassCheckEvent(String toUserName, String fromUserName, String createTime, String cardId, String refuseReason) {
        super(toUserName, fromUserName, createTime, WxEventTypes.CARD_PASS_CHECK);
        this.cardId = cardId;
        this.refuseReason = refuseReason;
    }
}
