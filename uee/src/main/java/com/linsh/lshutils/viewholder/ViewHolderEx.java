package com.linsh.lshutils.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/14
 *    desc   : 简单的 ViewHolder 基类
 * </pre>
 */
public class ViewHolderEx extends RecyclerView.ViewHolder {

    public ViewHolderEx(View itemView) {
        super(itemView);
        initView(itemView);
    }

    public ViewHolderEx(int layout, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    public void initView(View itemView) {
    }
}