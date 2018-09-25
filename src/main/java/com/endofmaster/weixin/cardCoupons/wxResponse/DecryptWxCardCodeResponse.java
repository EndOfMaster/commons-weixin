package com.endofmaster.weixin.cardCoupons.wxResponse;

import com.endofmaster.weixin.support.WxResponse;

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
