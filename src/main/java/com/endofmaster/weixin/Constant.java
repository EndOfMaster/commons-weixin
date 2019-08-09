package com.endofmaster.weixin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.entity.ContentType;

import java.nio.charset.Charset;

public abstract class Constant {

    public static final String CHARSET = "UTF-8";

    public static final Charset CHARSET_OBJ = Charset.forName(CHARSET);
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    public static final String REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    public static final ContentType CONTENT_TYPE = ContentType.create("text/xml", "UTF-8");

    public static final XmlMapper XML_MAPPER = (XmlMapper) new XmlMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

}
