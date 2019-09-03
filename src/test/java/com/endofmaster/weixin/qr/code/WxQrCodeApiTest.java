package com.endofmaster.weixin.qr.code;

import org.junit.Test;

public class WxQrCodeApiTest {

    private final WxQrCodeApi wxQrCodeApi = new WxQrCodeApi();

    @Test
    public void test() {
        String url = wxQrCodeApi.getTemporaryQrCodeUrl(100, "25_4pTJ7vmuhbb2rRib9ppsOkH8_seXEi3Bf4PU5VM4kerCrc_gRV9QjnDnqWaw3bSqE56tNdvbNep_2gNS1KEL4fIH8UVb0yqDcfWrkWyh4aDZrF0O_xWvBpF10uNrzEAeCPYDWcVnAN5hbNMrXAFaAGABEJ", WxQrCodeType.TEMPORARY);
        System.err.println(url);
    }

}