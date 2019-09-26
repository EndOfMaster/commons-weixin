package com.endofmaster.weixin.pay;

import com.endofmaster.commons.util.sign.Md5SignUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.weixin.WxServerException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import static com.endofmaster.weixin.Constant.CHARSET;


/**
 * @author YQ.Huang
 * @update ZM.Wang
 */
public class WxpayJsapiChargeResponse extends WxPayResponse {

    @JacksonXmlProperty(localName = "prepay_id")
    private String prepayId;

    public Map<String, String> buildCredentials(String appId, String key) {
        if (!isResultSuccessful()) {
            throw new WxServerException(getErrorMsg());
        }
        Map<String, String> credentials = new HashMap<>();
        credentials.put("appId", appId);
        credentials.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
        credentials.put("nonceStr", RandomStringUtils.randomAlphanumeric(32));
        credentials.put("package", "prepay_id=" + prepayId);
        credentials.put("signType", "MD5");
        String preSignString = PresignUtils.createLinkString(credentials, true);
        String sign;
        try {
            sign = Md5SignUtils.sign(preSignString, "&key=" + key, CHARSET).toUpperCase();
        } catch (SignatureException e) {
            throw new WxServerException(e);
        }
        credentials.put("paySign", sign);
        return credentials;
    }

    public String getPrepayId() {
        return prepayId;
    }
}
