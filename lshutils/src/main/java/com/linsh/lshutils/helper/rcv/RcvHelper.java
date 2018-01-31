package com.linsh.lshutils.helper.rcv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/01/29
 *    desc   :
 * </pre>
 */
public abstract class RcvHelper {

    private BaseHelperRcvAdapter mAdapter;
    private static int sItemTypeGenerator = 666;

    protected void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {

    }

    protected void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {

    }

    protected void onAttachedToRecyclerView(RecyclerView recyclerView) {

    }

    protected void onDetachedFromRecyclerView(RecyclerView recyclerView) {

    }

    protected BaseHelperRcvAdapter getAdapter() {
        return mAdapter;
    }

    protected void setHelperRcvAdapter(@NonNull BaseHelperRcvAdapter adapter) {
        mAdapter = adapter;
    }

    protected void removeHelperRcvAdapter() {
        mAdapter = null;
    }

    protected int generateItemType() {
        return sItemTypeGenerator++;
    }

    protected abstract int getItemCount(int itemCount);

    protected abstract int getItemViewType(int position);

    protected abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    protected abstract boolean onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    protected abstract int consumePosition(int position);

}
