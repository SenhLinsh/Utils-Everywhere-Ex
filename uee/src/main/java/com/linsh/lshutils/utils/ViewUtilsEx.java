package com.linsh.lshutils.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/09/01
 *    desc   :
 * </pre>
 */
public class ViewUtilsEx {

    private ViewUtilsEx() {
    }

    /**
     * 添加触摸代理, 将代理交给某 View
     * <p>
     * View 类默认有设置触摸代码的方法, 但其不支持添加多个代理, 因为本处对其进行扩展
     */
    public static void addTouchDelegate(View sourceView, View delegateView, Rect bounds) {
        sourceView.setTouchDelegate(new TouchDelegateEx(bounds, delegateView, sourceView.getTouchDelegate()));
    }

    private static class TouchDelegateEx extends TouchDelegate {

        private TouchDelegate previous;

        public TouchDelegateEx(Rect bounds, View delegateView, TouchDelegate previous) {
            super(bounds, delegateView);
            this.previous = previous;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (previous != null && previous.onTouchEvent(event))
                return true;
            return super.onTouchEvent(event);
        }
    }
}
