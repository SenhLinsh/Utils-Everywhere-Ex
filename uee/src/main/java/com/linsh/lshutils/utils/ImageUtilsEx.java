package com.linsh.lshutils.utils;

import android.media.ExifInterface;
import android.text.TextUtils;

import java.lang.reflect.Field;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/02/24
 *    desc   :
 * </pre>
 */
public class ImageUtilsEx {

    /**
     * 将 srcFilePath 图片文件的 Exif 信息保存到 destFilePath 图片文件中
     *
     * @param srcFilePath  保存有 Exif 信息的图片文件
     * @param destFilePath 需要保存 Exif 信息的图片文件
     */
    public static void saveExif(String srcFilePath, String destFilePath) throws Exception {
        ExifInterface oldExif = new ExifInterface(srcFilePath);
        ExifInterface newExif = new ExifInterface(destFilePath);
        Class<ExifInterface> cls = ExifInterface.class;
        Field[] fields = cls.getFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            if (!TextUtils.isEmpty(fieldName) && fieldName.startsWith("TAG")) {
                String fieldValue = fields[i].get(cls).toString();
                String attribute = oldExif.getAttribute(fieldValue);
                if (attribute != null) {
                    newExif.setAttribute(fieldValue, attribute);
                }
            }
        }
        newExif.saveAttributes();
    }
}
