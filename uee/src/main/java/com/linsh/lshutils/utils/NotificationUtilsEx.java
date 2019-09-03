package com.linsh.lshutils.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import com.linsh.utilseverywhere.ContextUtils;
import com.linsh.utilseverywhere.MathUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/09/03
 *    desc   :
 * </pre>
 */
public class NotificationUtilsEx {

    private NotificationUtilsEx() {
    }

    /**
     * 显示带有进度条的 Notification
     *
     * @param title     标题
     * @param text      文本
     * @param smallIcon 小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     */
    public static void showLoading(@Nullable String title, @Nullable String text, @DrawableRes int smallIcon) {
        showLoading(IdUtilsEx.generateId(), title, text, smallIcon);
    }

    /**
     * 显示带有进度条的 Notification
     *
     * @param id        Notification id
     * @param title     标题
     * @param text      文本
     * @param smallIcon 小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     */
    public static void showLoading(int id, @Nullable String title, @Nullable String text, @DrawableRes int smallIcon) {
        NotificationManager manager = (NotificationManager) ContextUtils.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, build(title, text, smallIcon, null)
                .setProgress(100, 0, true)
                .build());
    }

    /**
     * 显示带有进度条的 Notification
     *
     * @param title     标题
     * @param text      文本
     * @param smallIcon 小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     * @param progress  进度
     */
    public static void showLoading(@Nullable String title, @Nullable String text, @DrawableRes int smallIcon, @FloatRange(from = 0, to = 1) float progress) {
        showLoading(IdUtilsEx.generateId(), title, text, smallIcon, progress);
    }

    /**
     * 显示带有进度条的 Notification
     *
     * @param id        Notification id
     * @param title     标题
     * @param text      文本
     * @param smallIcon 小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     * @param progress  进度
     */
    public static void showLoading(int id, @Nullable String title, @Nullable String text, @DrawableRes int smallIcon, @FloatRange(from = 0, to = 1) float progress) {
        NotificationManager manager = (NotificationManager) ContextUtils.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, build(title, text, smallIcon, null)
                .setProgress(100, MathUtils.limit((int) (progress * 100), 100, 0), false)
                .build());
    }

    /**
     * 显示 Notification
     *
     * @param title     标题
     * @param text      文本
     * @param smallIcon 小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     */
    public static void show(@Nullable String title, @Nullable String text, @DrawableRes int smallIcon) {
        show(title, text, smallIcon, null);
    }

    /**
     * 显示 Notification
     *
     * @param id        Notification id
     * @param title     标题
     * @param text      文本
     * @param smallIcon 小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     */
    public static void show(int id, @Nullable String title, @Nullable String text, @DrawableRes int smallIcon) {
        show(id, title, text, smallIcon, null);
    }

    /**
     * 显示 Notification
     *
     * @param title         标题
     * @param text          文本
     * @param smallIcon     小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     * @param pendingIntent 点击触发的 Intent
     */
    public static void show(@Nullable String title, @Nullable String text,
                            @DrawableRes int smallIcon, @Nullable PendingIntent pendingIntent) {
        show(IdUtilsEx.generateId(), title, text, smallIcon, pendingIntent);
    }

    /**
     * 显示 Notification
     *
     * @param id            Notification id
     * @param title         标题
     * @param text          文本
     * @param smallIcon     小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     * @param pendingIntent 点击触发的 Intent
     */
    public static void show(int id, @Nullable String title, @Nullable String text,
                            @DrawableRes int smallIcon, @Nullable PendingIntent pendingIntent) {
        NotificationManager manager = (NotificationManager) ContextUtils.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, build(title, text, smallIcon, pendingIntent).build());
    }

    /**
     * 构建 Notification
     *
     * @param title         标题
     * @param text          文本
     * @param smallIcon     小图标, 在标题栏显示. 注意: 显示只保留 alpha 通道
     * @param pendingIntent 点击触发的 Intent
     */
    public static Notification.Builder build(@Nullable String title, @Nullable String text,
                                             @DrawableRes int smallIcon, @Nullable PendingIntent pendingIntent) {
        Notification.Builder builder = new Notification.Builder(ContextUtils.get());
        if (title != null) builder.setContentTitle(title);
        if (text != null) builder.setContentText(text);
        builder.setSmallIcon(smallIcon);
        if (pendingIntent != null) builder.setContentIntent(pendingIntent);
        return builder.setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND);
    }

}
