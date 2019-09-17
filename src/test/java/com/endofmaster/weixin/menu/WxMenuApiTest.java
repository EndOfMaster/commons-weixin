package com.endofmaster.weixin.menu;

import com.endofmaster.weixin.support.WxHttpClient;
import org.junit.Test;

/**
 * @author ZM.Wang
 */
public class WxMenuApiTest {
    private static final String accessToken = "25_zzePH8XAfc0HIN2UqYciFkUhpBgkVzOARj5eNDEukVTh23HTjuDEVvKKLdXZlAHTGJLmCZ1gw_UxE8w1kX66saFxYs-dRiMWD3A5CZn6RjNp8i9Mwat3uygu8tm7HrgmYe3oJXQFQ1du4vJkCOLhADAXJG";
    private static final WxHttpClient CLIENT = new WxHttpClient(null, null);
    private WxMenuApi menuApi = new WxMenuApi(CLIENT);

    @Test
    public void createMenu() {
        WxMenu menu = new WxMenu()
                .addButton(new WxViewButton("首页","https://www.wejuai.com"))
                .addButton(new WxViewButton("个人中心", "https://user.wejuai.com"));
        menuApi.createMenu(menu, accessToken);
    }

    @Test
    public void deleteMenu() throws Exception {
        menuApi.deleteMenu(accessToken);
    }

}