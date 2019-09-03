package com.linsh.lshutils.utils;

import android.os.Build;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/09/03
 *    desc   :
 * </pre>
 */
public class IdUtilsEx {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * 自动生成 id
     */
    public static int generateId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1;
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    /**
     * 生成 View id
     */
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        }
        return generateId();
    }
}
