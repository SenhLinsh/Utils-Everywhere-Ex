package com.linsh.lshutils.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linsh.lshutils.R;
import com.linsh.lshutils.viewholder.SimpleViewHolder;

import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/06/28
 *    desc   : 简单 RecyclerView.Adapter 的集成, 配合 {@link SimpleViewHolder} 使用
 * </pre>
 */
public abstract class SimpleRcvAdapter<T, H extends SimpleViewHolder<T>> extends RecyclerView.Adapter<H>
        implements View.OnClickListener, View.OnLongClickListener {

    private RecyclerView mRecyclerView;

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        H viewHolder = getViewHolder(parent, viewType);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setOnLongClickListener(this);
        return viewHolder;
    }

    protected abstract H getViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.itemView.setTag(R.id.tag_view_holder, holder);
        T data = null;
        if (this.data != null && position < this.data.size()) {
            data = this.data.get(position);
        }
        onBindViewHolder(holder, data, position);
    }

    protected void onBindViewHolder(H holder, T data, int position) {
        holder.setData(data, position);
    }

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

    @Override
    public void onClick(View v) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        if (params instanceof RecyclerView.LayoutParams) {
            Object tag = v.getTag(R.id.tag_view_holder);
            if (tag instanceof SimpleViewHolder) {
                SimpleViewHolder viewHolder = (SimpleViewHolder) tag;
                onClick(viewHolder);
                viewHolder.onItemClick();
            }
        }
    }

    public void onClick(SimpleViewHolder viewHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(viewHolder, viewHolder.getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        if (params instanceof RecyclerView.LayoutParams) {
            Object tag = v.getTag(R.id.tag_view_holder);
            if (tag instanceof SimpleViewHolder) {
                SimpleViewHolder viewHolder = (SimpleViewHolder) tag;
                return onLongClick(viewHolder) || viewHolder.onItemLongClick();
            }
        }
        return false;
    }

    public boolean onLongClick(SimpleViewHolder viewHolder) {
        if (mOnItemLongClickListener != null) {
            return mOnItemLongClickListener.onItemLongClick(viewHolder, viewHolder.getAdapterPosition());
        }
        return false;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(SimpleViewHolder holder, int position);
    }

    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(SimpleViewHolder holder, int position);
    }

    public static View inflateItem(ViewGroup parent, int layout) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }
}
