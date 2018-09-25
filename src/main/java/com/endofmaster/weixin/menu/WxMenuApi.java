package com.endofmaster.weixin.menu;


import com.endofmaster.weixin.WxApi;
import com.endofmaster.weixin.support.WxHttpClient;
import com.endofmaster.weixin.support.WxHttpRequest;
import com.endofmaster.weixin.support.WxHttpResponse;
import com.endofmaster.weixin.support.WxResponse;

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
