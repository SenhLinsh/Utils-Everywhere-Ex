package com.linsh.lshutils.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/01/25
 *    desc   : RecyclerView 分割线绘制
 *             1. 可以添加指定宽度的颜色块或者图片
 *             2. 可以指定绘制在头部 & 中间 & 尾部
 *             3. 目前只支持垂直方向列表的分割线
 * </pre>
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_MIDDLE = 1 << 1;
    public static final int SHOW_DIVIDER_END = 1 << 2;

    @IntDef(flag = true,
            value = {
                    SHOW_DIVIDER_NONE,
                    SHOW_DIVIDER_BEGINNING,
                    SHOW_DIVIDER_MIDDLE,
                    SHOW_DIVIDER_END
            })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static final int SCALE_TYPE_CENTER_INSIDE = 0;
    public static final int SCALE_TYPE_FIT_CENTER = 1;
    public static final int SCALE_TYPE_FIT_XY = 2;

    @IntDef(flag = true,
            value = {
                    SCALE_TYPE_CENTER_INSIDE,
                    SCALE_TYPE_FIT_CENTER,
                    SCALE_TYPE_FIT_XY
            })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScaleType {
    }

    private int mDividerWidth;
    private Drawable mDivider;
    private float mAlpha = 1;
    private int mShowDividers = SHOW_DIVIDER_MIDDLE;
    private int mScaleType;

    public DividerItemDecoration() {
        this(1, Color.TRANSPARENT);
    }

    public DividerItemDecoration(int dividerWidth) {
        this(dividerWidth, Color.TRANSPARENT);
    }

    public DividerItemDecoration(int dividerWidth, int color) {
        this(dividerWidth, new ColorDrawable(color));
    }

    public DividerItemDecoration(Drawable drawable) {
        this(-1, drawable);
    }

    public DividerItemDecoration(int dividerWidth, Drawable drawable) {
        if (dividerWidth < 0) {
            dividerWidth = drawable.getIntrinsicWidth();
        }
        mDividerWidth = dividerWidth;
        mDivider = drawable;
    }

    public void setShowDividers(@DividerMode int flag) {
        mShowDividers = flag;
    }

    public void setScaleType(@ScaleType int scaleType) {
        mScaleType = scaleType;
    }

    /**
     * 用于设置 Item 的偏移, 好腾出空间绘制分割线
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mShowDividers == SHOW_DIVIDER_NONE) return;

        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        if (position == 0) {
            // 在头部添加分割线
            if ((mShowDividers & SHOW_DIVIDER_BEGINNING) != 0) {
                outRect.top = mDividerWidth; // 将顶部"撑开"
            }
        }
        if (position < itemCount - 1) {
            // 中间部分添加分割线
            if ((mShowDividers & SHOW_DIVIDER_MIDDLE) != 0) {
                outRect.bottom = mDividerWidth; // 将底部"撑开"
            }
        } else {
            // 尾部部分添加分割线
            if ((mShowDividers & SHOW_DIVIDER_END) != 0) {
                outRect.bottom = mDividerWidth;
            }
        }
    }

    /**
     * 在 Item 之间已腾出的空间中绘制分割线
     */
    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        if (parent.getLayoutManager() == null || mDividerWidth <= 0) {
            return;
        }
        canvas.save();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            int itemCount = parent.getAdapter().getItemCount();

            if (position == 0) {
                // 绘制头部分割线 (在第一个 Item 头部绘制)
                if ((mShowDividers & SHOW_DIVIDER_BEGINNING) != 0) {
                    parent.getDecoratedBoundsWithMargins(view, mBounds);
                    int dividerTop = mBounds.top + Math.round(view.getTranslationY());
                    drawDividersVertical(canvas, parent, dividerTop, 1); // 头部分割线的 alpha 不与 Item 进行关联
                }
            }

            if (position < itemCount - 1) {
                // 绘制中间分割线 (在当前 Item 底部绘制)
                if ((mShowDividers & SHOW_DIVIDER_MIDDLE) != 0) {
                    parent.getDecoratedBoundsWithMargins(view, mBounds);
                    int dividerTop = mBounds.bottom - mDividerWidth + Math.round(view.getTranslationY());
                    drawDividersVertical(canvas, parent, dividerTop, view.getAlpha());
                }
            } else {
                // 绘制尾部分割线 (在最后一个 Item 底部绘制)
                if ((mShowDividers & SHOW_DIVIDER_END) != 0) {
                    parent.getDecoratedBoundsWithMargins(view, mBounds);
                    int dividerTop = mBounds.bottom - mDividerWidth + Math.round(view.getTranslationY());
                    drawDividersVertical(canvas, parent, dividerTop, view.getAlpha());
                }
            }
        }
        canvas.restore();
    }

    private final Rect mBounds = new Rect();

    /**
     * @param canvas     Canvas
     * @param parent     RecyclerView
     * @param dividerTop 分割线顶部的 X 坐标
     * @param alpha      配合在 Item 增删时产生的 alpha 动画效果
     */
    private void drawDividersVertical(Canvas canvas, RecyclerView parent, int dividerTop, float alpha) {
        int dividerBottom = dividerTop + mDividerWidth;
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        float drawableHeight = mDivider.getIntrinsicHeight();
        float drawableWidth = mDivider.getIntrinsicWidth();
        if (drawableHeight > 0 || drawableWidth > 0) {
            float dividerHeight = mDividerWidth;
            float dividerWidth = dividerRight - dividerLeft;
            if (mScaleType == SCALE_TYPE_FIT_XY) {
                // 无需操作
            } else if (mScaleType == SCALE_TYPE_FIT_CENTER) {
                // 根据图片宽高动态调整图片显示大小 (如果分割线宽度大于图片宽度, 则显示图片本身大小, 并居中显示)
                // 调整图片大小
                float scale = Math.max(drawableWidth * 1f / dividerWidth, drawableHeight * 1f / dividerHeight);
                if (scale > 1) {
                    drawableWidth = drawableWidth == 0 ? dividerWidth : drawableWidth / scale;
                    drawableHeight = drawableHeight == 0 ? dividerHeight : drawableHeight / scale;
                } else {
                    drawableWidth = drawableWidth == 0 ? dividerWidth : drawableWidth;
                    drawableHeight = drawableHeight == 0 ? dividerHeight : drawableHeight;
                }

                // 通过分割线大小和图片大小, 计算图片再分割线上的绘制区域
                if (drawableHeight > 0 && dividerHeight > drawableHeight) {
                    dividerTop += (dividerHeight - drawableHeight) / 2;
                    dividerBottom -= (dividerHeight - drawableHeight) / 2;
                }
                if (drawableWidth > 0 && dividerWidth > drawableWidth) {
                    dividerLeft += (dividerWidth - drawableWidth) / 2;
                    dividerRight -= (dividerWidth - drawableWidth) / 2;
                }
            } else {
                // SCALE_TYPE_CENTER_INSIDE
                // 根据图片宽高动态调整图片显示大小 (如果分割线宽度大于图片宽度, 则显示图片本身大小, 并居中显示)
                // 调整图片大小
                float scale = Math.max(drawableWidth * 1f / dividerWidth, drawableHeight * 1f / dividerHeight);
                drawableWidth = drawableWidth == 0 ? dividerWidth : drawableWidth / scale;
                drawableHeight = drawableHeight == 0 ? dividerHeight : drawableHeight / scale;

                // 通过分割线大小和图片大小, 计算图片再分割线上的绘制区域
                if (drawableHeight > 0 && dividerHeight > drawableHeight) {
                    dividerTop += (dividerHeight - drawableHeight) / 2;
                    dividerBottom -= (dividerHeight - drawableHeight) / 2;
                }
                if (drawableWidth > 0 && dividerWidth > drawableWidth) {
                    dividerLeft += (dividerWidth - drawableWidth) / 2;
                    dividerRight -= (dividerWidth - drawableWidth) / 2;
                }
            }
        }
        mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
        if (mAlpha != alpha) {
            mDivider.setAlpha((int) (alpha * 255));
            mAlpha = alpha;
        }
        mDivider.draw(canvas);
    }
}