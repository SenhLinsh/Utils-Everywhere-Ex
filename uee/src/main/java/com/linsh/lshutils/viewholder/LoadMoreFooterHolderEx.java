package com.linsh.lshutils.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.linsh.lshutils.adapter.LoadMoreFooterRcvAdapterEx;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/06/28
 *    desc   : 加载更多 ViewHolder 基类
 * </pre>
 */
public abstract class LoadMoreFooterHolderEx extends RecyclerView.ViewHolder {

    public LoadMoreFooterHolderEx(View itemView) {
        super(itemView);
    }

    public abstract void setStatus(LoadMoreFooterRcvAdapterEx.LoadMoreStatus status);
}
