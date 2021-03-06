package com.endofmaster.weixin.pay;

import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.sign.Md5SignUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.weixin.WxException;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final static Logger logger = LoggerFactory.getLogger(WxpayCallback.class);

    protected Map<String, String> params;

    protected WxpayCallback(String key, HttpServletRequest request) {
        try {
            String xml = StreamUtils.copyToString(request.getInputStream(), Charsets.toCharset(CHARSET));
            logger.debug("微信通知收到的消息：" + xml);
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
        logger.debug("验签传入Key：" + key);
        String actual = findParam(params, "sign");
        params.remove("sign");
        String preSignString = PresignUtils.createLinkString(params, true);
        String sign = Md5SignUtils.sign(preSignString, "&key=" + key, CHARSET).toUpperCase();
        if (!StringUtils.equals(sign, actual)) {
            logger.debug("微信验签不正确，传入值：{}，计算值:{}", actual, sign);
            throw new WxException("sign不正确");
        }
    }

}
