package com.linsh.lshutils.utils;

import android.content.pm.ApplicationInfo;

import com.linsh.utilseverywhere.ContextUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/03/14
 *    desc   :
 * </pre>
 */
public class DebugUtilsEx {

    /**
     * 判断应用是否处于 Debuggable 模式
     */
    private static boolean isDebuggable() {
        ApplicationInfo applicationInfo = ContextUtils.get().getApplicationInfo();
        return applicationInfo != null && (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
