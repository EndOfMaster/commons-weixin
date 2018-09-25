package com.jifenke.weixin.support;

import com.jifenke.commons.util.sign.PresignUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

public class WxCardUtils {

    public static String cardExtSignature(String apiTicket, String cardId, String timestamp, String nonceStr, String code, String openId){
        Map<String, String> params = new HashMap<>();
        params.put("apiTicket", apiTicket);
        params.put("cardId", cardId);
        params.put("timestamp", timestamp);
        params.put("nonce_str", nonceStr);
        params.put("code", code);
        params.put("openid", openId);
        String orderStr = PresignUtils.createLinkStringOnlyValueByValue(params);
        return DigestUtils.sha1Hex(orderStr);
    }

    public static String cardSign(String apiTicket, String appId, String locationId, String timestamp, String nonceStr, String cardId, String cardType){
        Map<String, String> params = new HashMap<>();
        params.put("apiTicket", apiTicket);
        params.put("appid", appId);
        params.put("locationId", locationId);
        params.put("timestamp", timestamp);
        params.put("nonceStr", nonceStr);
        params.put("cardId", cardId);
        params.put("cardType", cardType);
        return DigestUtils.sha1Hex(PresignUtils.createLinkStringOnlyValueByValue(params));
    }

}
