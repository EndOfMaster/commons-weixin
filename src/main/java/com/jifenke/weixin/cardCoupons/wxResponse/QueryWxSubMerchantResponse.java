package com.jifenke.weixin.cardCoupons.wxResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.cardCoupons.basic.WxSubMerchantState;
import com.jifenke.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class QueryWxSubMerchantResponse extends WxResponse {

    private Info info;

    public static class Info {

        @JsonProperty("merchant_id")
        long merchantId;

        @JsonProperty("app_id")
        String appId;

        @JsonProperty("create_time")
        long createTime;

        @JsonProperty("update_time")
        long updateTime;

        @JsonProperty("brand_name")
        String brandName;

        @JsonProperty("logo_url")
        String logoUrl;

        String status;

        @JsonProperty("begin_time")
        long beginTime;

        @JsonProperty("end_time")
        long endTime;

        @JsonProperty("primary_category_id")
        String primaryCategoryId;

        @JsonProperty("secondary_category_id")
        String secondaryCategoryId;

        public long getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(long merchantId) {
            this.merchantId = merchantId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getStatus() {
            return status;
        }
        public WxSubMerchantState getEnumStatus() {
            return WxSubMerchantState.valueOf(status);
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getBeginTime() {
            return beginTime;
        }

        public Info setBeginTime(long beginTime) {
            this.beginTime = beginTime;
            return this;
        }

        public long getEndTime() {
            return endTime;
        }

        public Info setEndTime(long endTime) {
            this.endTime = endTime;
            return this;
        }

        public String getPrimaryCategoryId() {
            return primaryCategoryId;
        }

        public void setPrimaryCategoryId(String primaryCategoryId) {
            this.primaryCategoryId = primaryCategoryId;
        }

        public String getSecondaryCategoryId() {
            return secondaryCategoryId;
        }

        public void setSecondaryCategoryId(String secondaryCategoryId) {
            this.secondaryCategoryId = secondaryCategoryId;
        }
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
