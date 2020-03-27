package com.linsh.lshutils.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

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
     * 查找所有指定名称的 View
     *
     * @param view        根布局
     * @param classOfView 指定 View 的类对象
     * @return 所有名称匹配的子 View
     */
    public static List<View> findViewsByName(@NonNull View view, @NonNull Class<? extends View> classOfView) {
        return findViewsByName(view, classOfView.getName());
    }

    /**
     * 查找所有指定名称的 View
     *
     * @param view      根布局
     * @param className 指定 View 的名称
     * @return 所有名称匹配的子 View
     */
    public static List<View> findViewsByName(@NonNull View view, @NonNull String className) {
        return findViewsByName(view, className, new ArrayList<View>());
    }

    private static List<View> findViewsByName(@NonNull View view, @NonNull String className, List<View> result) {
        if (view.getClass().getName().endsWith(className)) {
            result.add(view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findViewsByName(viewGroup.getChildAt(i), className, result);
            }
        }
        return result;
    }

    /**
     * 查找所有正则配合通过的 View
     * <p>
     * 目前只能匹配 TextView 及其子 View, 如 Button 等
     *
     * @param view  根布局
     * @param regex 用于匹配是否符合条件的正则
     * @return 正则配合通过的子 View, 匹配失败将返回空集合
     */
    @NonNull
    public static List<View> findViewsByText(@NonNull View view, @NonNull String regex) {
        return findViewsByText(view, regex, new ArrayList<View>());
    }

    private static List<View> findViewsByText(@NonNull View view, @NonNull String regex, List<View> result) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findViewsByText(viewGroup.getChildAt(i), regex, result);
            }
        } else if (view instanceof TextView) {
            String text = ((TextView) view).getText().toString();
            if (text.matches(regex)) {
                result.add(view);
            }
        }
        return result;
    }

    /**
     * 添加触摸代理, 将代理交给某 View
     * <p>
     * View 类默认有设置触摸代码的方法, 但其不支持添加多个代理, 因为本处对其进行扩展
     */
    public static void addTouchDelegate(@NonNull View sourceView, @NonNull View delegateView, @NonNull Rect bounds) {
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
