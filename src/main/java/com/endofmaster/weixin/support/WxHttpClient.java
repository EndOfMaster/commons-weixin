package com.endofmaster.weixin.support;

import com.endofmaster.weixin.WxClientException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.jifenke.commons.util.p12cert.P12Cert;
import com.jifenke.commons.util.p12cert.P12CertUtils;
import com.jifenke.commons.util.StreamUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static org.apache.http.entity.ContentType.MULTIPART_FORM_DATA;

/**
 * @author YQ.Huang
 */
public class WxHttpClient {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.setVisibility(new VisibilityChecker.Std(NONE, NONE, NONE, NONE, ANY));
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private final HttpClient httpClient;

    public WxHttpClient() {
        this(null, null);
    }

    public WxHttpClient(final InputStream cert, final String certPassword) {
        this(5000, 5000, cert, certPassword);
    }

    public WxHttpClient(final int connectTimeout, final int socketTimeout, final InputStream cert, final String certPassword) {
        try {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(connectTimeout)
                    .setSocketTimeout(socketTimeout)
                    .build();
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            if (cert != null) {
                SSLConnectionSocketFactory sslSocketFactory = buildSsl(cert, certPassword);
                httpClientBuilder.setSSLSocketFactory(sslSocketFactory);
            }
            httpClient = httpClientBuilder.setDefaultRequestConfig(config).build();
        } catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new WxClientException(e.getLocalizedMessage(), e);
        }
    }

    public WxHttpResponse execute(WxHttpRequest wxHttpRequest) throws WxClientException {
        HttpUriRequest httpRequest = buildHttpUriRequest(wxHttpRequest);
        try {
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            WxHttpResponse wxHttpResponse = new WxHttpResponse(httpResponse.getStatusLine().getStatusCode());
            wxHttpResponse.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            wxHttpResponse.setBody(httpResponse.getEntity().getContent());
            wxHttpResponse.setContentType(httpResponse.getEntity().getContentType().getValue());
            return wxHttpResponse;
        } catch (IOException e) {
            throw new WxClientException(e.getLocalizedMessage(), e);
        }
    }

    private HttpUriRequest buildHttpUriRequest(WxHttpRequest wxHttpRequest) throws WxClientException {
        RequestBuilder requestBuilder = RequestBuilder.create(wxHttpRequest.getMethod())
                .setUri(wxHttpRequest.getUrl());

        for (String headerKey : wxHttpRequest.getHeaders().keySet()) {
            requestBuilder.addHeader(headerKey, wxHttpRequest.getHeaders().get(headerKey));
        }

        if ("POST".equalsIgnoreCase(wxHttpRequest.getMethod()) || "PUT".equalsIgnoreCase(wxHttpRequest.getMethod())) {
            Map<String, Object> map = new HashMap<>();
            for (WxHttpRequest.Arg arg : wxHttpRequest.getArgs()) {
                map.put(arg.key, arg.value);
            }
            if ("json".equalsIgnoreCase(wxHttpRequest.getDataType())) {
                try {
                    String json = MAPPER.writeValueAsString(map);
                    StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
                    requestBuilder.setEntity(entity);
                } catch (JsonProcessingException e) {
                    throw new WxClientException(e.getLocalizedMessage(), e);
                }
            } else {
                //目前其他模式只有form
                try {
                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                    for (String key : map.keySet()) {
                        Object value = map.get(key);
                        if (value instanceof String) {
                            builder.addTextBody(key, value.toString());
                        } else {
                            byte[] data = StreamUtils.copyToByteArray((InputStream) value);
                            builder.addBinaryBody(key, data, MULTIPART_FORM_DATA, key);
                        }
                    }
                    builder.setMode(HttpMultipartMode.RFC6532);
                    requestBuilder.setEntity(builder.build());
                } catch (IOException e) {
                    throw new WxClientException(e.getLocalizedMessage(), e);
                }
            }
        } else {
            for (WxHttpRequest.Arg arg : wxHttpRequest.getArgs()) {
                requestBuilder.addParameter(arg.key, arg.value.toString());
            }
        }
        return requestBuilder.build();
    }

    private SSLConnectionSocketFactory buildSsl(InputStream cert, String certPassword) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        P12Cert p12Cert = P12CertUtils.load(cert, certPassword.toCharArray());
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(p12Cert.getKeyStore(), certPassword.toCharArray()).build();
        // Allow TLSv1 protocol only
        return new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    }

}
