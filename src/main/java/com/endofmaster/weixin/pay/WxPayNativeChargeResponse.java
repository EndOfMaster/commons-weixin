package com.endofmaster.weixin.pay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ZM.Wang
 */

public class WxPayNativeChargeResponse extends WxPayResponse {

    @JacksonXmlProperty(localName = "return_code")
    private String returnCode;

    @JacksonXmlProperty(localName = "return_msg")
    private String returnMsg;

    @JacksonXmlProperty(localName = "result_code")
    private String resultCode;

    @JacksonXmlProperty(localName = "err_code")
    private String errCode;

    @JacksonXmlProperty(localName = "err_code_des")
    private String errCodeDes;

    @JacksonXmlProperty(localName = "code_url")
    private String codeUrl;

    @SuppressWarnings("WeakerAccess")
    public boolean isReturnSuccessful() {
        return StringUtils.equals("SUCCESS", returnCode);
    }

    public boolean isResultSuccessful() {
        return StringUtils.equals("SUCCESS", resultCode);
    }

    public String getErrorMsg() {
        if (!isReturnSuccessful())
            return returnCode + ":" + returnMsg;
        return errCode + ":" + errCodeDes;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public String getCodeUrl() {
        return codeUrl;
    }
}
