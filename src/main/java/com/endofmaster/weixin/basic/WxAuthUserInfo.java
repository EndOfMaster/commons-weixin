package com.endofmaster.weixin.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.endofmaster.weixin.support.WxResponse;

import java.util.List;

/**
 * 通过用户授权获取的用户信息
 *
 * @author YQ.Huang
 * @author ZM.Wang
 */
public class WxAuthUserInfo extends WxResponse {

    private String subscribe;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("nickname")
    private String nickName;

    private Integer sex;

    private String country;

    private String province;

    private String city;

    private String language;

    @JsonProperty("headimgurl")
    private String headImgUrl;

    @JsonProperty("subscribe_time")
    private String subscribeTime;

    @JsonProperty("unionid")
    private String unionId;

    private List<String> privilege;

    public String getSubscribe() {
        return subscribe;
    }

    public String getOpenId() {
        return openId;
    }

    public String getNickName() {
        return nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public String getLanguage() {
        return language;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    @Override
    public String toString() {
        return "WxAuthUserInfo{" +
                "subscribe='" + subscribe + '\'' +
                ", openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", language='" + language + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", subscribeTime='" + subscribeTime + '\'' +
                ", unionId='" + unionId + '\'' +
                ", privilege=" + privilege +
                '}';
    }
}
