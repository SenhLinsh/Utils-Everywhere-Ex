package com.linsh.lshutils.utils;

import android.content.res.Configuration;

import com.linsh.utilseverywhere.ContextUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/03/09
 *    desc   :
 * </pre>
 */
public class DeviceUtilsEx {

    /**
     * 判断是否为手机设备
     *
     * @return true 为手机, false 为平板
     */
    @Deprecated
    public static boolean isPhoneDevice() {
        return (ContextUtils.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
