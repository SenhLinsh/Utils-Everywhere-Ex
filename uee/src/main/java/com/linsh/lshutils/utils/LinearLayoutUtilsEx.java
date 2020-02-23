package com.linsh.lshutils.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
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
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        if (linearLayout.getOrientation() == LinearLayout.HORIZONTAL) {
            linearLayout.setDividerDrawable(new DividerDrawable(width, 0));
        } else {
            linearLayout.setDividerDrawable(new DividerDrawable(0, width));
        }
    }

    private static class DividerDrawable extends Drawable {

        private int width;
        private int height;

        public DividerDrawable(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw(Canvas canvas) {
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
