package com.jifenke.weixin.support;

import org.apache.commons.lang3.Validate;

/**
 * @author YQ.Huang
 */
public interface ParamsUtils {

    static String require(String value) {
        Validate.notBlank(value, "必选参数不可为空");
        return value;
    }

    static <T> T require(T value) {
        Validate.notNull(value, "必选参数不可为空");
        return value;
    }

    static int require(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("必须大于0的数字参数");
        }
        return num;
    }

    static long require(long num) {
        if (num < 1L) {
            throw new IllegalArgumentException("必须大于0的数字参数");
        }
        return num;
    }
}
