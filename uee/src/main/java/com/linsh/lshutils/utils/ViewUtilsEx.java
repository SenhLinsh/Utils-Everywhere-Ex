package com.linsh.lshutils.utils;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
     * 设置 leftMargin
     *
     * @param view       View 对象
     * @param leftMargin leftMargin
     */
    public static void setLeftMargin(@NonNull View view, int leftMargin) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = leftMargin;
        }
    }

    /**
     * 设置 topMargin
     *
     * @param view      View 对象
     * @param topMargin topMargin
     */
    public static void setTopMargin(@NonNull View view, int topMargin) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = topMargin;
        }
    }

    /**
     * 设置 rightMargin
     *
     * @param view        View 对象
     * @param rightMargin rightMargin
     */
    public static void setRightMargin(@NonNull View view, int rightMargin) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = rightMargin;
        }
    }

    /**
     * 设置 bottomMargin
     *
     * @param view         View 对象
     * @param bottomMargin bottomMargin
     */
    public static void setBottomMargin(@NonNull View view, int bottomMargin) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = bottomMargin;
        }
    }

    /**
     * 查找所有实例为指定类型的 View，当前类型或为其子类
     *
     * @param view        根布局
     * @param classOfView 指定 View 的类对象
     * @return 所有名称匹配的子 View
     */
    @NonNull
    public static <T extends View> List<T> findViewsByClass(@NonNull View view, @NonNull Class<T> classOfView) {
        return findViewsByClassInternal(view, classOfView, new ArrayList<T>());
    }

    private static <T extends View> List<T> findViewsByClassInternal(@NonNull View view, @NonNull Class<T> classOfView, List<T> result) {
        if (classOfView.isAssignableFrom(view.getClass())) {
            result.add((T) view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findViewsByClassInternal(viewGroup.getChildAt(i), classOfView, result);
            }
        }
        return result;
    }

    /**
     * 查找所有类名为指定名称的 View，仅为当前类型（不包括其子类或实现类）
     *
     * @param view      根布局
     * @param className 指定 View 的名称
     * @return 所有名称匹配的子 View
     */
    @NonNull
    public static List<View> findViewsByClass(@NonNull View view, @NonNull String className) {
        return findViewsByClassInternal(view, className, new ArrayList<View>());
    }

    private static <T extends View> List<T> findViewsByClassInternal(@NonNull View view, @NonNull String className, List<T> result) {
        if (view.getClass().getName().endsWith(className)) {
            result.add((T) view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findViewsByClassInternal(viewGroup.getChildAt(i), className, result);
            }
        }
        return result;
    }

    /**
     * 查找首个实例为指定类型的 View，当前类型或为其子类
     *
     * @param view        根布局
     * @param classOfView 指定 View 的类对象
     * @return 首个名称匹配的子 View
     */
    @Nullable
    public static <T extends View> T findFirstViewByClass(@NonNull View view, @NonNull Class<T> classOfView) {
        if (classOfView.isAssignableFrom(view.getClass())) {
            return (T) view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                T ret = findFirstViewByClass(viewGroup.getChildAt(i), classOfView);
                if (ret != null)
                    return ret;
            }
        }
        return null;
    }

    /**
     * 查找首个类名为指定名称的 View，仅为当前类型（不包括其子类或实现类）
     *
     * @param view      根布局
     * @param className 指定 View 的名称
     * @return 首个名称匹配的子 View
     */
    @Nullable
    public static <T extends View> T findFirstViewByClass(@NonNull View view, @NonNull String className) {
        if (view.getClass().getName().equals(className)) {
            return (T) view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                T ret = findFirstViewByClass(viewGroup.getChildAt(i), className);
                if (ret != null)
                    return ret;
            }
        }
        return null;
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
