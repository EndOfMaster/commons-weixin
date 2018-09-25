package com.endofmaster.weixin;

import com.endofmaster.weixin.support.WxHttpClient;
import org.apache.commons.lang3.Validate;

/**
 * @author YQ.Huang
 */
public abstract class WxApi {

    protected final WxHttpClient client;

    public WxApi(WxHttpClient client) {
        Validate.notNull(client);
        this.client = client;
    }

}
