package com.linsh.lshutils.utils;

import java.util.Collections;
import java.util.Comparator;

import com.linsh.utilseverywhere.interfaces.Action;

import java.util.ArrayList;
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
     * 将指定的集合转成 String 集合, 即将集合的元素转成或抽取成 String
     *
     * @param list   源集合
     * @param action 将元素转成 String 的可执行任务
     * @return 新的 String 集合
     */
    public static <T> List<String> toStringList(List<T> list, Action<String, T> action) {
        List<String> stringList = new ArrayList<>();
        if (list != null && action != null) {
            for (T t : list) {
                stringList.add(action.call(t));
            }
        }
        return stringList;
    }
}
