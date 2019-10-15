package com.linsh.lshutils.utils;

import com.linsh.utilseverywhere.HandlerUtils;
import com.linsh.utilseverywhere.ToastUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/10/15
 *    desc   :
 * </pre>
 */
public class ToastUtilsEx {

    /**
     * 切换到 UI 线程中弹出 Toast
     */
    public static void post(final String text) {
        HandlerUtils.postRunnable(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show(text);
            }
        });
    }

    /**
     * 切换到 UI 线程中弹出 Toast
     */
    public static void postLong(final String text) {
        HandlerUtils.postRunnable(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showLong(text);
            }
        });
    }
}
