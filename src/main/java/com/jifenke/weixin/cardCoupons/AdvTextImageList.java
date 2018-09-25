package com.jifenke.weixin.cardCoupons;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 * 卡券高级参数中的图文列表对象
 */
public class AdvTextImageList {

    @JsonProperty("image_url")
    private String imageUrl;                    //图片链接，必须调用上传图片接口上传图片获得链接，并在此填入，否则报错
    private String text;

    public AdvTextImageList(String imageUrl, String text) {
        this.imageUrl = imageUrl;
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AdvTextImageList setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getText() {
        return text;
    }

    public AdvTextImageList setText(String text) {
        this.text = text;
        return this;
    }
}
