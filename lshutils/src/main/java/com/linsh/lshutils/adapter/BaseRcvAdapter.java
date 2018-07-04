package com.linsh.lshutils.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView.Adapter 基类
 */
public abstract class BaseRcvAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected RecyclerView mRecyclerView;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    protected View findViewById(int id) {
        return mRecyclerView.findViewById(id);
    }

    protected View findViewWithTag(Object tag) {
        return mRecyclerView.findViewWithTag(tag);
    }
}
