package com.jifenke.weixin.menu;


import com.jifenke.weixin.WxApi;
import com.jifenke.weixin.support.WxHttpClient;
import com.jifenke.weixin.support.WxHttpRequest;
import com.jifenke.weixin.support.WxHttpResponse;
import com.jifenke.weixin.support.WxResponse;

/**
 * @author YQ.Huang
 */
public class WxMenuApi extends WxApi {

    public WxMenuApi(WxHttpClient client) {
        super(client);
    }

    public void createMenu(WxMenu menu, String accessToken) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken)
                .withMethod("POST")
                .withArg("button", menu.getButtons());
        WxHttpResponse response = client.execute(request);
        response.parse(WxResponse.class);
    }

    public void deleteMenu(String accessToken) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/menu/delete")
                .withMethod("GET")
                .setArg("access_token", accessToken);
        WxHttpResponse response = client.execute(request);
        response.parse(WxResponse.class);
    }

}
