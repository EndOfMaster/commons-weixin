package com.jifenke.weixin.support;

import java.util.Map;

/**
 * @author ZM.Wang
 */
public class MapUtils {

    @SuppressWarnings("unchecked")
    public static void putIfNotNull(Map map, Object key, Object value) {
        if (value != null) {
            map.put(key, value);
        }
    }
}
