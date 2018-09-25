package com.jifenke.weixin;

/**
 * @author YQ.Huang
 */
public abstract class WxException extends RuntimeException {

    public WxException(String message) {
        super(message);
    }

    public WxException(String message, Throwable cause) {
        super(message, cause);
    }
}
