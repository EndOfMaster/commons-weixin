package com.endofmaster.weixin.pay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ZM.Wang
 */

public class WxPayNativeChargeResponse extends WxPayResponse {

    @JacksonXmlProperty(localName = "code_url")
    private String codeUrl;

    public String getCodeUrl() {
        return codeUrl;
    }
}
