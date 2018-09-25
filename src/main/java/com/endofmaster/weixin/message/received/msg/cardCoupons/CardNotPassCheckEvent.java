package com.endofmaster.weixin.message.received.msg.cardCoupons;

import com.endofmaster.weixin.message.received.msg.WxEvent;
import com.endofmaster.weixin.message.received.msg.WxEventTypes;

/**
 * @author ZM.Wang
 * 卡券审核未通过事件
 */
public class CardNotPassCheckEvent extends WxEvent {
    public final String cardId;             //卡券id
    public final String refuseReason;       //审核不通过原因

    public CardNotPassCheckEvent(String toUserName, String fromUserName, String createTime, String cardId, String refuseReason) {
        super(toUserName, fromUserName, createTime, WxEventTypes.CARD_NOT_PASS_CHECK);
        this.cardId = cardId;
        this.refuseReason = refuseReason;
    }
}
