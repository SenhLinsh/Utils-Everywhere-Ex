package com.linsh.lshutils.utils;

import androidx.annotation.Nullable;

import com.linsh.utilseverywhere.ObjectUtils;

import java.lang.reflect.Array;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/11/17
 *    desc   :
 * </pre>
 */
public class ArrayUtilsEx {

    /**
     * 将字符串解析成数组
     *
     * @param s 字符串
     */
    @Nullable
    public static String[] fromString(@Nullable String s) {
        if (s == null)
            return null;
        if (!s.startsWith("[") || !s.endsWith("]"))
            return null;
        String content = s.substring(1, s.length() - 1);
        String[] args = content.split(",");
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
        }
        return args;
    }

    /**
     * 查找元素在数组中的索引
     *
     * @param array 数组
     * @param item  数组中的元素
     * @return 索引, 如果不存在则返回 -1
     */
    public static <T> int indexOf(@Nullable T[] array, @Nullable T item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 查找索引
     *
     * @param array 数组
     * @param item  元素
     * @return 指定元素所在数组的索引，如果不存在则返回 -1
     */
    public static int indexOf(@Nullable boolean[] array, boolean item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 查找索引
     *
     * @param array 数组
     * @param item  元素
     * @return 指定元素所在数组的索引，如果不存在则返回 -1
     */
    public static int indexOf(@Nullable byte[] array, byte item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 查找索引
     *
     * @param array 数组
     * @param item  元素
     * @return 指定元素所在数组的索引，如果不存在则返回 -1
     */
    public static int indexOf(@Nullable char[] array, char item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 查找索引
     *
     * @param array 数组
     * @param item  元素
     * @return 指定元素所在数组的索引，如果不存在则返回 -1
     */
    public static int indexOf(@Nullable int[] array, int item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 查找索引
     *
     * @param array 数组
     * @param item  元素
     * @return 指定元素所在数组的索引，如果不存在则返回 -1
     */
    public static int indexOf(@Nullable long[] array, long item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 查找索引
     *
     * @param array 数组
     * @param item  元素
     * @return 指定元素所在数组的索引，如果不存在则返回 -1
     */
    public static int indexOf(@Nullable float[] array, float item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 查找索引
     *
     * @param array 数组
     * @param item  元素
     * @return 指定元素所在数组的索引，如果不存在则返回 -1
     */
    public static int indexOf(@Nullable double[] array, double item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 判断是否包含元素
     *
     * @param array 数组
     * @param item  元素
     * @return 指定元素存在于数组中
     */
    public static <T> boolean contains(@Nullable T[] array, @Nullable T item) {
        if (array != null) {
            for (T t : array) {
                if (ObjectUtils.isEqual(t, item))
                    return true;
            }
        }
        return false;
    }

    /**
     * 获取数组长度
     *
     * @param array 数组
     * @return 数组长度，数组为空时返回 0
     */
    public static <T> int length(@Nullable final T[] array) {
        return array == null ? 0 : array.length;
    }

    /**
     * 数组瘦身，移除为空的元素
     *
     * @param array 数组
     * @return 如果不存在为空的元素，将返回原数组；否则将创建一个新的数组
     */
    public static <T> T[] trim(@Nullable final T[] array) {
        if (array == null || array.length == 0)
            return array;
        int count = 0;
        for (T t : array) {
            if (t != null) count++;
        }
        if (count == array.length)
            return array;
        T[] ret = (T[]) Array.newInstance(array.getClass().getComponentType(), count);
        count = 0;
        for (int i = 0; i < array.length; i++) {
            T t = array[i];
            if (t != null) ret[count++] = t;
        }
        return ret;
    }
}
