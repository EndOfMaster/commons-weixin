package com.endofmaster.weixin.qr.code;

public enum WxQrCodeType {

    TEMPORARY("QR_STR_SCENE"),//临时
    PERMANENT("QR_LIMIT_STR_SCENE"); //永久

    private final String param;

    WxQrCodeType(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
