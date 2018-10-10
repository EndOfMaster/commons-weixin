package com.endofmaster.weixin.message.send;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class WxSendMessageResponse extends WxResponse {

    @JsonProperty("msgid")
    private String msgId;

    @JsonProperty("template_id")
    private String templateId;

    public String getMsgId() {
        return msgId;
    }

    public String getTemplateId() {
        return templateId;
    }
}
