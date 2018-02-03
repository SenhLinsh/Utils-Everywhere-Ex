package com.linsh.lshutils.helper.wm;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;

import com.linsh.utilseverywhere.MathUtils;
import com.linsh.utilseverywhere.ScreenUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/01
 *    desc   : 辅助 WindowManager 实现悬浮拖拽效果, 简单快捷, 还可以添加吸附至边框的功能
 * </pre>
 */
public class WindowManagerFloatHelper {

    private WindowManager mWindowManager;
    private View mView;
    private Rect mScreenSizes;
    private boolean mIsMovable;
    private boolean mIsSticky;
    private float mHidePercent;
    private boolean isAnimating;

    public WindowManagerFloatHelper(@NonNull View view) {
        this(view, false, 0);
    }

    public WindowManagerFloatHelper(@NonNull View view, boolean isSticky, float hidePercent) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        while (!(layoutParams instanceof WindowManager.LayoutParams)) {
            ViewParent parent = view.getParent();
            if (!(parent instanceof View))
                return;
            view = (View) parent;
            layoutParams = view.getLayoutParams();
        }
        mView = view;
        DisplayMetrics screenSize = ScreenUtils.getScreenSize();
        mScreenSizes = new Rect(0, 0, screenSize.widthPixels, screenSize.heightPixels);
        setMovable(true);
        setSticky(isSticky);
        setHideInFrame(hidePercent);
        if (isSticky) {
            stickToFrame();
        }
    }

    /**
     * 是否可拖动
     *
     * @param movable 是否可拖动
     */
    public void setMovable(boolean movable) {
        mIsMovable = movable;
    }

    /**
     * 是否快速粘附与屏幕边框
     * <p>
     * 如果为 true, 拖动悬浮窗后该 View 会自动以弹性动画形式就近横向移动至屏幕边框
     *
     * @param sticky 是否吸附
     */
    public void setSticky(boolean sticky) {
        mIsSticky = sticky;
    }

    /**
     * 自动吸附于屏幕边框后, 可以设置 View 部分隐藏于屏幕之外
     *
     * @param hidePercent 隐藏部分的百分比 (横向)
     */
    public void setHideInFrame(float hidePercent) {
        mHidePercent = MathUtils.limit(hidePercent, 1, 0);
    }

    /**
     * 以弹性动画形式就近横向移动至屏幕边框
     * <p>
     * 该类会在拖动悬浮窗后自动调用此方法, 其他情况下需要手动调用, 如手动移动了 View
     */
    public void stickToFrame() {
        if (!isAnimating || mWindowManager != null)
            mView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stickSmoothly(mView);
                }
            }, 500);
    }

    void attachToWindowManager(WindowManager windowManager) {
        mWindowManager = windowManager;
        if (mView != null)
            mView.setOnTouchListener(new FloatingListener());
    }

    void detaachToWindowManager() {
        mWindowManager = null;
        if (mView != null)
            mView.setOnTouchListener(null);
    }

    private class FloatingListener implements View.OnTouchListener {

        private Point mPoint;
        private int move;

        @Override
        public boolean onTouch(final View view, MotionEvent event) {
            if (isAnimating || !mIsMovable || mWindowManager == null) return false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPoint = new Point((int) event.getRawX(), (int) event.getRawY());
                    move = 0;
                    break;
                case MotionEvent.ACTION_MOVE:
                    Point curP = new Point((int) event.getRawX(), (int) event.getRawY());
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
                    if (mPoint != null) {
                        layoutParams.x += curP.x - mPoint.x;
                        layoutParams.y += curP.y - mPoint.y;
                        if ((layoutParams.flags & WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) == 0) {
                            int maxRight = mScreenSizes.right - view.getWidth();
                            int maxBottom = mScreenSizes.bottom - view.getHeight();
                            if (layoutParams.x < 0) layoutParams.x = 0;
                            else if (layoutParams.x > maxRight) layoutParams.x = maxRight;
                            if (layoutParams.y < 0) layoutParams.y = 0;
                            else if (layoutParams.y > maxBottom) layoutParams.y = maxBottom;
                        }
                        mWindowManager.updateViewLayout(view, layoutParams);
                    }
                    mPoint = curP;
                    move++;
                    return true;
                case MotionEvent.ACTION_UP:
                    mPoint = null;
                    if (mIsSticky) {
                        if (move > 3) {
                            stickSmoothly(view);
                            return true;
                        } else if (move > 0) {
                            stick(view);
                        }
                    }
            }
            return false;
        }
    }

    private void stick(final View view) {
        int width = view.getWidth();
        final WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
        int endX;
        int hide = (layoutParams.flags & WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) == 0 ? 0 : (int) (width * mHidePercent);
        if (layoutParams.x + width / 2 < mScreenSizes.right / 2) {
            endX = 0 - hide;
        } else {
            endX = mScreenSizes.right - width + hide;
        }
        layoutParams.x = endX;
        mWindowManager.updateViewLayout(view, layoutParams);
    }

    private void stickSmoothly(final View view) {
        int width = view.getWidth();
        final WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
        int startX = layoutParams.x;
        int endX;
        int hide = (layoutParams.flags & WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) == 0 ? 0 : (int) (width * mHidePercent);
        if (startX + width / 2 < mScreenSizes.right / 2) {
            endX = 0 - hide;
        } else {
            endX = mScreenSizes.right - width + hide;
        }
        if (startX == endX)
            return;
        ValueAnimator animator = ValueAnimator.ofInt(startX, endX);
        animator.setDuration(800);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.x = (Integer) animation.getAnimatedValue();
                mWindowManager.updateViewLayout(view, layoutParams);
            }
        });
        animator.setInterpolator(new OvershootInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
