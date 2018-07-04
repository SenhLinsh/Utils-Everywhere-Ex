package com.linsh.lshutils.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/06/28
 *    desc   : 简单的 ViewHolder 基类
 * </pre>
 */
public abstract class SimpleViewHolder<T> extends RecyclerView.ViewHolder {

    public SimpleViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(T t, int position);

    public void onItemClick() {
    }

    public boolean onItemLongClick() {
        return false;
    }
}