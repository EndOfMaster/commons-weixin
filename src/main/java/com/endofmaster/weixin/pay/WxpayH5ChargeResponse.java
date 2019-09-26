package com.endofmaster.weixin.pay;

import com.endofmaster.weixin.WxServerException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author ZM.Wang
 */
public class WxpayH5ChargeResponse extends WxPayResponse {

    @JacksonXmlProperty(localName = "mweb_url")
    private String mwebUrl;

    public String getUrl(){
        if (!isResultSuccessful()){
            throw new WxServerException(getErrorMsg());
        }
        return mwebUrl;
    }

    public String getMwebUrl() {
        return mwebUrl;
    }
}
