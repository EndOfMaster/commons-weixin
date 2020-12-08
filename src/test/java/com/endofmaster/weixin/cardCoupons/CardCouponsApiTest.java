package com.endofmaster.weixin.cardCoupons;

import com.endofmaster.weixin.cardCoupons.request.CreateCardQrCodeRequest;
import com.endofmaster.weixin.cardCoupons.wxResponse.CardCategory;
import com.endofmaster.weixin.cardCoupons.wxResponse.CardCouponsImages;
import com.endofmaster.weixin.cardCoupons.wxResponse.CreateCardQrCodeResponse;
import com.endofmaster.weixin.cardCoupons.wxResponse.DecryptWxCardCodeResponse;
import com.endofmaster.weixin.cardCoupons.wxResponse.MediaUploadImage;
import com.endofmaster.weixin.cardCoupons.wxResponse.QueryWxCardCodeResponse;
import com.endofmaster.weixin.cardCoupons.wxResponse.QueryWxPoiResponse;
import com.endofmaster.weixin.cardCoupons.wxResponse.QueryWxSubMerchantResponse;
import com.endofmaster.weixin.jssdk.WxJsApi;
import com.endofmaster.weixin.support.WxCardUtils;
import com.endofmaster.weixin.support.WxHttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.findParam;

//@Ignore
public class CardCouponsApiTest {

    private final CardCouponsApi cardCouponsApi;
    private final WxJsApi wxJsApi;

    public CardCouponsApiTest() {
        InputStream key = this.getClass().getResourceAsStream("/wx-server.pfx");
        this.cardCouponsApi = new CardCouponsApi(new WxHttpClient(key, "214292653100837"));
        this.wxJsApi = new WxJsApi(new WxHttpClient(), "wxe2190d22ce025e4f");

    }

    @Test
    public void test() throws FileNotFoundException {
        File file = new File("e:/test.png");
        FileInputStream inputStream = new FileInputStream(file);
        CardCouponsImages images = cardCouponsApi.simpleUploadImage("7MtcEtbKY7oqwPozGG7uq9kxNCjlhkdsbf4OiYKPL67E-_Ye4Z81sauC6VSa7Jxg9XlKMkhTJ78xmsC8jddEj9wkUTrBC1CUCDdS-ydHjZxtGr50gC_EqVgzYGPDC09QKKBaAFARRI",
                "test.png", inputStream);
        System.err.println(images);
    }

//    @Test
//    public void testJson() throws JsonProcessingException {
//        CreateSubMerchantRequest merchantInfo = new CreateSubMerchantRequest("asdsada", "sada", "asdsasd", 12313123123213L, 1, 1);
//        Map<String, Object> info = new HashMap<>();
//        info.put("brand_name", merchantInfo.getBrandName());
//        info.put("logo_url", merchantInfo.getLogoUrl());
//        info.put("protocol", merchantInfo.getProtocol());
//        info.put("end_time", merchantInfo.getEndTime());
//        info.put("primary_category_id", merchantInfo.getPrimaryCategoryId());
//        info.put("secondary_category_id", merchantInfo.getSecondaryCategoryId());
//        MapUtils.putIfNotNull(info, "agreement_media_id", merchantInfo.getAgreementMediaId());
//        MapUtils.putIfNotNull(info, "operator_media_id", merchantInfo.getOperatorMediaId());
//        WxHttpRequest request = new WxHttpRequest("https://api.weixin.qq.com/card/submerchant/submit?access_token=")
//                .withMethod("post").setArg("info", info);
//        Map<String, Object> map = new HashMap<>();
//        for (WxHttpRequest.Arg arg : request.getArgs()) {
//            map.put(arg.key, arg.value);
//        }
//        String json = MAPPER.writeValueAsString(map);
//        System.err.println(json);
//    }

    @Test
    public void testJson2bean() throws IOException {
        String json = "{\"info\":{\"merchant_id\":12,\"app_id\":\"xxxxxxxxxxxxx\",\"create_time\":1438790559,\"update_time\":1438790559,\"brand_name\":\"aaaaaa\",\"logo_url\":\"http://mmbiz.xxxx\",\"status\":\"CHECKING\",\"begin_time\":1438790559,\"end_time\":1438990559,\"primary_category_id\":1,\"secondary_category_id\":101}}";
        QueryWxSubMerchantResponse respose = WxHttpClient.MAPPER.readValue(json, QueryWxSubMerchantResponse.class);
        System.err.println(respose);
    }

