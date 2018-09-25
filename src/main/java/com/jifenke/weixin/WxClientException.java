package com.jifenke.weixin;

/**
 * @author YQ.Huang
 */
public class WxClientException extends WxException {

    public WxClientException(String message) {
        super(message);
    }

    public WxClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
