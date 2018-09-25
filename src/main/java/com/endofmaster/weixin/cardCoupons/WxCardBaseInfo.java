package com.endofmaster.weixin.cardCoupons;

import com.endofmaster.weixin.support.ParamsUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.cardCoupons.basic.WxCardCodeType;
import com.endofmaster.weixin.cardCoupons.basic.WxCardDateType;

import java.util.List;

/**
 * @author ZM.Wang
 */
public class WxCardBaseInfo {
    @JsonProperty("sub_merchant_info")
    private SubMerchantInfo subMerchantInfo;
    @JsonProperty("logo_url")
    private String logoUrl;             //卡券的商户logo，建议像素为300*300。
    @JsonProperty("code_type")          //需求文档建议CODE_TYPE_NONE
    private WxCardCodeType codeType;    //码型
    @JsonProperty("brand_name")
    private String brandName;           //商户名字,字数上限为12个汉字。
    private String title;               //卡券名
    private String color;               //券颜色。按色彩规范标注填写Color010-Color100。
    private String notice;              //卡券使用提醒，字数上限为16个汉字。
    private String description;         //卡券使用说明，字数上限为1024个汉字。
    private Sku sku;                    //商品信息。
    @JsonProperty("date_info")
    private DateInfo dateInfo;          //使用日期，有效期的信息。

    /*---------选填参数----------*/
    @JsonProperty("use_custom_code")
    private Boolean useCustomCode;      //是否自定义Code码

    /**
     * 填入 GET_CUSTOM_CODE_MODE_DEPOSIT表示该卡券为预存code模式卡券，
     * 须导入超过库存数目的自定义code后方可投放，填入该字段后，quantity字段须为0,须导入code后再增加库存
     */
    @JsonProperty("get_custom_code_mode")
    private String getCustomCodeMode;

    /**
     * 是否指定用户领取，填写true或false 。默认为false。通常指定特殊用户群体,投放卡券或防止刷券时选择指定用户领取。
     */
    @JsonProperty("bind_openid")
    private Boolean bindOpenid;
    @JsonProperty("service_phone")
    private String servicePhone;        //客服电话
    @JsonProperty("location_id_list")
    private List<Long> locationIdList;   //适用门店
    @JsonProperty("use_all_locations")
    private Boolean useAllLocations;    //适用全部门店，和location_id_list参数冲突

    /*----------按钮套装---------*/
    @JsonProperty("center_title")
    private String centerTitle;         //卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示
    @JsonProperty("center_sub_title")
    private String centerSubTitle;      //显示在入口下方的提示语，仅在卡券状态正常(可以核销)时显示。
    @JsonProperty("center_url")
    private String centerUrl;           //顶部居中的url,仅在卡券状态正常(可以核销)时显示。
    @JsonProperty("center_app_brand_user_name")
    private String centerAppBrandUserName;  //卡券跳转的小程序的user_name，仅可跳转该公众号绑定的小程序。
    @JsonProperty("center_app_brand_pass")
    private String centerAppBrandPass;  //卡券跳转的小程序的path

    /*-----------详细信息套装-----------*/
    @JsonProperty("custom_url_name")
    private String customUrlName;       //详细信息跳转外链的入口名字。
    @JsonProperty("custom_url")
    private String customUrl;           //详细信息跳转的URL。
    @JsonProperty("custom_url_sub_title")
    private String customUrlSubTitle;   //显示在入口右侧的提示语。
    @JsonProperty("custom_app_brand_user_name")
    private String customAppBrandUserName;  //卡券跳转的小程序的user_name，仅可跳转该公众号绑定的小程序。
    @JsonProperty("custom_app_brand_pass")
    private String customAppBrandPass;  //卡券跳转的小程序的path

    /*----------还不知道在哪的营销套装-----------*/
    @JsonProperty("promotion_url_name")
    private String promotionUrlName;    //营销场景的自定义入口名称。
    @JsonProperty("promotion_url")
    private String promotionUrl;        //入口跳转外链的地址链接。
    @JsonProperty("promotion_url_sub_title")
    private String promotionUrlSubTitle;    //显示在营销入口右侧的提示语。
    @JsonProperty("promotion_app_brand_user_name")
    private String promotionAppBrandUserName;   //卡券跳转的小程序的user_name，仅可跳转该 公众号绑定的小程序。
    @JsonProperty("promotion_app_brand_pass")
    private String promotionAppBrandPass;   //卡券跳转的小程序的path

    @JsonProperty("get_limit")
    private Integer getLimit;               //每人可领券的数量限制,不填写默认为50。
    @JsonProperty("use_limit")
    private Integer useLimit;               //每人可核销的数量限制,不填写默认为50。
    @JsonProperty("can_share")
    private Boolean canShare;           //卡券领取页面是否可分享。
    @JsonProperty("can_give_friend")
    private Boolean canGiveFriend;      //卡券是否可转赠。

    public static class Sku {
        long quantity;                   //卡券库存的数量，上限为100000000。

        public long getQuantity() {
            return quantity;
        }

        public Sku setQuantity(long quantity) {
            this.quantity = quantity;
            return this;
        }
    }

    public static class SubMerchantInfo {
        @JsonProperty("merchant_id")
        long merchantId;

        public long getMerchantId() {
            return merchantId;
        }

        public SubMerchantInfo setMerchantId(long merchantId) {
            this.merchantId = merchantId;
            return this;
        }
    }

    static class DateInfo {

        public String type;

        @JsonProperty("begin_timestamp")
        Long beginTimestamp;            //类型为时间范围时的开始时间

        @JsonProperty("end_timestamp")
        Long endTimestamp;              //两种类型的统一结束时间(时间范围的是为必填，固定时长的选填)

