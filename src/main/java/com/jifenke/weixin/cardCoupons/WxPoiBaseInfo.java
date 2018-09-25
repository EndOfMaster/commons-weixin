package com.jifenke.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.cardCoupons.basic.WxPoiOffsetType;

import java.util.List;

/**
 * @author ZM.Wang
 * 微信门店基本信息，创建和查询共用
 */
public class WxPoiBaseInfo {

    private String sid;
    @JsonProperty("business_name")
    private String businessName;        //门店名称
    @JsonProperty("branch_name")
    private String branchName;          //分店名称
    private String province;            //省份
    private String city;                //市
    private String district;            //区
    private String address;             //门店所在的详细街道地址（不要填写省市信息）
    private String telephone;           //门店电话
    private List<String> categories;    //门店的类型（不同级分类用“,”隔开，如：美食，川菜，火锅。详细分类参见附件：微信门店类目表）
    @JsonProperty("offset_type")
    private int offsetType;             //坐标类型：1 为火星坐标 2 为sogou经纬度 3 为百度经纬度 4 为mapbar经纬度 5 为GPS坐标 6 为sogou墨卡托坐标 注：高德经纬度无需转换可直接使用
    private double longitude;           //门店经度
    private double latitude;            //纬度

        /*------------下面为可选参数-------------*/

    @JsonProperty("photo_list")
    private List<PhotoList> photoList;  //图片列表，url 形式，可以有多张图片，尺寸为 640*340px。
    private String recommend;           //推荐品，餐厅可为推荐菜；酒店为推荐套房；景点为推荐游玩景点等，针对自己行业的推荐内容
    private String special;             //特色服务，如免费wifi，免费停车，送货上门等商户能提供的特色功能或服务
    private String introduction;        //商户简介，主要介绍商户信息等
    @JsonProperty("open_time")
    private String openTime;           //营业时间，24 小时制表示，用“-”连接，如8:00-20:00
    @JsonProperty("avg_price")
    private Integer avgPrice;           //人均价格，大于0 的整数

    public static class PhotoList {

        @JsonProperty("photo_url")
        String photoUrl;

        public PhotoList(String url) {
            this.photoUrl = url;
        }

        PhotoList() {
        }

        public String getPhotoUrl() {
            return photoUrl;
        }
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getOffsetType() {
        return offsetType;
    }

    public WxPoiOffsetType getOffsetEnumType() {
        return WxPoiOffsetType.valueOf(offsetType);
    }

    public void setOffsetType(int offsetType) {
        this.offsetType = offsetType;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<PhotoList> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoList> photoList) {
        this.photoList = photoList;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
    }
}
