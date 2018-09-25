package com.endofmaster.weixin.cardCoupons.request;

import com.endofmaster.weixin.cardCoupons.WxPoiBaseInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ZM.Wang
 * 创建微信门店数据传输类
 */
public class CreateWxPoiRequest implements WxRequest {

    @JsonProperty("base_info")
    private WxPoiBaseInfo baseInfo;

    public CreateWxPoiRequest(String sid, String businessName, String branchName, String province, String city, String district, String address, String telephone, String category, double longitude, double latitude) {
        baseInfo = new WxPoiBaseInfo();
        baseInfo.setSid(sid);
        baseInfo.setBusinessName(businessName);
        baseInfo.setBranchName(branchName);
        baseInfo.setProvince(province);
        baseInfo.setCity(city);
        baseInfo.setDistrict(district);
        baseInfo.setAddress(address);
        baseInfo.setTelephone(telephone);
        baseInfo.setCategories(Collections.singletonList(category));
        baseInfo.setOffsetType(1);
        baseInfo.setLongitude(longitude);
        baseInfo.setLatitude(latitude);
    }

    public void setPhotoList(List<String> urls) {
        if (urls == null || urls.size() < 1) {
            return;
        }
        List<WxPoiBaseInfo.PhotoList> lists = new ArrayList<>();
        for (String url : urls) {
            lists.add(new WxPoiBaseInfo.PhotoList(url));
        }
        baseInfo.setPhotoList(lists);
    }

    public void setRecommend(String recommend) {
        baseInfo.setRecommend(recommend);
    }


    public void setSpecial(String special) {
        baseInfo.setSpecial(special);
    }


    public void setIntroduction(String introduction) {
        baseInfo.setIntroduction(introduction);
    }

    public void setOpenTime(String openTime) {
        baseInfo.setOpenTime(openTime);
    }

    public void setAvgPrice(Integer avgPrice) {
        baseInfo.setAvgPrice(avgPrice);
    }

    public WxPoiBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(WxPoiBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    CreateWxPoiRequest() {
    }
}
