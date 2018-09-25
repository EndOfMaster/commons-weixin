package com.jifenke.weixin.cardCoupons.request;

/**
 * @author ZM.Wang
 */
public class CreateSubMerchantRequest implements WxRequest {

    /**
     * 子商户自己的appId
     */
    private String appId;
    /**
     * 子商户名称
     */
    private String brandName;
    /**
     * 子商户logo,文件上传获得的url
     */
    private String logoUrl;
    /**
     * 微信临时素材上传授权函文件的meida_id
     */
    private String protocol;
    /**
     * 授权函有效期截止时间的时间戳
     */
    private long endTime;
    /**
     * 一级类目id,可以通过本文档中接口查询
     */
    private int primaryCategoryId;
    /**
     * 二级类目id，可以通过本文档中接口查询
     */
    private int secondaryCategoryId;
    /**
     * 营业执照或个体工商户营业执照彩照或扫描件
     */
    private String agreementMediaId;
    /**
     * 营业执照内登记的经营者身份证彩照或扫描件
     */
    private String operatorMediaId;

    public CreateSubMerchantRequest(String brandName, String logoUrl, String protocol, long endTime, int primaryCategoryId, int secondaryCategoryId) {
        this.brandName = brandName;
        this.logoUrl = logoUrl;
        this.protocol = protocol;
        this.endTime = endTime;
        this.primaryCategoryId = primaryCategoryId;
        this.secondaryCategoryId = secondaryCategoryId;
    }

    CreateSubMerchantRequest() {
    }

    public void setAgreementMediaId(String agreementMediaId) {
        this.agreementMediaId = agreementMediaId;
    }

    public void setOperatorMediaId(String operatorMediaId) {
        this.operatorMediaId = operatorMediaId;
    }

    public String getAppId() {
        return appId;
    }

    public CreateSubMerchantRequest setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getProtocol() {
        return protocol;
    }

    public long getEndTime() {
        return endTime;
    }

    public int getPrimaryCategoryId() {
        return primaryCategoryId;
    }

    public int getSecondaryCategoryId() {
        return secondaryCategoryId;
    }

    public String getAgreementMediaId() {
        return agreementMediaId;
    }

    public String getOperatorMediaId() {
        return operatorMediaId;
    }
}
