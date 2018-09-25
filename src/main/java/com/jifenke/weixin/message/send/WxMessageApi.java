package com.jifenke.weixin.message.send;


import com.jifenke.weixin.WxApi;
import com.jifenke.weixin.WxException;
import com.jifenke.weixin.support.WxHttpClient;
import com.jifenke.weixin.support.WxHttpRequest;
import com.jifenke.weixin.support.WxHttpResponse;

/**
 * @author YQ.Huang
 */
public class WxMessageApi extends WxApi {

    public WxMessageApi(WxHttpClient client) {
        super(client);
    }

    public WxSendMessageResponse sendTemplateMessage(WxTemplateMsg message, String accessToken) throws WxException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken)
                .setArg("touser", message.getOpenId())
                .setArg("template_id", message.getTemplateId())
                .setArg("url", message.getUrl())
                .setArg("data", message.getData());
        request.withMethod("post");
        WxHttpResponse response = client.execute(request);
        return response.parse(WxSendMessageResponse.class);
    }

    public void sendWxCard(String accessToken){
        WxHttpRequest request=new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken)
                .withMethod("post");
    }

}
