package com.endofmaster.weixin.message.send;


import com.endofmaster.weixin.WxApi;
import com.endofmaster.weixin.WxException;
import com.endofmaster.weixin.support.WxHttpClient;
import com.endofmaster.weixin.support.WxHttpRequest;
import com.endofmaster.weixin.support.WxHttpResponse;

/**
 * @author YQ.Huang
 */
public class WxMessageApi extends WxApi {

    public WxMessageApi(WxHttpClient client) {
        super(client);
    }

    /** 发送公众号模版消息 */
    public WxSendMessageResponse sendTemplateMessage(WxTemplateMsg message, String accessToken) throws WxException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken)
                .setArg("touser", message.getOpenId())
                .setArg("template_id", message.getTemplateId())
                .setArg("url", message.getUrl())
                .setArg("emphasis_keyword", message.getUrl())
                .setArg("data", message.getData());
        request.withMethod("post");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxSendMessageResponse.class);
    }

    /** 发送小程序模版消息 */
    public WxSendMessageResponse sendWxOpenTemplateMessage(WxTemplateMsg message, String accessToken) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + accessToken)
                .setArg("touser", message.getOpenId())
                .setArg("template_id", message.getTemplateId())
                .setArg("page", message.getUrl())
                .setArg("form_id", message.getFormId())
                .setArg("data", message.getData());
        request.withMethod("post");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxSendMessageResponse.class);
    }

    /** 发送小程序订阅消息 */
    public WxSendMessageResponse sendWxSubscribeMessage(WxTemplateMsg message, String accessToken) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken)
                .setArg("touser", message.getOpenId())
                .setArg("template_id", message.getTemplateId())
                .setArg("page", message.getUrl())
                .setArg("data", message.getData());
        request.withMethod("post");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxSendMessageResponse.class);
    }

    public void sendWxCard(String accessToken) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken)
                .withMethod("post");
    }

}
