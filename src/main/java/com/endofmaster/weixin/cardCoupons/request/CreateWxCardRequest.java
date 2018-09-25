package com.endofmaster.weixin.cardCoupons.request;

import com.endofmaster.weixin.cardCoupons.*;
import com.endofmaster.weixin.cardCoupons.basic.WxCardCodeType;
import com.endofmaster.weixin.cardCoupons.basic.WxCardDateType;
import com.endofmaster.weixin.cardCoupons.basic.WxCardType;
import com.endofmaster.weixin.cardCoupons.basic.WxCardTypeExtraConstant;
import com.endofmaster.weixin.cardCoupons.basic.WxMerServiceType;
import com.endofmaster.weixin.support.ParamsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.endofmaster.commons.util.validate.ParamUtils.findParam;


/**
 * @author ZM.Wang
 * 创建微信卡券数据传输类
 */
public class CreateWxCardRequest implements WxRequest {

    private WxCardBaseInfo baseInfo;
    private WxCardAdvancedInfo advancedInfo;
    private WxCardType cardType;
    private Map<String, Object> extraParams;

    public CreateWxCardRequest(WxCardType cardType, long wxMerchantId, String logoUrl, WxCardCodeType codeType, String brandName, String title, String color, String notice, String description, long quantity) {
        this.cardType = cardType;
        baseInfo = new WxCardBaseInfo();
        baseInfo.setSubMerchantInfo(new WxCardBaseInfo.SubMerchantInfo().setMerchantId(wxMerchantId));
        baseInfo.setLogoUrl(logoUrl);
        baseInfo.setCodeType(codeType);
        baseInfo.setBrandName(brandName);
        baseInfo.setTitle(title);
        baseInfo.setColor(color);
        baseInfo.setNotice(notice);
        baseInfo.setDescription(description);
        baseInfo.setSku(new WxCardBaseInfo.Sku().setQuantity(ParamsUtils.require(quantity)));
    }

    public CreateWxCardRequest setDateInfo(WxCardDateType dateType, Long begin, Long end) {
        if (dateType == WxCardDateType.TIME_RANGE) {
            baseInfo.timeRange(begin / 1000, end / 1000);
        } else {
            baseInfo.timeFixTerm(end, begin, end);
        }
        return this;
    }

    public AbstractWxCardInfo getWxCardInfo() {
        switch (cardType) {
            case GROUPON:
                return new WxGrouponCardInfo(baseInfo, advancedInfo, findParam(extraParams, WxCardTypeExtraConstant.GROUPON_EXTRA_PARAM, String.class));
            case CASH:
                return new WxCashCardInfo(baseInfo, advancedInfo, findParam(extraParams, WxCardTypeExtraConstant.CASH_EXTRA_PARAM1, Integer.class),
                        findParam(extraParams, WxCardTypeExtraConstant.CASH_EXTRA_PARAM2, Integer.class));
            case DISCOUNT:
                return new WxDiscountCardInfo(baseInfo, advancedInfo, findParam(extraParams, WxCardTypeExtraConstant.DISCOUNT_EXTRA_PARAM, Integer.class));
            case GIFT:
                return new WxGiftCardInfo(baseInfo, advancedInfo, findParam(extraParams, WxCardTypeExtraConstant.GIFT_EXTRA_PARAM, String.class));
            case GENERAL_COUPON:
                return new WxGeneralCouponCardInfo(baseInfo, advancedInfo, findParam(extraParams, WxCardTypeExtraConstant.GENERA_COUPON_EXTRA_PARAM, String.class));
            default:
                throw new IllegalArgumentException("没有该类型卡券：" + cardType);
        }
    }

    /**
     * 创建高级参数对象
     */
    public CreateWxCardRequest addAdvancedInfo() {
        this.advancedInfo = new WxCardAdvancedInfo();
        return this;
    }

    //使用门槛相关参数
    public WxCardAdvancedInfo setAcceptCategory(String acceptCategory) {
        if (advancedInfo.getUseCondition() == null) {
            advancedInfo.addUseCondition();
        }
        return advancedInfo.setAcceptCategory(acceptCategory);
    }

    public WxCardAdvancedInfo setRejectCategory(String rejectCategory) {
        if (advancedInfo.getUseCondition() == null) {
            advancedInfo.addUseCondition();
        }
        return advancedInfo.setRejectCategory(rejectCategory);
    }

    public WxCardAdvancedInfo setLeastCost(Integer leastCost) {
        if (advancedInfo.getUseCondition() == null) {
            advancedInfo.addUseCondition();
        }
        return advancedInfo.setLeastCost(leastCost);
    }

    public WxCardAdvancedInfo setObjectUseFor(String objectUseFor) {
        if (advancedInfo.getUseCondition() == null) {
            advancedInfo.addUseCondition();
        }
        return advancedInfo.setObjectUseFor(objectUseFor);
    }

    public WxCardAdvancedInfo setCanUseWithOtherDiscount(Boolean canUseWithOtherDiscount) {
        if (advancedInfo.getUseCondition() == null) {
            advancedInfo.addUseCondition();
        }
        return advancedInfo.setCanUseWithOtherDiscount(canUseWithOtherDiscount);
    }

