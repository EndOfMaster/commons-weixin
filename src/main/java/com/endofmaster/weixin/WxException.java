package com.endofmaster.weixin;

/**
 * @author YQ.Huang
 */
public class WxException extends RuntimeException {

    public WxException(String message) {
        super(message);
    }

    public WxException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxException(Throwable cause) {
        super(cause);
    }
}
