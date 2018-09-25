package com.jifenke.weixin.basic;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 */
public class WxUserInfo {
    private String subscribe;

    @JsonProperty("openid")
    private String openId;

    private String nickname;
    private String sex;
    private String city;
    private String country;
    private String province;
    private String language;

    @JsonProperty("headimgurl")
    private String headImgUrl;

    @JsonProperty("subscribe_time")
    private String subscribeTime;

    @JsonProperty("unionid")
    private String unionId;

    private String remark;

    @JsonProperty("groupid")
    private String groupId;

    @JsonProperty("tagid_list")
    private String tagidList;

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList;
    }

    @Override
    public String toString() {
        return "WxUserInfo{" +
                "subscribe='" + subscribe + '\'' +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", language='" + language + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", subscribeTime='" + subscribeTime + '\'' +
                ", unionId='" + unionId + '\'' +
                ", remark='" + remark + '\'' +
                ", groupId='" + groupId + '\'' +
                ", tagidList='" + tagidList + '\'' +
                '}';
    }
}
