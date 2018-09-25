package com.jifenke.weixin.cardCoupons.wxResponse;

import com.jifenke.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class DecryptWxCardCodeResponse extends WxResponse {

    private String code;

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "DecryptWxCardCodeResponse{" +
                "code='" + code + '\'' +
                '}';
    }
}
