package com.jifenke.weixin.cardCoupons.basic;

/**
 * @author ZM.Wang
 */
public enum WxCardCodeType {
    CODE_TYPE_TEXT,         //文本
    CODE_TYPE_BARCODE,      //一维码
    CODE_TYPE_QRCODE,       //二维码
    CODE_TYPE_ONLY_QRCODE,  //二维码无code
    CODE_TYPE_ONLY_BARCODE, //一维码无code
    CODE_TYPE_NONE,         //不显示code和条形码类型
}
