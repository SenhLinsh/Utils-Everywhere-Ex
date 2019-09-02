package com.linsh.lshutils.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/09/02
 *    desc   :
 * </pre>
 */
public class ListUtilsEx {

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
}
