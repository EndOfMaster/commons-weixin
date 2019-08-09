package com.endofmaster.weixin;

/**
 * @author YQ.Huang
 */
public class WxServerException extends WxException {

    public WxServerException(String message) {
        super(message);
    }

    public WxServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxServerException(Throwable cause) {
        super(cause);
    }
}
