package com.endofmaster.weixin.message.received.msg;

/**
 * @author YQ.Huang
 */
public interface WxEventTypes {
    String LOCATION = "LOCATION";                                       //位置消息
    String SUBSCRIBE = "subscribe";                                     //关注消息，也可能是带参二维码关注
    String UNSUBSCRIBE = "unsubscribe";                                 //取消关注消息
    String SCAN = "SCAN";                                               //已关注后的带参二维码
    String CLICK = "CLICK";                                             //自定义菜单点击事件
    String VIEW = "VIEW";                                               //自定义菜单连接跳转事件

    String POI_CHECK_NOTIFY = "poi_check_notify";                       //门店审核事件推送
    String CARD_MERCHANT_CHECK_RESULT = "card_merchant_check_result";   //子商户审核事件推送
    String CARD_PASS_CHECK = "card_pass_check";                         //卡券审核通过事件
    String CARD_NOT_PASS_CHECK = "card_not_pass_check";                 //卡券审核未通过事件
    String USER_GET_CARD = "user_get_card";                             //卡券领取事件
    String USER_GIFTING_CARD = "user_gifting_card";                     //卡券转赠事件
    String USER_DEL_CARD = "user_del_card";                             //卡券删除事件
    String USER_CONSUME_CARD = "user_consume_card";                     //卡券核销事件
}
