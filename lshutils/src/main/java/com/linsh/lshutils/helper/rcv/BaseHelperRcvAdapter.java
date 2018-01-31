package com.linsh.lshutils.helper.rcv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/01/29
 *    desc   :
 * </pre>
 */
public abstract class BaseHelperRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RcvHelper> mHelpers;

    public void addRcvHelper(@NonNull RcvHelper rcvHelper) {
        if (mHelpers == null) mHelpers = new ArrayList<>();
        rcvHelper.setHelperRcvAdapter(this);
        mHelpers.add(rcvHelper);
    }

    public void removeRcvHelper(@NonNull RcvHelper rcvHelper) {
        if (mHelpers != null) {
            mHelpers.remove(rcvHelper);
            rcvHelper.removeHelperRcvAdapter();
        }
    }

    public void removeAllRcvHelper() {
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                helper.removeHelperRcvAdapter();
            }
            mHelpers.clear();
        }
    }

    public int getDataPosition(int position) {
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                position = helper.consumePosition(position);
                if (position < 0) return position;
            }
        }
        return position;
    }

    @Override
    public int getItemCount() {
        int itemCount = getDataItemCount();
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                itemCount = helper.getItemCount(itemCount);
            }
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                viewType = helper.getItemViewType(position);
                if (viewType > 0) return viewType;
                position = helper.consumePosition(position);
            }
        }
        viewType = getDataItemViewType(position);
        return viewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                RecyclerView.ViewHolder viewHolder = helper.onCreateViewHolder(parent, viewType);
                if (viewHolder != null)
                    return viewHolder;
            }
        }
        return onCreateItemViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                boolean consumed = helper.onBindViewHolder(holder, position);
                if (consumed) return;
                position = helper.consumePosition(position);
            }
        }
        onBindItemViewHolder(holder, position);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                helper.onViewAttachedToWindow(holder);
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                helper.onViewDetachedFromWindow(holder);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                helper.onAttachedToRecyclerView(recyclerView);
            }
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mHelpers != null) {
            for (RcvHelper helper : mHelpers) {
                helper.onDetachedFromRecyclerView(recyclerView);
            }
        }
    }

    public abstract int getDataItemCount();

    public abstract int getDataItemViewType(int position);

    public abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);
}
