package com.linsh.lshutils.utils;

import androidx.annotation.Nullable;

import java.io.File;

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

    /**
     * 在指定目录下查找指定名称（不带后缀）的文件
     * <p>
     *
     * @param dir       指定目录
     * @param name      文件名（不带后缀）
     * @param postFixes 文件后缀，不传则表示任意后缀
     * @return 找到的文件，未找到则返回 null
     */
    @Nullable
    public static File findFile(File dir, String name, String... postFixes) {
        if (dir != null && dir.isDirectory()) {
            if (postFixes != null && postFixes.length > 0) {
                for (String postFix : postFixes) {
                    File file = new File(dir, name + postFix);
                    if (file.exists()) {
                        return file;
                    }
                }
            } else {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        String fileName = file.getName();
                        if (fileName.startsWith(name)) {
                            if (fileName.length() == name.length()) {
                                return file;
                            }
                            if (fileName.charAt(name.length()) == '.'
                                    && fileName.indexOf('.', name.length() + 1) == -1) {
                                return file;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
