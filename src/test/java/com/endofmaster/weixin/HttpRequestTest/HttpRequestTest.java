package com.endofmaster.weixin.HttpRequestTest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpRequestTest {

    @Test
    public void setAliLogLevel() {
        String url = "http://101.201.39.79:18081/loggers/com";
        String configuredLevel = "info";
        try {
            HttpClient httpClient = HttpClients.custom().build();
            RequestBuilder requestBuilder = RequestBuilder.create("post").setUri(url);
            requestBuilder.setEntity(new StringEntity("{\"configuredLevel\": \"" + configuredLevel + "\" }", ContentType.APPLICATION_JSON));
            HttpResponse httpResponse = httpClient.execute(requestBuilder.build());
            System.err.println(httpResponse.getStatusLine().getReasonPhrase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHttpPost() throws IOException {
        String url = "http://101.201.39.79:18081/loggers/com.jifenke";
        String configuredLevel = "info";

        HttpClient httpClient = HttpClients.custom().build();
        HttpPost httpPost = new HttpPost(url);
        StringEntity requestEntity = new StringEntity("{\"configuredLevel\": \"" + configuredLevel + "\" }","utf-8");
        requestEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        System.err.println(response.getStatusLine().getStatusCode());
    }

}
