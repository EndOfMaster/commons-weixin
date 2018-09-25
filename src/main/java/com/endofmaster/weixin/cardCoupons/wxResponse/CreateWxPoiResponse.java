package com.endofmaster.weixin.cardCoupons.wxResponse;

import com.endofmaster.weixin.support.WxResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

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
