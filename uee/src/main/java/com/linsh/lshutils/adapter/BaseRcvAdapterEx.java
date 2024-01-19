package com.linsh.lshutils.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.linsh.lshutils.R;

import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    date   : 2024/01/18
 *    desc   : RecyclerView 适配器扩展基类
 *
 *            一个常规的扩展基类，主要封装：
 *            1. 数据集合的管理
 *            2. 点击事件的回调（点击和长按）
 * </pre>
 */
public abstract class BaseRcvAdapterEx<T, H extends RecyclerView.ViewHolder> extends BaseRcvAdapter<H>
        implements View.OnClickListener, View.OnLongClickListener {

    private List<? extends T> data;

    public List<? extends T> getData() {
        return data;
    }

    public void setData(List<? extends T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    protected T getItem(int position) {
        if (this.data != null && position < this.data.size()) {
            return this.data.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        H viewHolder = onCreateItemViewHolder(parent, viewType);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setOnLongClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.itemView.setTag(R.id.uee_tag_view_holder, holder);
        onBindItemViewHolder(holder, getItem(position), position);
    }

    protected abstract H onCreateItemViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindItemViewHolder(H holder, T item, int position);

    @Override
    public void onClick(View v) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        if (params instanceof RecyclerView.LayoutParams) {
            Object tag = v.getTag(R.id.uee_tag_view_holder);
            if (tag instanceof RecyclerView.ViewHolder) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) tag;
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder, holder.getAdapterPosition());
                }
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        if (params instanceof RecyclerView.LayoutParams) {
            Object tag = v.getTag(R.id.uee_tag_view_holder);
            if (tag instanceof RecyclerView.ViewHolder) {
                RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) tag;
                if (mOnItemLongClickListener != null) {
                    return mOnItemLongClickListener.onItemLongClick(holder, holder.getAdapterPosition());
                }
            }
        }
        return false;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder holder, int position);
    }

    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView.ViewHolder holder, int position);
    }
}
