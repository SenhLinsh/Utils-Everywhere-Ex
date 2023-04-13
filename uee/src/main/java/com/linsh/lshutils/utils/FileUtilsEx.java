package com.linsh.lshutils.utils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2023/04/13
 *    desc   :
 * </pre>
 */
public class FileUtilsEx {

    /**
     * 根据文件数据判断是否为只读文件
     *
     * @param attributes 文件属性
     */
    public static boolean isReadOnly(long attributes) {
        return (attributes & 0x1) != 0;
    }

    /**
     * 根据文件数据判断是否为隐藏文件
     *
     * @param attributes 文件属性
     */
    public static boolean isHidden(long attributes) {
        return (attributes & 0x2) != 0;
    }

    /**
     * 根据文件数据判断是否为文件夹
     *
     * @param attributes 文件属性
     */
    public static boolean isDirectory(long attributes) {
        return (attributes & 0x10) != 0;
    }

}
