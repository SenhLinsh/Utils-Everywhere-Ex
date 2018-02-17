package com.linsh.lshutils.helper.wm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.linsh.utilseverywhere.ContextUtils;

import java.util.ArrayList;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/01
 *    desc   : WindowManager 辅助类, 帮助快速简单使用 WindowManager 创建悬浮窗
 *             不建议叫 WindowManagerHelper 的, 它将使用的 Helper 产生歧义冲突, 但是想不出更好的后缀
 * </pre>
 */
public class WindowManagerHelper {

    private final WindowManager mWindowManager;
    private ArrayList<View> mViews;

    public WindowManagerHelper() {
        mWindowManager = ((WindowManager) ContextUtils.get().getSystemService(Context.WINDOW_SERVICE));
        mViews = new ArrayList<>();
    }

    public WindowManagerViewHelper addView(@NonNull Context context, int layout) {
        return addView(new WindowManagerViewHelper(context, layout));
    }

    public WindowManagerViewHelper addView(@NonNull View view, LayoutParams layoutParams) {
        return addView(new WindowManagerViewHelper(view, layoutParams));
    }

    public WindowManagerViewHelper addView(@NonNull WindowManagerViewHelper viewHelper) {
        View view = viewHelper.getView();
        mViews.add(view);
        mWindowManager.addView(view, viewHelper.getLayoutParams());
        return viewHelper;
    }

    public void addFloat(@NonNull WindowManagerFloatHelper floatHelper) {
        floatHelper.attachToWindowManager(mWindowManager);
    }

    public int getViewCount() {
        return mViews.size();
    }

    public boolean containView(View view) {
        return mViews.contains(view);
    }

    public boolean containViewType(Class<? extends View> clazz) {
        for (View view : mViews) {
            if (clazz.isInstance(view))
                return true;
        }
        return false;
    }

    public void updateViewLayout(View view) {
        mWindowManager.updateViewLayout(view, view.getLayoutParams());
    }

    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        mWindowManager.updateViewLayout(view, params);
    }

    public void removeView(int index) {
        if (index < mViews.size()) {
            View view = mViews.remove(index);
            if (view != null) {
                mWindowManager.removeView(view);
            }
        }
    }

    public void removeView(View view) {
        mWindowManager.removeView(view);
        mViews.remove(view);
    }

    public void removeAllViews() {
        for (View view : mViews) {
            if (view != null) {
                mWindowManager.removeView(view);
            }
        }
        mViews.clear();
    }
}
