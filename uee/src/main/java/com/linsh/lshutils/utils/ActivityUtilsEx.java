package com.linsh.lshutils.utils;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.linsh.utilseverywhere.HandlerUtils;
import com.linsh.utilseverywhere.RandomUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/03/03
 *    desc   :
 * </pre>
 */
public class ActivityUtilsEx {

    /**
     * 模拟点击
     *
     * @param activity 用于发送点击事件的 Activity
     * @param x        点击坐标 x
     * @param y        点击坐标 y
     */
    public static void performClick(final Activity activity, final int x, final int y) {
        final long downTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0);
        activity.dispatchTouchEvent(event);
        HandlerUtils.postRunnable(new Runnable() {
            @Override
            public void run() {
                MotionEvent event = MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0);
                activity.dispatchTouchEvent(event);
            }
        }, RandomUtils.getInt(100, 500));
    }

    /**
     * 模拟长按
     *
     * @param activity 用于发送点击事件的 Activity
     * @param x        点击坐标 x
     * @param y        点击坐标 y
     */
    public static void performLongClick(final Activity activity, final int x, final int y) {
        final long downTime = SystemClock.uptimeMillis();
        MotionEvent event = MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0);
        activity.dispatchTouchEvent(event);
        HandlerUtils.postRunnable(new Runnable() {
            @Override
            public void run() {
                MotionEvent event = MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0);
                activity.dispatchTouchEvent(event);
            }
        }, RandomUtils.getInt(600, 1200));
    }

    /**
     * 获取 Activity 的 DecorView
     *
     * @param activity Activity
     * @return DecorView
     */
    public static View getDecorView(final Activity activity) {
        return activity.getWindow().getDecorView();
    }

    /**
     * 获取 Activity 的 ContentView
     * <p>
     * 该 ContentView 为 Activity setContentView 的父布局
     *
     * @param activity Activity
     * @return DecorView
     */
    public static View getContentView(final Activity activity) {
        return activity.findViewById(android.R.id.content);
    }

    /**
     * 显示 Action Bar
     *
     * @param activity Activity
     */
    public static void showActionBar(Activity activity) {
        if (activity instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        } else {
            android.app.ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    }

    /**
     * 隐藏 Action Bar
     *
     * @param activity Activity
     */
    public static void hideActionBar(Activity activity) {
        if (activity instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        } else {
            android.app.ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }
    }
}
