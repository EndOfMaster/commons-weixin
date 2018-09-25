package com.endofmaster.weixin.menu;

import com.endofmaster.weixin.support.WxHttpClient;
import org.junit.Test;

/**
 * @author ZM.Wang
 */
public class WxMenuApiTest {
    private static final String accessToken = "zGML1ZK9QGl7Q-m72BhYwoDsuoFjlvzzAfdpugIOix2fFkvTbTs32eH_caKum9q2VpDc0tWkywUOn2e9tveI4GD-o1mK8aXb4bi5RLbGS9SXYzEBf9vP3j6zBQcVKWKtMKHaABACAX";
    private static final WxHttpClient CLIENT = new WxHttpClient(null, null);
    private WxMenuApi menuApi = new WxMenuApi(CLIENT);

    @Test
    public void createMenu() throws Exception {
        WxMenu menu = new WxMenu()
                .addButton(new WxMenuButton("扫码交易")
                        .addViewButton("交易记录", "http://www.lepluspay.com/wx/tradeList")
                        .addViewButton("每日账单", "http://www.lepluspay.com/wx/financialList"))
                .addButton(new WxViewButton("团购验证", "http://www.lepluspay.com/wx/groupon"))
                .addButton(new WxMenuButton("商户中心")
                        .addViewButton("收款二维码", "http://www.lepluspay.com/wx/qrcode")
                        .addViewButton("商户中心", "http://www.lepluspay.com/wx/merchantCenter"));
        menuApi.createMenu(menu, accessToken);
    }

    @Test
    public void deleteMenu() throws Exception {
        menuApi.deleteMenu(accessToken);
    }

}