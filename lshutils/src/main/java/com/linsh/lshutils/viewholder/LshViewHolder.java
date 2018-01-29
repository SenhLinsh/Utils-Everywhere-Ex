package com.linsh.lshutils.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/14
 *    desc   : 简单的 ViewHolder 基类
 * </pre>
 */
public class LshViewHolder extends RecyclerView.ViewHolder {

    private LshViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    public LshViewHolder(int layout, ViewGroup parent) {
        this(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    public void initView(View itemView) {
    }
}