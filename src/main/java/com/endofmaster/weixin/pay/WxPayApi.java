package com.endofmaster.weixin.pay;

import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.sign.Md5SignUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.weixin.Constant;
import com.endofmaster.weixin.WxClientException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.buildXml;
import static com.endofmaster.weixin.Constant.CHARSET_OBJ;
import static com.endofmaster.weixin.Constant.XML_MAPPER;

/**
 * @author ZM.Wang
 */
public class WxPayApi {

    private final static Logger logger = LoggerFactory.getLogger(WxPayApi.class);

    private final String key; //签名算法需要用到的秘钥
    private final String appId; //公众账号ID
    private final String mchId; //商户ID
    private final HttpClient httpClient;

    protected WxPayApi(String key, String appId, String mchId) {
        this.key = key;
        this.appId = appId;
        this.mchId = mchId;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    public <T extends WxPayResponse> T execute(WxPayRequest<T> request) {
        try {
            Map<String, String> params = request.buildRequestParams();
            params.put("appid", appId);
            params.put("mch_id", mchId);
            params.put("nonce_str", RandomStringUtils.randomAlphanumeric(32));
            sign(params);
            String requestXml = buildXml(params, "xml");
            HttpPost httpPost = new HttpPost(request.getUrl());
            StringEntity requestEntity = new StringEntity(requestXml, Constant.CONTENT_TYPE);
            httpPost.setEntity(requestEntity);
            HttpResponse response = httpClient.execute(httpPost);
            String responseXml = StreamUtils.copyToString(response.getEntity().getContent(), CHARSET_OBJ);
            logger.debug("微信支付接口获取结果：" + responseXml);
            return XML_MAPPER.readValue(responseXml, request.responseClass());
        } catch (IOException | SignatureException e) {
            throw new WxClientException(e);
        }
    }

    private void sign(Map<String, String> params) throws SignatureException {
        String preSignString = PresignUtils.createLinkString(params, true);
        String sign = Md5SignUtils.sign(preSignString, "&key=" + key, Constant.CHARSET).toUpperCase();
        params.put("sign", sign);
    }

}
