package com.jifenke.weixin.cardCoupons.wxResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.support.WxResponse;

import java.util.List;

public class PoiCategory extends WxResponse {

    @JsonProperty("category_list")
    private List<String> categoryList;

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }
}
