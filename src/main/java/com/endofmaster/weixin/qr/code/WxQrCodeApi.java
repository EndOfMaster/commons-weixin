package com.endofmaster.weixin.qr.code;

import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.weixin.WxException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.endofmaster.weixin.Constant.CHARSET_OBJ;
import static com.endofmaster.weixin.Constant.MAPPER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author ZM.Wang
 */
public class WxQrCodeApi {
    private final static Logger logger = LoggerFactory.getLogger(WxQrCodeApi.class);

    private final HttpClient httpClient;

    public WxQrCodeApi() {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        this.httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    public String getTemporaryQrCodeUrl(long time, String accessToken, WxQrCodeType type) {
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
            Map<String, Object> params = new HashMap<>();
            params.put("expire_seconds", time);
            params.put("action_name", type.getParam());
            String json = MAPPER.writeValueAsString(params);
            Map result = execute(url, json);
            return (String) result.get("url");
        } catch (IOException e) {
            throw new WxException(e);
        }
    }

    private Map execute(String url, String json) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity requestEntity = new StringEntity(json, APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        String responseJson = StreamUtils.copyToString(response.getEntity().getContent(), CHARSET_OBJ);
        logger.debug("微信支付接口获取结果：" + responseJson);
        return MAPPER.readValue(responseJson, Map.class);
    }
}
