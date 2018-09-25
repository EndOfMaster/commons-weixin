package com.endofmaster.weixin.message.received.msg.cardCoupons;

import com.endofmaster.weixin.message.received.msg.WxEvent;
import com.endofmaster.weixin.message.received.msg.WxEventTypes;

/**
 * @author ZM.Wang
 * 卡券删除事件
 */
public class UserDelCardEvent extends WxEvent {
    public final String cardId;         //卡券ID
    public final String userCardCode;   //code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。

    public UserDelCardEvent(String toUserName, String fromUserName, String createTime, String cardId, String userCardCode) {
        super(toUserName, fromUserName, createTime, WxEventTypes.USER_DEL_CARD);
        this.cardId = cardId;
        this.userCardCode = userCardCode;
    }
}
