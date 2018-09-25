package com.endofmaster.weixin.message.received.msg.cardCoupons;

import com.endofmaster.weixin.message.received.msg.WxEvent;
import com.endofmaster.weixin.message.received.msg.WxEventTypes;

/**
 * @author ZM.Wang
 * 卡券核销事件
 */
public class UserConsumeCardEvent extends WxEvent {
    public final String cardId;             //卡券id
    public final String userCardCode;       //卡券Code码。
    public final String consumeSource;      //核销来源。支持开发者统计API核销（FROM_API）、公众平台核销（FROM_MP）、卡券商户助手核销（FROM_MOBILE_HELPER）（核销员微信号）
    public final String locationName;       //门店名称，当前卡券核销的门店名称（只有通过自助核销和买单核销时才会出现该字段）
    public final String staffOpenId;        //核销该卡券核销员的openid（只有通过卡券商户助手核销时才会出现）
    public final String verifyCode;         //自助核销时，用户输入的验证码
    public final String remarkAmount;       //自助核销时，用户输入的备注金额
    public final String outerStr;           //开发者发起核销时传入的自定义参数，用于进行核销渠道统计

    public UserConsumeCardEvent(String toUserName, String fromUserName, String createTime, String cardId, String userCardCode, String consumeSource, String locationName, String staffOpenId, String verifyCode, String remarkAmount, String outerStr) {
        super(toUserName, fromUserName, createTime, WxEventTypes.USER_CONSUME_CARD);
        this.cardId = cardId;
        this.userCardCode = userCardCode;
        this.consumeSource = consumeSource;
        this.locationName = locationName;
        this.staffOpenId = staffOpenId;
        this.verifyCode = verifyCode;
        this.remarkAmount = remarkAmount;
        this.outerStr = outerStr;
    }
}
