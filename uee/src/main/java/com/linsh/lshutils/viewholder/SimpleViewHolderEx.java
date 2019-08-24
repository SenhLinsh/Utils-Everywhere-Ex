package com.linsh.lshutils.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/06/28
 *    desc   : 简单的 ViewHolder 基类
 * </pre>
 */
public abstract class SimpleViewHolderEx<T> extends RecyclerView.ViewHolder {

    public SimpleViewHolderEx(View itemView) {
        super(itemView);
    }

    public abstract void setData(T t, int position);

    public void onItemClick() {
    }

    public boolean onItemLongClick() {
        return false;
    }
}