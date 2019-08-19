package com.endofmaster.weixin.pay;

import com.endofmaster.commons.util.sign.Md5SignUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.security.SignatureException;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.findParam;
import static com.endofmaster.commons.util.validate.ParamUtils.parseXml;
import static com.endofmaster.weixin.Constant.CHARSET;

/**
 * @author ZM.Wang
 */
public class WxPayApiTest {

    private final WxPayApi wxPayApi;

    public WxPayApiTest() {
        this.wxPayApi = new WxPayApi("*******", "wx94581d0e2c01d1b0", "1550785821");
    }

    @Test
    public void test() {
        String id = RandomStringUtils.randomAlphanumeric(32);
        WxPayNativeChargeRequest request = new WxPayNativeChargeRequest(id, "测试",
                "100", "http://127.0.0.1", id, "127.0.0.1");
        WxPayNativeChargeResponse response = wxPayApi.execute(request);
        System.err.println(response.getCodeUrl());
    }

    @Test
    public void callbackTest() throws DocumentException, SignatureException {
        String xml = "<xml>\n" +
                "<appid><![CDATA[wx94581d0e2c01d1b0]]></appid>\n" +
                "<bank_type><![CDATA[COMM_CREDIT]]></bank_type>\n" +
                "<cash_fee><![CDATA[100]]></cash_fee>\n" +
                "<fee_type><![CDATA[CNY]]></fee_type>\n" +
                "<is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "<mch_id><![CDATA[1550785821]]></mch_id>\n" +
                "<nonce_str><![CDATA[roAzvplhvuEpNog6GbMJGVqv8Y5Xa4Ju]]></nonce_str>\n" +
                "<openid><![CDATA[o7h9S1i9nzmduKEYDdwTA69m9SKw]]></openid>\n" +
                "<out_trade_no><![CDATA[5d5a3bf3c8eb94000184bd10]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[6C1C69A0DBB2DDF1C62A5C636F44ABED]]></sign>\n" +
                "<time_end><![CDATA[20190819140444]]></time_end>\n" +
                "<total_fee>100</total_fee>\n" +
                "<trade_type><![CDATA[NATIVE]]></trade_type>\n" +
                "<transaction_id><![CDATA[4200000353201908192752766184]]></transaction_id>\n" +
                "</xml>";
        String key = "******";
        Map<String, String> params = parseXml(xml);
        System.err.println(params);
        String actual = findParam(params, "sign");
        params.remove("sign");
        String preSignString = PresignUtils.createLinkString(params, true);
        String sign = Md5SignUtils.sign(preSignString, "&key=" + key, CHARSET).toUpperCase();
        System.err.println(actual);
        System.err.println(sign);
    }
}
