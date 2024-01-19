package com.linsh.lshutils.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2023/12/19
 *    desc   :
 * </pre>
 */
public abstract class BaseRcvAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private RecyclerView mRecyclerView;

    @Nullable
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }
}
