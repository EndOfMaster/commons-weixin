package com.endofmaster.weixin.message.received.msg.cardCoupons;

import com.endofmaster.weixin.message.received.msg.WxEvent;
import com.endofmaster.weixin.message.received.msg.WxEventTypes;

/**
 * @author ZM.Wang
 * 卡券转赠事件
 */
public class UserGiftingCardEvent extends WxEvent {
    public final String cardId;                 //卡券id
    public final String friendUserName;         //接收卡券用户的openid
    public final String userCardCode;           //code序列号
    public final boolean isReturnBack;          //是否转赠退回，0代表不是，1代表是。
    public final boolean isChatRoom;            //是否是群转赠

    public UserGiftingCardEvent(String toUserName, String fromUserName, String createTime, String cardId, String friendUserName, String userCardCode, String isReturnBack, String isChatRoom) {
        super(toUserName, fromUserName, createTime, WxEventTypes.USER_GIFTING_CARD);
        this.cardId = cardId;
        this.friendUserName = friendUserName;
        this.userCardCode = userCardCode;
        this.isReturnBack = "1".equals(isReturnBack);
        this.isChatRoom = "1".equals(isChatRoom);
    }
}
