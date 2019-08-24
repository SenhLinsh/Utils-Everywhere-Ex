package com.linsh.lshutils.helper.wm;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/01
 *    desc   : 为 WindowManager 创建 View 视图, 不再为 WindowManager.LayoutParams 的事情而烦恼
 * </pre>
 */
public class WindowManagerViewHelper {

    protected View mView;
    protected WindowManager.LayoutParams mLayoutParams;

    public WindowManagerViewHelper(@NonNull Context context, @LayoutRes int layout) {
        this(LayoutInflater.from(context).inflate(layout, null));
    }

    public WindowManagerViewHelper(@NonNull View view) {
        this(view, generateLayoutParams());
    }

    public WindowManagerViewHelper(@NonNull View view, @NonNull WindowManager.LayoutParams layoutParams) {
        mView = view;
        mView.setLayoutParams(layoutParams);
        mLayoutParams = layoutParams;
    }

    public View getView() {
        return mView;
    }

    public WindowManagerViewHelper setWidth(int width) {
        mLayoutParams.width = width;
        return this;
    }

    public WindowManagerViewHelper setHeight(int height) {
        mLayoutParams.height = height;
        return this;
    }

    public WindowManagerViewHelper setSize(int width, int height) {
        mLayoutParams.width = width;
        mLayoutParams.height = height;
        return this;
    }

    public WindowManagerViewHelper setLocation(int x, int y) {
        mLayoutParams.x = x;
        mLayoutParams.y = y;
        return this;
    }

    public WindowManagerViewHelper addFlags(int flags) {
        mLayoutParams.flags |= flags;
        return this;
    }

    public WindowManagerViewHelper removeFlags(int flags) {
        mLayoutParams.flags &= ~flags;
        return this;
    }

    public WindowManagerViewHelper setGravity(int gravity) {
        mLayoutParams.gravity = gravity;
        return this;
    }

    public WindowManagerViewHelper setType(int type) {
        mLayoutParams.type = type;
        return this;
    }

    public WindowManagerViewHelper setFormat(int format) {
        mLayoutParams.format = format;
        return this;
    }

    public void addViewToWindowManager(@NonNull WindowManager windowManager) {
        windowManager.addView(mView, mLayoutParams);
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    private static WindowManager.LayoutParams generateLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.TOP | Gravity.START;
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.format = 1;
        layoutParams.flags = 40;
        return layoutParams;
    }
}
