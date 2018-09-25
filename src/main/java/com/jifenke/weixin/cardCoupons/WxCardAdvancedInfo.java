package com.jifenke.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.util.Asserts;

import java.time.DayOfWeek;
import java.util.List;

/**
 * @author ZM.Wang
 */
public class WxCardAdvancedInfo {

    @JsonProperty("use_condition")
    private UseCondition useCondition;      //使用门槛（条件）字段，若不填写使用条件则在券面拼写：无最低消费限制，全场通用，不限品类；并在使用说明显示：可与其他优惠共享

    @JsonProperty("abstract")
    private Abstract adAbstract;

    @JsonProperty("text_image_list")
    private List<AdvTextImageList> textImageLists;

    @JsonProperty("time_limit")
    private List<AdvTimeLimit> timeLimits;

    @JsonProperty("business_service")
    private List<String> businessService;

    static class UseCondition {
        @JsonProperty("accept_category")
        String acceptCategory;              //指定可用的商品类目，仅用于代金券类型，填入后将在券面拼写适用于xxx

        @JsonProperty("reject_category")
        String rejectCategory;              //指定不可用的商品类目，仅用于代金券类型，填入后将在券面拼写不适用于xxxx

        @JsonProperty("least_cost")
        Integer leastCost;                  //满减门槛字段，可用于兑换券和代金券，填入后将在全面拼写消费满xx元可用。

        @JsonProperty("object_use_for")
        String objectUseFor;                //购买xx可用类型门槛，仅用于兑换，填入后自动拼写购买xxx可用。

        @JsonProperty("can_use_with_other_discount")
        Boolean canUseWithOtherDiscount;    //拼写“不可与其他优惠共享”,填写true时系统将在使用须知里拼写“可与其他优惠共享”，默认为true

    }

    public void addUseCondition() {
        this.useCondition = new UseCondition();
    }

    public WxCardAdvancedInfo setAcceptCategory(String acceptCategory) {
        Asserts.notNull(useCondition, "useCondition");
        useCondition.acceptCategory = acceptCategory;
        return this;
    }

    public WxCardAdvancedInfo setRejectCategory(String rejectCategory) {
        Asserts.notNull(useCondition, "useCondition");
        useCondition.rejectCategory = rejectCategory;
        return this;
    }

    public WxCardAdvancedInfo setLeastCost(Integer leastCost) {
        Asserts.notNull(useCondition, "useCondition");
        useCondition.leastCost = leastCost;
        return this;
    }

    public WxCardAdvancedInfo setObjectUseFor(String objectUseFor) {
        Asserts.notNull(useCondition, "useCondition");
        useCondition.objectUseFor = objectUseFor;
        return this;
    }

    public WxCardAdvancedInfo setCanUseWithOtherDiscount(Boolean canUseWithOtherDiscount) {
        Asserts.notNull(useCondition, "useCondition");
        useCondition.canUseWithOtherDiscount = canUseWithOtherDiscount;
        return this;
    }

    static class Abstract {
        @JsonProperty("abstract")
        String inAbstract;                  //封面摘要简介。

        @JsonProperty("icon_url_list")
        List<String> iconUrlList;           //封面图片列表，仅支持填入一个封面图片链接，上传图片接口上传获取图片获得链接，填写非CDN链接会报错，并在此填入。建议图片尺寸像素850*350

    }

    public void addAbstract() {
        this.adAbstract = new Abstract();
    }

    public WxCardAdvancedInfo setInAbstract(String inAbstract) {
        Asserts.notBlank(inAbstract, "inAbstract");
        this.adAbstract.inAbstract = inAbstract;
        return this;
    }

    public WxCardAdvancedInfo setIconUrlList(List<String> iconUrlList) {
        Asserts.notNull(iconUrlList, "iconUrlList");
        if (iconUrlList.size() < 1) {
            return this;
        }
        this.adAbstract.iconUrlList = iconUrlList;
        return this;
    }

    public UseCondition getUseCondition() {
        return useCondition;
    }

    public void setUseCondition(UseCondition UseCondition) {
        this.useCondition = UseCondition;
    }

    public Abstract getAdAbstract() {
        return adAbstract;
    }

    public void setAdAbstract(Abstract adAbstract) {
        this.adAbstract = adAbstract;
    }

    public List<AdvTextImageList> getTextImageLists() {
        return textImageLists;
    }

    public WxCardAdvancedInfo setTextImageLists(List<AdvTextImageList> textImageLists) {
        this.textImageLists = textImageLists;
        return this;
    }

    public List<AdvTimeLimit> getTimeLimits() {
        return timeLimits;
    }

    public WxCardAdvancedInfo setTimeLimits(List<AdvTimeLimit> timeLimits) {
        this.timeLimits = timeLimits;
        return this;
    }

    public List<String> getBusinessService() {
        return businessService;
    }

    public WxCardAdvancedInfo setBusinessService(List<String> businessService) {
        this.businessService = businessService;
        return this;
    }
}
