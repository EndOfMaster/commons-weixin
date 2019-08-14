package com.endofmaster.weixin.pay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ZM.Wang
 */
@SuppressWarnings("WeakerAccess")
@JacksonXmlRootElement(localName = "xml")
public abstract class WxPayResponse {

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

    private boolean isReturnSuccessful() {
        return StringUtils.equals("SUCCESS", returnCode);
    }

    public boolean isResultSuccessful() {
        if (isReturnSuccessful()) {
            return StringUtils.equals("SUCCESS", resultCode);
        }
        return false;
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
}