    @Test
    public void testCardCategory() throws JsonProcessingException {
        CardCategory cardCategory = cardCouponsApi.getCardCategory("hX4vGD3jDqIYOkk8I-K3JQvOwBs_ss1sWiPbwbWZH66dZiWydXd_NFp0H5tCjUdXSffPo2B2VK2WCDRd0qEQSMWBtkj-j0WPFhv7qxOXXZpO0vrY6On6thgRTDm_AOnpMSVeAHATZC");
        String json = WxHttpClient.MAPPER.writeValueAsString(cardCategory.getCategories());
        System.err.println(json);
    }

    @Test
    public void testMediaUploadImage() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("e:/test.png");
        MediaUploadImage image = cardCouponsApi.defaultUploadImage("g-qaftPAmze5FS7iGJslW4zIDUXtzI4kPZQa3Cop02QaqmALpgkJrUfbVTEnYo4ap0ZZUzLFPa3QbtA4f9ht08AsCE_QZXa9fFRO_eMxBOOu8yCzbmhLUgk61rs_zJMlBISiABAQXH",
                "test.png", inputStream);
        System.err.println(image);
    }

    @Test
    public void testQueryWxPoiResponseJson2Bean() throws IOException {
        String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"business\":{\"base_info\":{\"sid\":\"001\",\"business_name\":\"麦当劳\",\"branch_name\":\"艺苑路店\",\"province\":\"广东省\",\"city\":\"广州市\",\"address\":\"海珠区艺苑路11 号\",\"telephone\":\"020-12345678\",\"categories\":[\"美食,小吃快餐\"],\"offset_type\":1,\"longitude\":115.32375,\"latitude\":25.097486,\"photo_list\":[{\"photo_url\":\"https:// XXX.com\"},{\"photo_url\":\"https://XXX.com\"}],\"recommend\":\"麦辣鸡腿堡套餐，麦乐鸡，全家桶\",\"special\":\"免费wifi，外卖服务\",\"introduction\":\"麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大   约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品\",\"open_time\":\"8:00-20:00\",\"avg_price\":35,\"available_state\":3,\"update_status\":0}}}";
        QueryWxPoiResponse response = WxHttpClient.MAPPER.readValue(json, QueryWxPoiResponse.class);
        System.err.println(response);
    }

    @Test
    public void testWxCardBaseInfoJson() throws IOException {
        String json = "{\"logo_url\":\"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0\",\"brand_name\":\"微信餐厅\",\"code_type\":\"CODE_TYPE_TEXT\",\"title\":\"132元双人火锅套餐\",\"color\":\"Color010\",\"notice\":\"使用时向服务员出示此券\",\"service_phone\":\"020-88888888\",\"description\":\"不可与其他优惠同享\\n如需团购券发票，请在消费时向商户提出\\n店内均可使用，仅限堂食\",\"date_info\":{\"type\":\"DATE_TYPE_FIX_TIME_RANGE\",\"begin_timestamp\":1397577600,\"end_timestamp\":1472724261},\"sku\":{\"quantity\":500000},\"use_limit\":100,\"get_limit\":3,\"use_custom_code\":false,\"bind_openid\":false,\"can_share\":true,\"can_give_friend\":true,\"location_id_list\":[123,12321,345345],\"center_title\":\"顶部居中按钮\",\"center_sub_title\":\"按钮下方的wording\",\"center_url\":\"www.qq.com\",\"custom_url_name\":\"立即使用\",\"custom_url\":\"http://www.qq.com\",\"custom_url_sub_title\":\"6个汉字tips\",\"promotion_url_name\":\"更多优惠\",\"promotion_url\":\"http://www.qq.com\",\"source\":\"大众点评\"}";
        WxCardBaseInfo info = WxHttpClient.MAPPER.readValue(json, WxCardBaseInfo.class);
        String newJson = WxHttpClient.MAPPER.writeValueAsString(info);
        System.err.println(newJson);
    }

    @Test
    public void testWxCardGrouponCardInfo() throws IOException {
        String json = "{\"card\":{\"card_type\":\"GROUPON\",\"groupon\":{\"base_info\":{\"logo_url\":\"http://mmbiz.qpic.cn/mmbiz/iaL1LJM1mF9aRKPZJkmG8xXhiaHqkKSVMMWeN3hLut7X7hicFNjakmxibMLGWpXrEXB33367o7zHN0CwngnQY7zb7g/0\",\"brand_name\":\"微信餐厅\",\"code_type\":\"CODE_TYPE_TEXT\",\"title\":\"132元双人火锅套餐\",\"color\":\"Color010\",\"notice\":\"使用时向服务员出示此券\",\"service_phone\":\"020-88888888\",\"description\":\"不可与其他优惠同享\\n如需团购券发票，请在消费时向商户提出\\n店内均可使用，仅限堂食\",\"date_info\":{\"type\":\"DATE_TYPE_FIX_TIME_RANGE\",\"begin_timestamp\":1397577600,\"end_timestamp\":1472724261},\"sku\":{\"quantity\":500000},\"use_limit\":100,\"get_limit\":3,\"use_custom_code\":false,\"bind_openid\":false,\"can_share\":true,\"can_give_friend\":true,\"location_id_list\":[123,12321,345345],\"center_title\":\"顶部居中按钮\",\"center_sub_title\":\"按钮下方的wording\",\"center_url\":\"www.qq.com\",\"custom_url_name\":\"立即使用\",\"custom_url\":\"http://www.qq.com\",\"custom_url_sub_title\":\"6个汉字tips\",\"promotion_url_name\":\"更多优惠\",\"promotion_url\":\"http://www.qq.com\",\"source\":\"大众点评\"},\"advanced_info\":{\"use_condition\":{\"accept_category\":\"鞋类\",\"reject_category\":\"阿迪达斯\",\"can_use_with_other_discount\":true},\"abstract\":{\"abstract\":\"微信餐厅推出多种新季菜品，期待您的光临\",\"icon_url_list\":[\"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj  piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\"]},\"text_image_list\":[{\"image_url\":\"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sjpiby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\",\"text\":\"此菜品精选食材，以独特的烹饪方法，最大程度地刺激食 客的味蕾\"},{\"image_url\":\"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LacgHxp3sJ3vn97bGLz0ib0Sfz1bjiaoOYA027iasqSG0sj piby4vce3AtaPu6cIhBHkt6IjlkY9YnDsfw/0\",\"text\":\"此菜品迎合大众口味，老少皆宜，营养均衡\"}],\"time_limit\":[{\"type\":\"MONDAY\",\"begin_hour\":0,\"end_hour\":10,\"begin_minute\":10,\"end_minute\":59},{\"type\":\"HOLIDAY\"}],\"business_service\":[\"BIZ_SERVICE_FREE_WIFI\",\"BIZ_SERVICE_WITH_PET\",\"BIZ_SERVICE_FREE_PARK\",\"BIZ_SERVICE_DELIVER\"]},\"deal_detail\":\"以下锅底2选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补 凉锅、酸菜鱼锅可选）：\\n大锅1份 12元\\n小锅2份 16元 \"}}}";
        Map info = WxHttpClient.MAPPER.readValue(json, Map.class);
        String newJson = WxHttpClient.MAPPER.writeValueAsString(info.get("card"));
        System.err.println(newJson);
    }

    @Test
    public void testGetPoiCategory() {
        cardCouponsApi.getPoiCategory("hX4vGD3jDqIYOkk8I-K3JQvOwBs_ss1sWiPbwbWZH66dZiWydXd_NFp0H5tCjUdXSffPo2B2VK2WCDRd0qEQSMWBtkj-j0WPFhv7qxOXXZpO0vrY6On6thgRTDm_AOnpMSVeAHATZC").getCategoryList().stream().forEach(c -> System.err.println(c));
    }

    @Test
    public void testQueryWxCardCode() {
        String accessToken = "L-hKLitHyNPRAVoZPkv5zMB-fKSr0XU-AsMUw53uygyk-5wYIpv0Mhh0O1VB4YlSEqy9i4Ga_Zj7VAwRhow1GjCwlKI3EpZgV4r4DgLudt-ASt_f-RnDFxxMmmV5FZzBVEPdAGAKTE";
        QueryWxCardCodeResponse response = cardCouponsApi.queryWxCardCode(
                accessToken, "pVmqjxFv_0rPJGNTa3IjuMfidLno", "917647312075");
        System.err.println(response);
    }

    @Test
    public void testSignature() {
        String apiTicket = "IpK_1T69hDhZkLQTlwsAXw5tUFze0X87-H-dgbVLXk6xqvGvbOr4z1aSfldcwYCMNP1qbVxDLJK2OMjR_PVrMg";
        String cardId = "12308898124";
        String openId = "2jh342kj5gh35k23iug523uk";
        String timestamp = System.currentTimeMillis() + "";
        System.err.println(timestamp);
        String nonceStr = RandomStringUtils.randomAlphanumeric(24);
        System.err.println(nonceStr);
        System.err.println(WxCardUtils.cardExtSignature(apiTicket, cardId, timestamp, nonceStr, null, openId));
    }

    @Test
    public void queryCardStatusTest() throws IOException {
        String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"card\":{\"card_type\":\"DISCOUNT\",\"discount\":{\"base_info\":{\"id\":\"pbLatjnP97_F9PudzBARQhn7xR7A\",\"logo_url\":\"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LafmY25YclQ7vw5noBxeVH3DG5AKFR1ZsRgMgsvjll7EkUsZib00J964AEpTjkNXF2HorJHt5mtt45Q/0?wx_fmt=png\",\"code_type\":\"CODE_TYPE_NONE\",\"brand_name\":\"微信餐厅\",\"title\":\"9折优惠券\",\"date_info\":{\"type\":\"DATE_TYPE_FIX_TERM\",\"fixed_term\":30,\"fixed_begin_term\":0},\"color\":\"#10AD61\",\"notice\":\"到店使用\",\"description\":\"\",\"location_id_list\":[218384742,402521653,402521608],\"get_limit\":3,\"can_share\":true,\"can_give_friend\":true,\"status\":\"CARD_STATUS_VERIFY_OK\",\"sku\":{\"quantity\":100096,\"total_quantity\":100100},\"create_time\":1457525546,\"update_time\":1457526240,\"area_code_list\":[]},\"discount\":10,\"advanced_info\":{\"time_limit\":[{\"type\":\"MONDAY\"},{\"type\":\"TUESDAY\"}],\"text_image_list\":[],\"business_service\":[],\"consume_share_card_list\":[],\"abstract\":{\"abstract\":\"点击了解更多\",\"icon_url_list\":[\"http://mmbiz.qpic.cn/mmbiz/p98FjXy8LafiawSeJeqBzk8qC40iaKIwUPm4TSCelulzEbAywKr7tWjkd5vRjbmFloUFeThfwhwMUZIXmsCtJpyQ/0?wx_fmt=jpeg\"]},\"share_friends\":false}}}}";
        JsonNode card = WxHttpClient.MAPPER.readTree(json).get("card");
        JsonNode cardInfo = card.get(findParam(card, "card_type").toLowerCase());
        JsonNode baseInfo = cardInfo.get("base_info");
        String status = findParam(baseInfo, "status");
        System.err.println(status);
    }

    @Test
    public void queryCardStatusTest2() throws IOException {
        String accessToken = "IAwXQOQOe6AwwnGh5nuGzb4mkENgRh7e2UVJw_zMvzs0t9cG-IYhXsqPkbX4RzwBNsXVQpFj0bFEyuDeC0PnATiHFit6V-qIt5tmg_TKSRXrgI3Hx6htxjo3Zbxe0O1IPKIaABAWRP";
        String status = cardCouponsApi.queryWxCardStatus(accessToken, "pVmqjxNgf50j6L2HEOSV6rcCi698");
        System.err.println(status);
    }

    @Test
    public void createWxCardQrCodeTest() {
        String accessToken = "psdJHegR9dgsa1_TsHyZX8tXDjZL8bAkaCXXduzgu2HMh9QLVvAmPu4ZWkSuumoQIuVTXV0BlX9K6x7POYc10rPkTyDyPqVoJc27lrSpXxs22QdxIvRifQNOCljQ6dCyOSQfAJAZDB";
        CreateCardQrCodeRequest request = new CreateCardQrCodeRequest(300, "pVmqjxDJ0yWctdDnrnbjjPgYrs3c", "QRCODE");
        CreateCardQrCodeResponse response = cardCouponsApi.createCardQrCode(accessToken, request);
        System.err.println(response);
    }

    @Test
    public void decryptWxCardCodeTest() throws UnsupportedEncodingException {
        String accessToken = "5_DtXQtckqqPlqPTYxK4Vs1-izrT5ewcQNV3t4ZKrUqJItmRAYGKNTKJpsbtYOSfzfimblw2pV7YliD1pz1tm8qp3u5VKLBtbi5GYj2EPoCMGZ5wxjo4HoZTlHuDjHDxSsrLnxymHmgo8G6ZG9HLMfADAYCD";
        DecryptWxCardCodeResponse response = cardCouponsApi.decryptWxCardCode(accessToken, "mIce2ao01Wu1uVdV%2BnYVbQeDF%2FnJzH3bHvgDsQniUVg%3D");
        System.err.println(response);
    }

    @Test
    public void delWxCard() {
        String accessToken = "nDJ4fFQExKbrOrMff7XVV2jp1xfQGhUUcUfVmIGHdQl67rYRut_VXyb5TbNRi60U_HLquj2ngxRVHUrkIEOolm7UTmOFMuhOIqeDk4DdBk7MdG_zp_zLVz4fHiwfcNLgTJCgABABRK";
        cardCouponsApi.delWxCard(accessToken, "pVmqjxIRtz82BJnTaISUoF0ApZ5Y");
    }
}