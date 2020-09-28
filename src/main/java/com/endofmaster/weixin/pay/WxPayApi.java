package com.endofmaster.weixin.pay;

import com.endofmaster.commons.util.StreamUtils;
import com.endofmaster.commons.util.p12cert.P12Cert;
import com.endofmaster.commons.util.p12cert.P12CertUtils;
import com.endofmaster.commons.util.sign.Md5SignUtils;
import com.endofmaster.commons.util.sign.PresignUtils;
import com.endofmaster.weixin.Constant;
import com.endofmaster.weixin.WxClientException;
import com.endofmaster.weixin.WxException;
import com.endofmaster.weixin.WxServerException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.buildXml;
import static com.endofmaster.weixin.Constant.CHARSET_OBJ;
import static com.endofmaster.weixin.Constant.XML_MAPPER;

/**
 * @author ZM.Wang
 */
public class WxPayApi {

    private final static Logger logger = LoggerFactory.getLogger(WxPayApi.class);

    private String key; //签名算法需要用到的秘钥
    private String appId; //公众账号ID
    private String mchId; //商户ID
    private HttpClient httpClient;

    public WxPayApi(String key, String appId, String mchId) {
        this(key, appId, mchId, null, null);
    }

    public WxPayApi(String key, String appId, String mchId, InputStream cert, String certPassword) {
        this.key = key;
        this.appId = appId;
        this.mchId = mchId;
        try {
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            if (cert != null) {
                SSLConnectionSocketFactory socketFactory = buildSsl(cert, certPassword);
                httpClientBuilder.setSSLSocketFactory(socketFactory);
            }
            this.httpClient = httpClientBuilder.setDefaultRequestConfig(requestConfig).build();
        } catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException | KeyManagementException e) {
            throw new WxServerException(e);
        }
    }

    public <T extends WxPayResponse> T execute(WxPayRequest<T> request) {
        try {
            Map<String, String> params = request.buildRequestParams();
            if (request instanceof WxMerchantPayRequest) {
                params.put("mch_appid", appId);
                params.put("mchid", mchId);
            } else {
                params.put("appid", appId);
                params.put("mch_id", mchId);
            }
            params.put("nonce_str", RandomStringUtils.randomAlphanumeric(32));
            sign(params);
            String requestXml = buildXml(params, "xml");
            logger.debug("微信请求xml：" + requestXml);
            HttpPost httpPost = new HttpPost(request.getUrl());
            StringEntity requestEntity = new StringEntity(requestXml, Constant.XML_CONTENT_TYPE);
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

    private SSLConnectionSocketFactory buildSsl(InputStream cert, String certPassword) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        P12Cert p12Cert = P12CertUtils.load(cert, certPassword.toCharArray());
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(p12Cert.getKeyStore(), certPassword.toCharArray()).build();
        //Allow TLSv1 protocol only
        return new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    }
}
