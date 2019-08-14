package com.endofmaster.weixin.pay;

import java.util.Collections;
import java.util.Map;

import static com.endofmaster.weixin.Constant.ORDER_QUERY_URL;

/**
 * 微信查询订单
 * 参考文档：
 * https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_2
 *
 * @author YQ.Huang
 */
public class WxpayChargeQueryRequest extends WxPayRequest<WxpayChargeQueryResponse> {

    private final String outTradeNo;

    public WxpayChargeQueryRequest(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }


    @Override
    protected String getUrl() {
        return ORDER_QUERY_URL;
    }

    @Override
    Class<WxpayChargeQueryResponse> responseClass() {
        return WxpayChargeQueryResponse.class;
    }

    @Override
    protected Map<String, String> buildRequestParams() {
        return Collections.singletonMap("out_trade_no", outTradeNo);
    }

}
