package com.endofmaster.weixin.pay;


import com.endofmaster.weixin.WxException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ZM.Wang
 */
public class WxpayChargeQueryResponse extends WxPayResponse {

    @JacksonXmlProperty(localName = "trade_state")
    private String tradeState;

    @JacksonXmlProperty(localName = "total_fee")
    private String totalFee;

    @JacksonXmlProperty(localName = "transaction_id")
    private String transactionId;

    @JacksonXmlProperty(localName = "out_trade_no")
    private String outTradeNo;

    public boolean getChargeStatus() {
        if (!isResultSuccessful())
            throw new WxException(getErrorMsg());
        return StringUtils.equals("SUCCESS", tradeState);
    }

    public String getTradeState() {
        return tradeState;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }
}
