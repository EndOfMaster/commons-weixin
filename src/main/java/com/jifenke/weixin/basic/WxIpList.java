package com.jifenke.weixin.basic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.support.WxResponse;

import java.util.List;

/**
 * @author YQ.Huang
 */
public class WxIpList extends WxResponse {

    @JsonProperty("ip_list")
    private List<String> ipList;

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }

    @Override
    public String toString() {
        return "WxIpList{" +
                "ipList=" + ipList +
                '}';
    }
}
