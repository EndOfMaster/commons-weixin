package com.endofmaster.weixin.message.received.msg.cardCoupons;

import com.endofmaster.weixin.message.received.msg.WxEvent;
import com.endofmaster.weixin.message.received.msg.WxEventTypes;

/**
 * @author ZM.Wang
 * 门店审核事件推送
 */
public class PoiCheckNotifyEvent extends WxEvent {

    public final String uniqId;  //商户自己内部ID，即字段中的sid
    public final String poiId;   //微信的门店ID，微信内门店唯一标示ID
    public final String result;  //审核结果，成功succ 或失败fail
    public final String msg;     //成功的通知信息，或审核失败的驳回理由

    public PoiCheckNotifyEvent(String toUserName, String fromUserName, String createTime, String uniqId, String poiId, String result, String msg) {
        super(toUserName, fromUserName, createTime, WxEventTypes.POI_CHECK_NOTIFY);
        this.uniqId = uniqId;
        this.poiId = poiId;
        this.result = result;
        this.msg = msg;
    }
}
