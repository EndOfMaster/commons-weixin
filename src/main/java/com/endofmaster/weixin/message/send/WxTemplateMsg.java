package com.endofmaster.weixin.message.send;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YQ.Huang
 */
public class WxTemplateMsg {
    private String openId;
    private String templateId;
    private String url;
    private String formId;
    private Map<String, DataItem> data;

    public WxTemplateMsg setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public WxTemplateMsg setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public WxTemplateMsg setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getFormId() {
        return formId;
    }

    public WxTemplateMsg setFormId(String formId) {
        this.formId = formId;
        return this;
    }

    public WxTemplateMsg setData(Map<String, DataItem> data) {
        this.data = data;
        return this;
    }

    public WxTemplateMsg addDataItem(String name, String value, String color) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(name, new DataItem(value, color));
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, DataItem> getData() {
        return data;
    }

    public static class DataItem {
        private final String value;
        private final String color;

        public DataItem(String value) {
            this(value, null);
        }

        public DataItem(String value, String color) {
            this.value = value;
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public String getColor() {
            return color;
        }
    }
}
