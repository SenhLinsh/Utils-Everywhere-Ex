package com.linsh.lshutils.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/02/23
 *    desc   :
 * </pre>
 */
public class LinearLayoutUtilsEx {

    /**
     * 设置 LinearLayout 的透明分割线
     *
     * @param width 分割线宽度
     */
    public static void setDivider(LinearLayout linearLayout, int width) {
        setDivider(linearLayout, width, Color.TRANSPARENT);
    }

    /**
     * 设置 LinearLayout 的透明分割线
     *
     * @param width 分割线宽度
     */
    public static void setDivider(LinearLayout linearLayout, int width, int color) {
        setDivider(linearLayout, width, color, LinearLayout.SHOW_DIVIDER_MIDDLE);
    }

    /**
     * 设置 LinearLayout 的透明分割线
     *
     * @param width 分割线宽度
     */
    public static void setDivider(LinearLayout linearLayout, int width, int color, int dividerFlag) {
        linearLayout.setShowDividers(dividerFlag);
        if (linearLayout.getOrientation() == LinearLayout.HORIZONTAL) {
            linearLayout.setDividerDrawable(new DividerDrawable(width, 0, color));
        } else {
            linearLayout.setDividerDrawable(new DividerDrawable(0, width, color));
        }
    }

    private static class DividerDrawable extends Drawable {

        private final int width;
        private final int height;
        private final Paint paint;

        public DividerDrawable(int width, int height) {
            this.width = width;
            this.height = height;
            this.paint = null;
        }

        public DividerDrawable(int width, int height, int color) {
            this.width = width;
            this.height = height;
            this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            this.paint.setColor(color);
        }

        @Override
        public void draw(Canvas canvas) {
            if (paint != null) {
                canvas.drawRect(getBounds(), paint);
            }
        }

        @Override
        public void setAlpha(int alpha) {
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSPARENT;
        }

        @Override
        public int getIntrinsicWidth() {
            return width;
        }

        @Override
        public int getIntrinsicHeight() {
            return height;
        }
    }
}
