package com.jifenke.weixin.cardCoupons.wxResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.support.WxResponse;

/**
 * @author ZM.Wang
 */
public class CreateWxPoiResponse extends WxResponse {

    @JsonProperty("poi_id")
    private long poiId;

    public long getPoiId() {
        return poiId;
    }

    public void setPoiId(long poiId) {
        this.poiId = poiId;
    }
}