    //封面摘要的可选参数
    public WxCardAdvancedInfo setInAbstract(String inAbstract) {
        if (advancedInfo.getAdAbstract() == null) {
            advancedInfo.addAbstract();
        }
        return advancedInfo.setInAbstract(inAbstract);
    }

    public WxCardAdvancedInfo setIconUrlList(List<String> iconUrlList) {
        if (advancedInfo.getAdAbstract() == null) {
            advancedInfo.addAbstract();
        }
        return advancedInfo.setIconUrlList(iconUrlList);
    }

    //高级参数中的图文列表
    public WxCardAdvancedInfo setTextImageLists(List<AdvTextImageList> textImageLists) {
        return advancedInfo.setTextImageLists(textImageLists);
    }

    public void setBusinessService(List<WxMerServiceType> businessService) {
        if (businessService != null && businessService.size() > 0) {
            List<String> services = new ArrayList<>();
            businessService.forEach(type -> services.add(type.getValue()));
            advancedInfo.setBusinessService(services);
        }
    }

    public WxCardAdvancedInfo setTimeLimits(List<AdvTimeLimit> timeLimits) {
        return advancedInfo.setTimeLimits(timeLimits);
    }

    //基础参数中的可选参数
    public void setUseCustomCode(Boolean useCustomCode) {
        baseInfo.setUseCustomCode(useCustomCode);
    }

    public void setGetCustomCodeMode(String getCustomCodeMode) {
        baseInfo.setGetCustomCodeMode(getCustomCodeMode);
    }

    public void setBindOpenid(Boolean bindOpenid) {
        baseInfo.setBindOpenid(bindOpenid);
    }

    public void setServicePhone(String servicePhone) {
        baseInfo.setServicePhone(servicePhone);
    }

    public void setLocationIdList(List<Long> locationIdList) {
        baseInfo.setLocationIdList(locationIdList);
    }

    public void setUseAllLocations(Boolean useAllLocations) {
        baseInfo.setUseAllLocations(useAllLocations);
    }

    public void setCenterTitle(String centerTitle) {
        baseInfo.setCenterTitle(centerTitle);
    }

    public void setCenterSubTitle(String centerSubTitle) {
        baseInfo.setCenterSubTitle(centerSubTitle);
    }

    public void setCenterUrl(String centerUrl) {
        baseInfo.setCenterUrl(centerUrl);
    }

    public void setCenterAppBrandUserName(String centerAppBrandUserName) {
        baseInfo.setCenterAppBrandUserName(centerAppBrandUserName);
    }

    public void setCenterAppBrandPass(String centerAppBrandPass) {
        baseInfo.setCenterAppBrandPass(centerAppBrandPass);
    }

    public void setCustomUrlName(String customUrlName) {
        baseInfo.setCustomUrlName(customUrlName);
    }

    public void setCustomUrl(String customUrl) {
        baseInfo.setCustomUrl(customUrl);
    }

    public void setCustomUrlSubTitle(String customUrlSubTitle) {
        baseInfo.setCustomUrlSubTitle(customUrlSubTitle);
    }

    public void setCustomAppBrandUserName(String customAppBrandUserName) {
        baseInfo.setCustomAppBrandUserName(customAppBrandUserName);
    }

    public void setCustomAppBrandPass(String customAppBrandPass) {
        baseInfo.setCustomAppBrandPass(customAppBrandPass);
    }

    public void setPromotionUrlName(String promotionUrlName) {
        baseInfo.setPromotionUrlName(promotionUrlName);
    }

    public void setPromotionUrl(String promotionUrl) {
        baseInfo.setPromotionUrl(promotionUrl);
    }

    public void setPromotionUrlSubTitle(String promotionUrlSubTitle) {
        baseInfo.setPromotionUrlSubTitle(promotionUrlSubTitle);
    }

    public void setPromotionAppBrandUserName(String promotionAppBrandUserName) {
        baseInfo.setPromotionAppBrandUserName(promotionAppBrandUserName);
    }

    public void setPromotionAppBrandPass(String promotionAppBrandPass) {
        baseInfo.setPromotionAppBrandPass(promotionAppBrandPass);
    }

    public void setGetLimit(Integer getLimit) {
        baseInfo.setGetLimit(getLimit);
    }

    public void setUseLimit(Integer useLimit) {
        baseInfo.setUseLimit(useLimit);
    }

    public void setCanShare(Boolean canShare) {
        baseInfo.setCanShare(canShare);
    }

    public void setCanGiveFriend(Boolean canGiveFriend) {
        baseInfo.setCanGiveFriend(canGiveFriend);
    }

    public CreateWxCardRequest setExtraParams(Map<String, Object> extraParams) {
        this.extraParams = extraParams;
        return this;
    }

    CreateWxCardRequest() {
    }

    public WxCardBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public WxCardAdvancedInfo getAdvancedInfo() {
        return advancedInfo;
    }

    public WxCardType getCardType() {
        return cardType;
    }

    public Map<String, Object> getExtraParams() {
        return extraParams;
    }
}
