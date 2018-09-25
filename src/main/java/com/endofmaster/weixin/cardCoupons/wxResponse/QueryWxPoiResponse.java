package com.endofmaster.weixin.cardCoupons.wxResponse;

import com.endofmaster.weixin.cardCoupons.WxPoiBaseInfo;
import com.endofmaster.weixin.cardCoupons.basic.WxPoiAvailableState;
import com.endofmaster.weixin.cardCoupons.basic.WxPoiUpdataStatus;
import com.endofmaster.weixin.support.WxResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ZM.Wang
 * 微信门店查询
 */
public class QueryWxPoiResponse extends WxResponse {

    private Business business;

    public static class Business {

        @JsonProperty("base_info")
        private BaseInfo baseInfo;

        public BaseInfo getBaseInfo() {
            return baseInfo;
        }

        public void setBaseInfo(BaseInfo baseInfo) {
            this.baseInfo = baseInfo;
        }
    }

    public static class BaseInfo extends WxPoiBaseInfo {
        @JsonProperty("available_state")
        private Integer availableState;  //门店是否可用状态。1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回。当该字段为1、2、4 状态时，poi_id 为空

        @JsonProperty("update_status")
        private Integer updateStatus;    //扩展字段是否正在更新中。1 表示扩展字段正在更新中，尚未生效，不允许再次更新； 0 表示扩展字段没有在更新中或更新已生效，可以再次更新

        public int getAvailableState() {
            return availableState;
        }

        public WxPoiAvailableState getAvailableEnumState() {
            return WxPoiAvailableState.valueOf(availableState);
        }

        public void setAvailableState(Integer availableState) {
            this.availableState = availableState;
        }

        public int getUpdateStatus() {
            return updateStatus;
        }

        public WxPoiUpdataStatus getUpdateEnumStatus() {
            return WxPoiUpdataStatus.valueOf(updateStatus);
        }

        public void setUpdateStatus(Integer updateStatus) {
            this.updateStatus = updateStatus;
        }
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
