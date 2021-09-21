package com.linsh.lshutils.utils;

import com.linsh.utilseverywhere.interfaces.Convertible;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/08/25
 *    desc   :
 * </pre>
 */
public class ListUtilsEx {

    private ListUtilsEx() {
    }

    /**
     * 获取集合长度（空指针判断）
     *
     * @param list 集合
     * @return 集合长度，集合为 null 时返回 0
     */
    public static int size(List<?> list) {
        return list == null ? 0 : list.size();
    }

    /**
     * 查找元素在集合中的索引
     *
     * @param list 集合
     * @param item 集合中的元素
     * @return 索引, 如果不存在则返回 -1
     */
    public static <T> int indexOf(List<T> list, T item) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == item)
                    return i;
            }
        }
        return -1;
    }

    /**
     * 排序
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        Collections.sort(list);
    }

    /**
     * 排序
     */
    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        Collections.sort(list, comparator);
    }

    /**
     * 将集合转化成 int 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 int 数组
     */
    public static int[] toIntArray(List<Integer> list) {
        if (list == null) return null;
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 将集合转化成 int 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 int 数组
     */
    public static <T> int[] toIntArray(List<T> list, Convertible<T, Integer> converter) {
        if (converter == null)
            throw new IllegalArgumentException("converter can not be null");
        if (list == null) return null;
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = converter.convert(list.get(i));
        }
        return array;
    }

    /**
     * 将集合转化成 long 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 long 数组
     */
    public static long[] toLongArray(List<Long> list) {
        if (list == null) return null;
        long[] array = new long[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 将集合转化成 long 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 long 数组
     */
    public static <T> long[] toLongArray(List<T> list, Convertible<T, Long> converter) {
        if (converter == null)
            throw new IllegalArgumentException("converter can not be null");
        if (list == null) return null;
        long[] array = new long[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = converter.convert(list.get(i));
        }
        return array;
    }

    /**
     * 将集合转化成 float 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 float 数组
     */
    public static float[] toFloatArray(List<Float> list) {
        if (list == null) return null;
        float[] array = new float[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 将集合转化成 float 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 float 数组
     */
    public static <T> float[] toFloatArray(List<T> list, Convertible<T, Float> converter) {
        if (converter == null)
            throw new IllegalArgumentException("converter can not be null");
        if (list == null) return null;
        float[] array = new float[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = converter.convert(list.get(i));
        }
        return array;
    }

    /**
     * 将集合转化成 double 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 double 数组
     */
    public static double[] toDoubleArray(List<Double> list) {
        if (list == null) return null;
        double[] array = new double[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 将集合转化成 double 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 double 数组
     */
    public static <T> double[] toDoubleArray(List<T> list, Convertible<T, Double> converter) {
        if (converter == null)
            throw new IllegalArgumentException("converter can not be null");
        if (list == null) return null;
        double[] array = new double[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = converter.convert(list.get(i));
        }
        return array;
    }

    /**
     * 将集合转化成 boolean 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 boolean 数组
     */
    public static boolean[] toBooleanArray(List<Boolean> list) {
        if (list == null) return null;
        boolean[] array = new boolean[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 将集合转化成 boolean 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 boolean 数组
     */
    public static <T> boolean[] toBooleanArray(List<T> list, Convertible<T, Boolean> converter) {
        if (converter == null)
            throw new IllegalArgumentException("converter can not be null");
        if (list == null) return null;
        boolean[] array = new boolean[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = converter.convert(list.get(i));
        }
        return array;
    }

    /**
     * 将集合转化成 char 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 char 数组
     */
    public static char[] toCharArray(List<Character> list) {
        if (list == null) return null;
        char[] array = new char[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 将集合转化成 char 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 char 数组
     */
    public static <T> char[] toCharArray(List<T> list, Convertible<T, Character> converter) {
        if (converter == null)
            throw new IllegalArgumentException("converter can not be null");
        if (list == null) return null;
        char[] array = new char[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = converter.convert(list.get(i));
        }
        return array;
    }

    /**
     * 将集合转化成 String 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 String 数组
     */
    public static String[] toStringArray(List<String> list) {
        if (list == null) return null;
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 将集合转化成 String 数组
     *
     * @param list 指定的集合
     * @return 转化后得到的 String 数组
     */
    public static <T> String[] toStringArray(List<T> list, Convertible<T, String> converter) {
        if (converter == null)
            throw new IllegalArgumentException("converter can not be null");
        if (list == null) return null;
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = converter.convert(list.get(i));
        }
        return array;
    }
}
