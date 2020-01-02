package com.linsh.lshutils.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.linsh.utilseverywhere.ListUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/08/25
 *    desc   :
 * </pre>
 */
public class AllertDialogUtilsEx {

    public static AlertDialog showText(@NonNull Context context, @Nullable String content) {
        return showText(context, null, content);
    }

    public static AlertDialog showText(@NonNull Context context, @Nullable String title, @Nullable String content) {
        return showText(context, title, content, null, null);
    }

    public static AlertDialog showText(@NonNull Context context, @Nullable String title, @Nullable String content,
                                       @Nullable String positiveBtn, @Nullable DialogInterface.OnClickListener positiveListener) {
        return showText(context, title, content, positiveBtn, positiveListener, null, null);
    }

    public static AlertDialog showText(@NonNull Context context, @Nullable String title, @Nullable String content,
                                       @Nullable String positiveBtn, @Nullable DialogInterface.OnClickListener positiveListener,
                                       @Nullable String negativeBtn, @Nullable DialogInterface.OnClickListener negativeListener) {
        return buildText(context, title, content, positiveBtn, positiveListener, negativeBtn, negativeListener).show();
    }

    public static AlertDialog.Builder buildText(@NonNull Context context, @Nullable String title, @Nullable String content,
                                                @Nullable String positiveBtn, @Nullable DialogInterface.OnClickListener positiveListener,
                                                @Nullable String negativeBtn, @Nullable DialogInterface.OnClickListener negativeListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(positiveBtn, positiveListener)
                .setNegativeButton(negativeBtn, negativeListener);
    }

    public static AlertDialog showList(@NonNull Context context, @Nullable String title,
                                       @Nullable List<String> items, @Nullable DialogInterface.OnClickListener listener) {
        return showList(context, title, ListUtils.toArray(items, CharSequence.class), listener);
    }

    public static AlertDialog showList(@NonNull Context context, @Nullable String title,
                                       @Nullable CharSequence[] items, @Nullable DialogInterface.OnClickListener listener) {
        return buildList(context, title, items, listener).show();
    }

    public static AlertDialog.Builder buildList(@NonNull Context context, @Nullable String title,
                                                @Nullable CharSequence[] items, @Nullable DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(items, listener);
    }
}