        @JsonProperty("fixed_term")
        Long fixedTerm;              //类型为固定时长时,表示自领取后多少天内有效，不支持填写0

        @JsonProperty("fixed_begin_term")
        Long fixedBeginTerm;         //类型为固定时长时,表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）
    }

    public void timeRange(Long beginTimestamp, Long endTimestamp) {
        dateInfo = new DateInfo();
        dateInfo.type = WxCardDateType.TIME_RANGE.getValue();
        dateInfo.beginTimestamp = ParamsUtils.require(beginTimestamp);
        dateInfo.endTimestamp = ParamsUtils.require(endTimestamp);
    }

    public void timeFixTerm(Long fixedTerm, Long fixedBeginTerm, Long endTimestamp) {
        dateInfo = new DateInfo();
        dateInfo.type = WxCardDateType.FIX_TERM.getValue();
        dateInfo.fixedTerm = ParamsUtils.require(fixedTerm);
        dateInfo.fixedBeginTerm = ParamsUtils.require(fixedBeginTerm);
//        dateInfo.endTimestamp = endTimestamp;  暂无有效期卡券还有结束时间
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public WxCardCodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(WxCardCodeType codeType) {
        this.codeType = codeType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public SubMerchantInfo getSubMerchantInfo() {
        return subMerchantInfo;
    }

    public WxCardBaseInfo setSubMerchantInfo(SubMerchantInfo subMerchantInfo) {
        this.subMerchantInfo = subMerchantInfo;
        return this;
    }

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }

    public Boolean isUseCustomCode() {
        return useCustomCode;
    }

    public void setUseCustomCode(Boolean useCustomCode) {
        this.useCustomCode = useCustomCode;
    }

    public String getGetCustomCodeMode() {
        return getCustomCodeMode;
    }

    public void setGetCustomCodeMode(String getCustomCodeMode) {
        this.getCustomCodeMode = getCustomCodeMode;
    }

    public Boolean isBindOpenid() {
        return bindOpenid;
    }

    public void setBindOpenid(Boolean bindOpenid) {
        this.bindOpenid = bindOpenid;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public List<Long> getLocationIdList() {
        return locationIdList;
    }

    public void setLocationIdList(List<Long> locationIdList) {
        this.locationIdList = locationIdList;
    }

    public Boolean isUseAllLocations() {
        return useAllLocations;
    }

    public void setUseAllLocations(Boolean useAllLocations) {
        this.useAllLocations = useAllLocations;
    }

    public String getCenterTitle() {
        return centerTitle;
    }

    public void setCenterTitle(String centerTitle) {
        this.centerTitle = centerTitle;
    }

    public String getCenterSubTitle() {
        return centerSubTitle;
    }

    public void setCenterSubTitle(String centerSubTitle) {
        this.centerSubTitle = centerSubTitle;
    }

    public String getCenterUrl() {
        return centerUrl;
    }

    public void setCenterUrl(String centerUrl) {
        this.centerUrl = centerUrl;
    }

    public String getCenterAppBrandUserName() {
        return centerAppBrandUserName;
    }

    public void setCenterAppBrandUserName(String centerAppBrandUserName) {
        this.centerAppBrandUserName = centerAppBrandUserName;
    }

    public String getCenterAppBrandPass() {
        return centerAppBrandPass;
    }

    public void setCenterAppBrandPass(String centerAppBrandPass) {
        this.centerAppBrandPass = centerAppBrandPass;
    }

    public String getCustomUrlName() {
        return customUrlName;
    }

    public void setCustomUrlName(String customUrlName) {
        this.customUrlName = customUrlName;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public String getCustomUrlSubTitle() {
        return customUrlSubTitle;
    }

    public void setCustomUrlSubTitle(String customUrlSubTitle) {
        this.customUrlSubTitle = customUrlSubTitle;
    }

    public String getCustomAppBrandUserName() {
        return customAppBrandUserName;
    }

    public void setCustomAppBrandUserName(String customAppBrandUserName) {
        this.customAppBrandUserName = customAppBrandUserName;
    }

    public String getCustomAppBrandPass() {
        return customAppBrandPass;
    }

    public void setCustomAppBrandPass(String customAppBrandPass) {
        this.customAppBrandPass = customAppBrandPass;
    }

    public String getPromotionUrlName() {
        return promotionUrlName;
    }

    public void setPromotionUrlName(String promotionUrlName) {
        this.promotionUrlName = promotionUrlName;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    public String getPromotionUrlSubTitle() {
        return promotionUrlSubTitle;
    }

    public void setPromotionUrlSubTitle(String promotionUrlSubTitle) {
        this.promotionUrlSubTitle = promotionUrlSubTitle;
    }

    public String getPromotionAppBrandUserName() {
        return promotionAppBrandUserName;
    }

    public void setPromotionAppBrandUserName(String promotionAppBrandUserName) {
        this.promotionAppBrandUserName = promotionAppBrandUserName;
    }

    public String getPromotionAppBrandPass() {
        return promotionAppBrandPass;
    }

    public void setPromotionAppBrandPass(String promotionAppBrandPass) {
        this.promotionAppBrandPass = promotionAppBrandPass;
    }

    public Integer getGetLimit() {
        return getLimit;
    }

    public void setGetLimit(Integer getLimit) {
        this.getLimit = getLimit;
    }

    public Integer getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(Integer useLimit) {
        this.useLimit = useLimit;
    }

    public Boolean isCanShare() {
        return canShare;
    }

    public void setCanShare(Boolean canShare) {
        this.canShare = canShare;
    }

    public Boolean isCanGiveFriend() {
        return canGiveFriend;
    }

    public void setCanGiveFriend(Boolean canGiveFriend) {
        this.canGiveFriend = canGiveFriend;
    }
}
