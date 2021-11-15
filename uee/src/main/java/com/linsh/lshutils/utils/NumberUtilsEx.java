package com.linsh.lshutils.utils;

import androidx.annotation.Nullable;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2021/11/15
 *    desc   :
 * </pre>
 */
public class NumberUtilsEx {

    /**
     * 解析字符串为 byte 类型数字
     *
     * @param s            字符串
     * @param defaultValue 解析失败时，使用的默认值
     */
    public static byte parseByte(@Nullable String s, byte defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Byte.parseByte(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 解析字符串为 int 类型数字
     *
     * @param s            字符串
     * @param defaultValue 解析失败时，使用的默认值
     */
    public static int parseInt(@Nullable String s, int defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 解析字符串为 long 类型数字
     *
     * @param s            字符串
     * @param defaultValue 解析失败时，使用的默认值
     */
    public static long parseLong(@Nullable String s, long defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Long.parseLong(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 解析字符串为 float 类型数字
     *
     * @param s            字符串
     * @param defaultValue 解析失败时，使用的默认值
     */
    public static float parseFloat(@Nullable String s, float defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 解析字符串为 double 类型数字
     *
     * @param s            字符串
     * @param defaultValue 解析失败时，使用的默认值
     */
    public static double parseFloat(@Nullable String s, double defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
