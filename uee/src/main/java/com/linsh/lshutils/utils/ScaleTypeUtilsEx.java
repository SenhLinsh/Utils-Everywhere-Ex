package com.linsh.lshutils.utils;

import android.widget.ImageView;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2021/09/25
 *    desc   :
 * </pre>
 */
public class ScaleTypeUtilsEx {

    /**
     * 给一个显示区域（如 ImageView）绘制显示对象（如图片），根据 ScaleType 来分别获取：
     * 1. 显示区域的使用部分（Rect）
     * 2. 显示对象的显示部分（Rect）
     *
     * @param viewWidth   显示区域的宽度
     * @param viewHeight  显示区域的高度
     * @param imageWidth  显示对象的宽度
     * @param imageHeight 显示对象的高度
     * @param scaleType   对齐类型
     * @return 返回一个长度为 8 的数组，其中 0 - 3 位为显示区域的显示部分，4 - 7 位为显示对象的使用部分
     */
    public static int[] makeRect(int viewWidth, int viewHeight, int imageWidth, int imageHeight, ImageView.ScaleType scaleType) {
        int[] ret = new int[8];
        if (viewWidth == 0 || viewHeight == 0 || imageWidth == 0 || imageHeight == 0)
            return ret;
        switch (scaleType) {
            case FIT_XY:
                return new int[]{0, 0, viewWidth, viewHeight, 0, 0, imageWidth, imageHeight};
            case CENTER_CROP:
                if (viewWidth * 1f / viewHeight > imageWidth * 1f / imageHeight) {
                    float top = (imageHeight - viewHeight) / 2f * imageWidth / viewWidth;
                    float bottom = (imageHeight + viewHeight) / 2f * imageWidth / viewWidth;
                    return new int[]{0, 0, viewWidth, viewHeight, 0, Math.round(top), imageWidth, Math.round(bottom)};
                } else {
                    float left = (imageWidth * 1f * viewHeight / imageHeight - viewWidth) / 2f * imageHeight / viewHeight;
                    float right = (imageWidth * 1f * viewHeight / imageHeight + viewWidth) / 2f * imageHeight / viewHeight;
                    return new int[]{0, 0, viewWidth, viewHeight, Math.round(left), 0, Math.round(right), imageHeight};
                }
        }

        return ret;
    }
}
