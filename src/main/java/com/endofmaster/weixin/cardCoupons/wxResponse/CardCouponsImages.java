package com.endofmaster.weixin.cardCoupons.wxResponse;

import com.endofmaster.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class CardCouponsImages extends WxResponse {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CardCouponsImages{" +
                "url='" + url + '\'' + super.toString() +
                '}';
    }
}
