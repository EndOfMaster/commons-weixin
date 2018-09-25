package com.jifenke.weixin.message.received.msg.cardCoupons;

import com.jifenke.weixin.message.received.msg.WxEvent;
import com.jifenke.weixin.message.received.msg.WxEventTypes;

/**
 * @author ZM.Wang
 * 领取事件推送
 */
public class UserGetCardEvent extends WxEvent {
    public final String cardId;                 //卡券id
    public final boolean isGiveByFriend;        //是否为转赠领取
    public final String userCardCode;           //新code序列号
    public final String friendUserName;         //当IsGiveByFriend为是有该字段，表示发起转赠用户的openid
    public final String oldUserCardCode;        //之前的code序列号
    public final String outerStr;               //领取场景值，用于领取渠道数据统计。可在生成二维码接口及添加Addcard接口中自定义该字段的字符串值。
    public final boolean isRestoreMemberCard;   //用户删除会员卡后可重新找回，当用户本次操作为找回时，该值为1，否则为0

    public UserGetCardEvent(String toUserName, String fromUserName, String createTime, String cardId, String isGiveByFriend, String userCardCode, String friendUserName, String oldUserCardCode, String outerStr, String isRestoreMemberCard) {
        super(toUserName, fromUserName, createTime, WxEventTypes.USER_GET_CARD);
        this.cardId = cardId;
        this.isGiveByFriend = "1".equals(isGiveByFriend);
        this.userCardCode = userCardCode;
        this.friendUserName = friendUserName;
        this.oldUserCardCode = oldUserCardCode;
        this.outerStr = outerStr;
        this.isRestoreMemberCard = "1".equals(isRestoreMemberCard);
    }
}
