package com.endofmaster.weixin.log;


import java.util.Date;
import java.util.Map;

/**
 * @author ZM.Wang
 */
public class WxMsgLog {

    private String id;
    private Date date;
    private Map<String, String> value;


    public WxMsgLog(Map<String, String> value) {
        this.id = null;
        this.date = new Date();
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, String> getValue() {
        return value;
    }
}
