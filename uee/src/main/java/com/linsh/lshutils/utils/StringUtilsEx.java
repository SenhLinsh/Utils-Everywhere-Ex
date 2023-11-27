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

    /**
     * 对数组中的每个元素进行 trim
     */
    public static String[] trimArr(String[] texts) {
        if (texts == null) return null;
        for (int i = 0; i < texts.length; i++) {
            texts[i] = trim(texts[i]);
        }
        return texts;
    }

    /**
     * 自定义字符的 trim
     */
    public static String trim(String text, char... trims) {
        if (text == null)
            return null;
        if (trims == null || trims.length == 0) {
            trims = new char[]{' '};
        }
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

    /**
     * 自定义字符的左向 trim
     */
    public static String trimLeft(String text, char... trims) {
        if (text == null)
            return null;
        if (trims == null || trims.length == 0) {
            trims = new char[]{' '};
        }
        int len = text.length();
        int st = 0;
        while ((st < len) && ArrayUtilsEx.indexOf(trims, text.charAt(st)) >= 0) {
            st++;
        }
        return st > 0 ? text.substring(st, len) : text;
    }

    /**
     * 自定义字符的右向 trim
     */
    public static String trimRight(String text, char... trims) {
        if (text == null)
            return null;
        if (trims == null || trims.length == 0) {
            trims = new char[]{' '};
        }
        int len = text.length();
        while ((0 < len) && ArrayUtilsEx.indexOf(trims, text.charAt(len - 1)) >= 0) {
            len--;
        }
        return len < text.length() ? text.substring(0, len) : text;
    }

    /**
     * 默认字符 + 自定义字符的 trim
     */
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

    /**
     * 默认字符 + 自定义字符的左向 trim
     */
    public static String trimLeftPlus(String text, char... extras) {
        int len = text.length();
        int st = 0;
        while ((st < len) && (text.charAt(st) < ' ' || ArrayUtilsEx.indexOf(extras, text.charAt(st)) >= 0)) {
            st++;
        }
        return st > 0 ? text.substring(st, len) : text;
    }

    /**
     * 默认字符 + 自定义字符的右向 trim
     */
    public static String trimRightPlus(String text, char... extras) {
        int len = text.length();
        while ((0 < len) && (text.charAt(len - 1) < ' ' || ArrayUtilsEx.indexOf(extras, text.charAt(len - 1)) >= 0)) {
            len--;
        }
        return len < text.length() ? text.substring(0, len) : text;
    }

    /**
     * 自定义多个字符的 indexOf
     */
    public static int indexOf(String text, char... chs) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (char ch : chs) {
                if (c == ch) return i;
            }
        }
        return -1;
    }

    /**
     * 自定义多个字符的 indexOf
     */
    public static int indexOf(String text, int fromIndex, char... chs) {
        for (int i = fromIndex; i < text.length(); i++) {
            char c = text.charAt(i);
            for (char ch : chs) {
                if (c == ch) return i;
            }
        }
        return -1;
    }

    /**
     * 按指定次数拼接字符串
     *
     * @param text 需要拼接的字符串
     * @param num  拼接次数
     */
    public static String jointStr(String text, int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(text);
        }
        return builder.toString();
    }

    /**
     * 判断字符串是否以指定的字符开头
     *
     * @param text 字符串
     * @param c    字符
     */
    public static boolean startsWith(String text, char c) {
        return text.length() > 0 && text.charAt(0) == c;
    }
}
