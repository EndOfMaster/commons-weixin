package com.endofmaster.weixin.pay;

import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.sign.Md5SignUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.weixin.WxException;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.findParam;
import static com.endofmaster.commons.util.validate.ParamUtils.parseXml;
import static com.endofmaster.weixin.Constant.CHARSET;

/**
 * @author YQ.Huang
 */
public abstract class WxpayCallback {

    protected Map<String, String> params;

    protected WxpayCallback(String key, HttpServletRequest request) {
        try {
            String xml = StreamUtils.copyToString(request.getInputStream(), Charsets.toCharset(CHARSET));
            this.params = parseXml(xml);
            validateSign(key);
        } catch (DocumentException | IOException | SignatureException e) {
            throw new WxException(e);
        }
    }

    public abstract Map<String, String> buildResultParams();

    public void ack(HttpServletResponse response) {
        String message = "<xml>" +
                "<return_code><![CDATA[SUCCESS]]></return_code>" +
                "<return_msg><![CDATA[OK]]></return_msg>" +
                "</xml>";
        AckUtils.ack(response, message);
    }

    private void validateSign(String key) throws SignatureException {
        String actual = findParam(params, "sign");
        params.remove("sign");
        String preSignString = PresignUtils.createLinkString(params, true);
        String sign = Md5SignUtils.sign(preSignString, "&key=" + key, CHARSET).toUpperCase();
        if (!StringUtils.equals(sign, actual)) {
            throw new WxException("sign不正确");
        }
    }

}
