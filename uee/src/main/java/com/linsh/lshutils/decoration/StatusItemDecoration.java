package com.linsh.lshutils.decoration;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.ref.SoftReference;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/28
 *    desc   : 为 RecyclerView 绘制状态显示
 * </pre>
 */
public abstract class StatusItemDecoration extends RecyclerView.ItemDecoration {

    private SoftReference<View> cache;
    private View mView;
    private int layout;

    public StatusItemDecoration(int layout) {
        this.layout = layout;
    }

    public void initHeader(RecyclerView parent) {
        if (mView == null) {
            if (cache != null && cache.get() != null) {
                mView = cache.get();
            } else {
                mView = LayoutInflater.from(parent.getContext()).inflate(layout, new FrameLayout(parent.getContext()), false);
                if (mView.getLayoutParams() == null) {
                    mView.setLayoutParams(new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }
                cache = new SoftReference<>(mView);
            }
        }
    }

    public abstract boolean shouldShowStatus(RecyclerView parent, RecyclerView.State state);

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        if (!shouldShowStatus(parent, state)) {
            mView = null;
            return;
        }

        initHeader(parent);
        int parentLeft = 0;
        int parentTop = 0;
        int parentRight = parent.getWidth();
        int parentBottom = parent.getHeight();
        int paddingLeft = parent.getPaddingLeft();
        int paddingTop = parent.getPaddingTop();
        int paddingRight = parent.getPaddingRight();
        int paddingBottom = parent.getPaddingBottom();

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);

        int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(), mView.getLayoutParams().width);
        int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                parent.getPaddingTop() + parent.getPaddingBottom(), mView.getLayoutParams().height);

        mView.measure(childWidthSpec, childHeightSpec);

        int width = mView.getMeasuredWidth();
        int height = mView.getMeasuredHeight();

        mView.layout(0, 0, width, height);

        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mView.getLayoutParams();

        int horizontalGravity = lp.gravity & Gravity.HORIZONTAL_GRAVITY_MASK;
        int verticalGravity = lp.gravity & Gravity.VERTICAL_GRAVITY_MASK;
        int childLeft;
        int childTop;
        switch (horizontalGravity) {
            case Gravity.CENTER_HORIZONTAL:
                childLeft = parentLeft + (parentRight - parentLeft - width) / 2 +
                        lp.leftMargin - lp.rightMargin;
                break;
            case Gravity.RIGHT:
                childLeft = parentRight - paddingRight - width - lp.rightMargin;
                break;
            case Gravity.LEFT:
            default:
                childLeft = parentLeft + paddingLeft + lp.leftMargin;
        }

        switch (verticalGravity) {
            case Gravity.CENTER_VERTICAL:
                childTop = parentTop + (parentBottom - parentTop - height) / 2 +
                        lp.topMargin - lp.bottomMargin;
                break;
            case Gravity.BOTTOM:
                childTop = parentBottom - paddingBottom - height - lp.bottomMargin;
                break;
            case Gravity.TOP:
            default:
                childTop = parentTop + paddingTop + lp.topMargin;
        }

        canvas.save();
        canvas.translate(childLeft, childTop);
        canvas.clipRect(paddingLeft - childLeft, paddingTop - childTop,
                parentRight - childLeft - paddingRight, parentBottom - childTop - paddingBottom);
        mView.draw(canvas);
        canvas.restore();
    }
}
