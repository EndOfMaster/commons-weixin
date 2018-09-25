package com.endofmaster.weixin.cardCoupons;

import com.endofmaster.weixin.WxServerException;
import com.endofmaster.weixin.cardCoupons.wxResponse.*;
import com.endofmaster.weixin.support.MapUtils;
import com.endofmaster.weixin.support.WxHttpClient;
import com.endofmaster.weixin.support.WxHttpRequest;
import com.endofmaster.weixin.support.WxHttpResponse;
import com.endofmaster.weixin.support.WxResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.endofmaster.weixin.cardCoupons.request.CreateCardQrCodeRequest;
import com.endofmaster.weixin.cardCoupons.request.CreateSubMerchantRequest;
import com.endofmaster.weixin.cardCoupons.request.CreateWxCardRequest;
import com.endofmaster.weixin.cardCoupons.request.CreateWxPoiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.findParam;
import static com.endofmaster.weixin.support.WxHttpClient.MAPPER;

/**
 * @author ZM.Wang
 */
public class CardCouponsApi {

    private final static Logger logger = LoggerFactory.getLogger(CardCouponsApi.class);

    private final WxHttpClient client;

    public CardCouponsApi(WxHttpClient client) {
        this.client = client;
    }

    public CardCouponsImages simpleUploadImage(String accessToken, String fileName, InputStream inputStream) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + accessToken)
                .withDataType("form").withMethod("post")
                .setArg(fileName, inputStream);
        WxHttpResponse response = client.execute(request);
        return response.parse(CardCouponsImages.class);
    }

    public MediaUploadImage defaultUploadImage(String accessToken, String fileName, InputStream inputStream) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/media/upload?type=image&access_token=" + accessToken)
                .withDataType("form").withMethod("post")
                .setArg(fileName, inputStream);
        WxHttpResponse response = client.execute(request);
        return response.parse(MediaUploadImage.class);
    }

    public QueryWxSubMerchantResponse createSubMerchant(String accessToken, CreateSubMerchantRequest merchantInfo) {
        Map<String, Object> info = new HashMap<>();
        info.put("app_id", merchantInfo.getAppId());
        info.put("brand_name", merchantInfo.getBrandName());
        info.put("logo_url", merchantInfo.getLogoUrl());
        info.put("protocol", merchantInfo.getProtocol());
        info.put("end_time", merchantInfo.getEndTime());
        info.put("primary_category_id", merchantInfo.getPrimaryCategoryId());
        info.put("secondary_category_id", merchantInfo.getSecondaryCategoryId());
        MapUtils.putIfNotNull(info, "agreement_media_id", merchantInfo.getAgreementMediaId());
        MapUtils.putIfNotNull(info, "operator_media_id", merchantInfo.getOperatorMediaId());
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/submerchant/submit?access_token=" + accessToken)
                .withMethod("post").setArg("info", info);
        WxHttpResponse response = client.execute(request);
        return response.parse(QueryWxSubMerchantResponse.class);
    }

    public QueryWxSubMerchantResponse getWxSubMerchantInfo(String accessToken, long merchantId) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/submerchant/get?access_token=" + accessToken)
                .withMethod("post").setArg("merchant_id", merchantId);
        WxHttpResponse response = client.execute(request);
        return response.parse(QueryWxSubMerchantResponse.class);
    }

    public CardCategory getCardCategory(String accessToken) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/getapplyprotocol?access_token=" + accessToken);
        WxHttpResponse response = client.execute(request);
        return response.parse(CardCategory.class);
    }

    public CreateWxPoiResponse createWxPoi(String accessToken, CreateWxPoiRequest paramsRequest) {
        WxHttpRequest request = new WxHttpRequest("http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=" + accessToken)
                .withMethod("post").setArg("business", paramsRequest);
        WxHttpResponse response = client.execute(request);
        return response.parse(CreateWxPoiResponse.class);
    }

    public QueryWxPoiResponse getWxPoiInfo(String accessToken, String poiId) {
        WxHttpRequest request = new WxHttpRequest("http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=" + accessToken)
                .withMethod("post").setArg("poi_id", poiId);
        WxHttpResponse response = client.execute(request);
        return response.parse(QueryWxPoiResponse.class);
    }

    public WxResponse delPoi(String accessToken, String poiId) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/cgi-bin/poi/delpoi?access_token=" + accessToken)
                .withMethod("post").setArg("poi_id", poiId);
        WxHttpResponse response = client.execute(request);
        return response.parse(WxResponse.class);
    }

    public PoiCategory getPoiCategory(String accessToken) {
        WxHttpRequest request = new WxHttpRequest("http://api.weixin.qq.com/cgi-bin/poi/getwxcategory?access_token=" + accessToken);
        WxHttpResponse response = client.execute(request);
        return response.parse(PoiCategory.class);
    }

    public CreateWxCardResponse createWxCard(String accessToken, CreateWxCardRequest wxCardRequest) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/create?access_token=" + accessToken)
                .withMethod("post").setArg("card", wxCardRequest.getWxCardInfo());
        WxHttpResponse response = client.execute(request);
        return response.parse(CreateWxCardResponse.class);
    }

    public String queryWxCardStatus(String accessToken, String cardId) throws IOException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/get?access_token=" + accessToken)
                .withMethod("post").setArg("card_id", cardId);
        WxHttpResponse response = client.execute(request);
        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
            JsonNode root = MAPPER.readTree(response.getBody());
            JsonNode card = root.get("card");
            String errCode = findParam(root, "errcode");
            if (!"0".equals(errCode)) {
                String errorMsg = findParam(root, "errmsg");
                logger.error("微信错误码：" + errCode + ",错误内容：" + errorMsg);
                throw new WxServerException(errorMsg);
            }
            JsonNode cardInfo = card.get(findParam(card, "card_type").toLowerCase());
            JsonNode baseInfo = cardInfo.get("base_info");
            return findParam(baseInfo, "status");
        } else {
            throw new WxServerException("Failed to parse body, invalid status code");
        }
    }

    public void modifyCardStock(String accessToken, String cardId, long num) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/modifystock?access_token=" + accessToken)
                .withMethod("post").setArg("card_id", cardId);
        if (num > 0) {
            request.setArg("increase_stock_value", num);
        } else if (num == 0) {
            return;
        } else {
            request.setArg("reduce_stock_value", num * -1);
        }
        client.execute(request);
    }

    public QueryWxCardCodeResponse queryWxCardCode(String accessToken, String cardId, String code) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/code/get?access_token=" + accessToken)
                .withMethod("post").setArg("card_id", cardId).setArg("code", code).setArg("check_consume", false);
        WxHttpResponse response = client.execute(request);
        return response.parse(QueryWxCardCodeResponse.class);
    }

    public void delWxCard(String accessToken, String cardId) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/delete?access_token=" + accessToken)
                .withMethod("post").setArg("card_id", cardId);
        client.execute(request);
    }

    public WxCardConsumeResponse consumeWxCard(String accessToken, String code) {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/code/consume?access_token=" + accessToken)
                .withMethod("post").setArg("code", code);
        WxHttpResponse response = client.execute(request);
        return response.parse(WxCardConsumeResponse.class);
    }

    public CreateCardQrCodeResponse createCardQrCode(String accessToken, CreateCardQrCodeRequest request) {
        WxHttpRequest wxRequest = new WxHttpRequest("https://api.weixin.qq.com/card/qrcode/create?access_token=" + accessToken)
                .withMethod("post").setArg("action_name", request.getActionName())
                .setArg("expire_seconds", request.getExpireSeconds())
                .setArg("action_info", request.getActionInfo());
        WxHttpResponse response = client.execute(wxRequest);
        return response.parse(CreateCardQrCodeResponse.class);
    }

    public DecryptWxCardCodeResponse decryptWxCardCode(String accessToken, String encryptCode) throws UnsupportedEncodingException {
        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/code/decrypt?access_token=" + accessToken)
                .withMethod("post").setArg("encrypt_code", URLDecoder.decode(encryptCode, "UTF-8"));
        WxHttpResponse response = client.execute(request);
        return response.parse(DecryptWxCardCodeResponse.class);
    }

}
