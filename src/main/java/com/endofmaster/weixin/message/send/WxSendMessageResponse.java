package com.endofmaster.weixin.message.send;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class WxSendMessageResponse extends WxResponse {

    @JsonProperty("msgid")
    private String msgId;

    public String getMsgId() {
        return msgId;
    }
}
