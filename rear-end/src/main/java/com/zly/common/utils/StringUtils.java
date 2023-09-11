package com.zly.common.utils;

/**
 * string 工具类
 */
public class StringUtils {

    private StringUtils() {

    }

    public static boolean isBlank(String s) {
        return null == s || "".equals(trim(s));
    }

    public static boolean isNotBlank(String s) {
        return null != s && !"".equals(trim(s));
    }

    public static String trim(String s) {
        return s.trim();
    }
}
