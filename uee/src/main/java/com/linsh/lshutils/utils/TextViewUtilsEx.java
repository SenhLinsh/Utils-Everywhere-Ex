package com.linsh.lshutils.utils;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/09/03
 *    desc   :
 * </pre>
 */
public class TextViewUtilsEx {

    /**
     * 设置 TextView 左侧的图片
     *
     * @param textView TextView
     * @param drawable 图片
     */
    public static void setDrawableLeft(@NonNull TextView textView, @Nullable Drawable drawable) {
        Drawable[] drawables = textView.getCompoundDrawables();
        setDrawables(textView, drawable, null, drawables[1], null, drawables[2], null, drawables[3], null);
    }

    /**
     * 设置 TextView 左侧的图片
     *
     * @param textView TextView
     * @param drawable 图片
     * @param bounds   图片边界, 用于控制图片显示大小
     */
    public static void setDrawableLeft(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Rect bounds) {
        Drawable[] drawables = textView.getCompoundDrawables();
        setDrawables(textView, drawable, bounds, drawables[1], null, drawables[2], null, drawables[3], null);
    }

    /**
     * 设置 TextView 上侧的图片
     *
     * @param textView TextView
     * @param drawable 图片
     */
    public static void setDrawableTop(@NonNull TextView textView, @Nullable Drawable drawable) {
        Drawable[] drawables = textView.getCompoundDrawables();
        setDrawables(textView, drawables[0], null, drawable, null, drawables[2], null, drawables[3], null);
    }

    /**
     * 设置 TextView 上侧的图片
     *
     * @param textView TextView
     * @param drawable 图片
     * @param bounds   图片边界, 用于控制图片显示大小
     */
    public static void setDrawableTop(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Rect bounds) {
        Drawable[] drawables = textView.getCompoundDrawables();
        setDrawables(textView, drawables[0], null, drawable, bounds, drawables[2], null, drawables[3], null);
    }

    /**
     * 设置 TextView 右侧的图片
     *
     * @param textView TextView
     * @param drawable 图片
     */
    public static void setDrawableRight(@NonNull TextView textView, @Nullable Drawable drawable) {
        Drawable[] drawables = textView.getCompoundDrawables();
        setDrawables(textView, drawables[0], null, drawables[1], null, drawable, null, drawables[3], null);
    }

    /**
     * 设置 TextView 右侧的图片
     *
     * @param textView TextView
     * @param drawable 图片
     * @param bounds   图片边界, 用于控制图片显示大小
     */
    public static void setDrawableRight(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Rect bounds) {
        Drawable[] drawables = textView.getCompoundDrawables();
        setDrawables(textView, drawables[0], null, drawables[1], null, drawable, bounds, drawables[3], null);
    }

    /**
     * 设置 TextView 下侧的图片
     *
     * @param textView TextView
     * @param drawable 图片
     */
    public static void setDrawableBottom(@NonNull TextView textView, @Nullable Drawable drawable) {
        Drawable[] drawables = textView.getCompoundDrawables();
        setDrawables(textView, drawables[0], null, drawables[1], null, drawables[2], null, drawable, null);
    }

    /**
     * 设置 TextView 下侧的图片
     *
     * @param textView TextView
     * @param drawable 图片
     * @param bounds   图片边界, 用于控制图片显示大小
     */
    public static void setDrawableBottom(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Rect bounds) {
        Drawable[] drawables = textView.getCompoundDrawables();
        setDrawables(textView, drawables[0], null, drawables[1], null, drawables[2], null, drawable, bounds);
    }

    /**
     * 设置 TextView 四个方向的图片
     * <p>
     * 注: bounds 用来控制图片的显示大小
     *
     * @param textView TextView
     * @param left     左侧图片
     * @param top      上侧图片
     * @param right    右侧图片
     * @param bottom   下侧图片
     */
    public static void setDrawables(@NonNull TextView textView, @Nullable Drawable left, @Nullable Drawable top,
                                    @Nullable Drawable right, @Nullable Drawable bottom) {
        setDrawables(textView, left, null, top, null, right, null, bottom, null);
    }

    /**
     * 设置 TextView 四个方向的图片
     * <p>
     * 注: bounds 用来控制图片的显示大小.
     * bounds.width() 和 bounds.height() 分别为显示的宽和高.
     * bounds.left 和 bounds.top 分别为 x 和 y 方向的偏移量.
     *
     * @param textView     TextView
     * @param left         左侧图片
     * @param leftBounds   左侧图片的边界
     * @param top          上侧图片
     * @param topBounds    上侧图片的边界
     * @param right        右侧图片
     * @param rightBounds  右侧图片的边界
     * @param bottom       下侧图片
     * @param bottomBounds 下侧图片的边界
     */
    public static void setDrawables(@NonNull TextView textView, @Nullable Drawable left, @Nullable Rect leftBounds,
                                    @Nullable Drawable top, @Nullable Rect topBounds,
                                    @Nullable Drawable right, @Nullable Rect rightBounds,
                                    @Nullable Drawable bottom, @Nullable Rect bottomBounds) {
        setDrawableBounds(left, leftBounds);
        setDrawableBounds(top, topBounds);
        setDrawableBounds(right, rightBounds);
        setDrawableBounds(bottom, bottomBounds);
        textView.setCompoundDrawables(left, top, right, bottom);
    }

    /**
     * 设置图片的边界, 用于控制图片的显示大小
     */
    private static void setDrawableBounds(@Nullable Drawable drawable, @Nullable Rect bounds) {
        if (drawable != null) {
            if (bounds != null) {
                drawable.setBounds(bounds);
            } else if (drawable.getBounds().isEmpty()) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
        }
    }
}
