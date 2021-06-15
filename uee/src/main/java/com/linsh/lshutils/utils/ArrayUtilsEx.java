package com.linsh.lshutils.utils;

import androidx.annotation.Nullable;

import com.linsh.utilseverywhere.ObjectUtils;

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

    public static int indexOf(@Nullable boolean[] array, boolean item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    public static int indexOf(@Nullable byte[] array, byte item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    public static int indexOf(@Nullable char[] array, char item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    public static int indexOf(@Nullable int[] array, int item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    public static int indexOf(@Nullable long[] array, long item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    public static int indexOf(@Nullable float[] array, float item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    public static int indexOf(@Nullable double[] array, double item) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == item)
                    return i;
            }
        }
        return -1;
    }

    public static <T> boolean contains(@Nullable T[] array, @Nullable T item) {
        if (array != null) {
            for (T t : array) {
                if (ObjectUtils.isEqual(t, item))
                    return true;
            }
        }
        return false;
    }

    public static <T> int length(@Nullable T[] array) {
        return array == null ? 0 : array.length;
    }
}
