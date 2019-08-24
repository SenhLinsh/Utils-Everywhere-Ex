package com.linsh.lshutils.utils;

import android.view.MotionEvent;
import android.view.View;

import com.linsh.utilseverywhere.LogUtils;
import com.linsh.utilseverywhere.ViewUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/10
 *    desc   : 工具类: 触摸事件相关
 * </pre>
 */
public class TouchUtils {

    /**
     * 重写触摸事件回调, 模仿点击事件
     *
     * @param view     View
     * @param listener 点击时间回调
     */
    public static void asOnClick(final View view, final View.OnClickListener listener) {
        view.setOnTouchListener(new View.OnTouchListener() {
            private int onClick = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        onClick = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        onClick++;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (onClick < 3) {
                            listener.onClick(view);
                        }
                        onClick = 0;
                        break;
                }
                return false;
            }
        });
    }

    public static void moveWithTouch(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            private float curX;
            private float curY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        curX = event.getRawX();
                        curY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float x = event.getRawX();
                        float y = event.getRawY();
                        float dX = x - curX;
                        float dY = y - curY;
                        int[] locationsParent = ViewUtils.getLocationsOnScreen((View) v.getParent());
                        int[] locations = ViewUtils.getLocationsOnScreen(v);
                        if (dX < 0) {
                            if (locations[0] == locationsParent[0]) {
                                dX = 0;
                            } else if (dX < locationsParent[0] - locations[0]) {
                                dX = locationsParent[0] - locations[0];
                            }
                        } else if (dX > 0) {
                            if (locations[2] == locationsParent[2]) {
                                dX = 0;
                            } else if (dX > locationsParent[2] - locations[2]) {
                                dX = locationsParent[2] - locations[2];
                            }
                        }
                        if (dY < 0) {
                            if (locations[1] == locationsParent[1]) {
                                dY = 0;
                            } else if (dY < locationsParent[1] - locations[1]) {
                                dY = locationsParent[1] - locations[1];
                            }
                        } else if (dY > 0) {
                            if (locations[3] == locationsParent[3]) {
                                dY = 0;
                            } else if (dY > locationsParent[3] - locations[3]) {
                                dY = locationsParent[3] - locations[3];
                            }
                        }
                        if (dX != 0) {
                            v.setTranslationX(v.getTranslationX() + dX);
                            LogUtils.i("setTranslationX", dX);
                        }
                        if (dY != 0) {
                            v.setTranslationY(v.getTranslationY() + dY);
                            LogUtils.i("setTranslationY", dY);
                        }
                        curX = x;
                        curY = y;
                        return true;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }
}
