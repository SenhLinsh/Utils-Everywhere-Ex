package com.linsh.lshutils.utils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/12/23
 *    desc   :
 * </pre>
 */
public class StringUtilsEx {

    public static String trim(String text, char... trims) {
        int len = text.length();
        int st = 0;
        while ((st < len) && ArrayUtilsEx.indexOf(trims, text.charAt(st)) >= 0) {
            st++;
        }
        while ((st < len) && ArrayUtilsEx.indexOf(trims, text.charAt(len - 1)) >= 0) {
            len--;
        }
        return ((st > 0) || (len < text.length())) ? text.substring(st, len) : text;
    }

    public static String trimLeft(String text, char... trims) {
        int len = text.length();
        int st = 0;
        while ((st < len) && ArrayUtilsEx.indexOf(trims, text.charAt(st)) >= 0) {
            st++;
        }
        return st > 0 ? text.substring(st, len) : text;
    }

    public static String trimRight(String text, char... trims) {
        int len = text.length();
        while ((0 < len) && ArrayUtilsEx.indexOf(trims, text.charAt(len - 1)) >= 0) {
            len--;
        }
        return len < text.length() ? text.substring(0, len) : text;
    }

    public static String trimPlus(String text, char... extras) {
        int len = text.length();
        int st = 0;
        while ((st < len) && (text.charAt(st) < ' ' || ArrayUtilsEx.indexOf(extras, text.charAt(st)) >= 0)) {
            st++;
        }
        while ((st < len) && (text.charAt(len - 1) < ' ' || ArrayUtilsEx.indexOf(extras, text.charAt(len - 1)) >= 0)) {
            len--;
        }
        return ((st > 0) || (len < text.length())) ? text.substring(st, len) : text;
    }

    public static String trimLeftPlus(String text, char... extras) {
        int len = text.length();
        int st = 0;
        while ((st < len) && (text.charAt(st) < ' ' || ArrayUtilsEx.indexOf(extras, text.charAt(st)) >= 0)) {
            st++;
        }
        return st > 0 ? text.substring(st, len) : text;
    }

    public static String trimRightPlus(String text, char... extras) {
        int len = text.length();
        while ((0 < len) && (text.charAt(len - 1) < ' ' || ArrayUtilsEx.indexOf(extras, text.charAt(len - 1)) >= 0)) {
            len--;
        }
        return len < text.length() ? text.substring(0, len) : text;
    }

}
