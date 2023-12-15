package com.linsh.lshutils.adapter;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2023/12/15
 *    desc   :
 * </pre>
 */
public interface TextWatcherAdapter extends TextWatcher {

    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    default void afterTextChanged(Editable s) {
    }
}