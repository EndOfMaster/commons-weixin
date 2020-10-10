package com.endofmaster.weixin.pay;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public class WxMerchantPayResponse extends WxPayResponse {

    @JsonProperty("payment_no")
    private String paymentNo;

    public String getPaymentNo() {
        return paymentNo;
    }
}
