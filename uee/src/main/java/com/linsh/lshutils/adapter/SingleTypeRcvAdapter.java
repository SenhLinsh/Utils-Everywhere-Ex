package com.linsh.lshutils.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.linsh.lshutils.viewholder.SimpleViewHolder;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/07/04
 *    desc   : 单条目类型 RecyclerView.Adapter 的简单集成
 * </pre>
 */
public abstract class SingleTypeRcvAdapter<T, H extends SimpleViewHolder<T>> extends SimpleRcvAdapter<T, H> {

    @Override
    protected H getViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(inflateItem(parent, getLayout()));
    }

    protected abstract int getLayout();

    protected abstract H getViewHolder(View itemView);
}
