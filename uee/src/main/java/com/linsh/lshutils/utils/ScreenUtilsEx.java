package com.linsh.lshutils.utils;

import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2022/03/21
 *    desc   :
 * </pre>
 */
public class ScreenUtilsEx {

    /**
     * 保持屏幕常亮
     */
    public static void keepScreenOn(@NonNull Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 取消保持屏幕常亮
     */
    public static void clearKeepScreenOn(@NonNull Window window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
