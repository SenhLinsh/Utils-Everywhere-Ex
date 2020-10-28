package com.linsh.lshutils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.linsh.lshutils.R;
import com.linsh.lshutils.viewholder.SimpleViewHolderEx;

import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/06/28
 *    desc   : 简单 RecyclerView.Adapter 的集成, 配合 {@link SimpleViewHolderEx} 使用
 * </pre>
 */
public abstract class SimpleRcvAdapterEx2<T, H extends SimpleViewHolderEx<T>> extends RecyclerView.Adapter<H>
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
        H viewHolder = initViewHolder(parent, viewType);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setOnLongClickListener(this);
        return viewHolder;
    }

    protected abstract H initViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.itemView.setTag(R.id.uee_tag_view_holder, holder);
        T data = null;
        if (this.data != null && position < this.data.size()) {
            data = this.data.get(position);
        }
        onBindViewHolder(holder, data, position);
    }

    protected void onBindViewHolder(H holder, T t, int position) {
        holder.setData(t, position);
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
            Object tag = v.getTag(R.id.uee_tag_view_holder);
            if (tag instanceof SimpleViewHolderEx) {
                H viewHolder = (H) tag;
                onClick(viewHolder);
                viewHolder.onItemClick();
            }
        }
    }

    public void onClick(H viewHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(viewHolder, viewHolder.getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        if (params instanceof RecyclerView.LayoutParams) {
            Object tag = v.getTag(R.id.uee_tag_view_holder);
            if (tag instanceof SimpleViewHolderEx) {
                H viewHolder = (H) tag;
                return onLongClick(viewHolder) || viewHolder.onItemLongClick();
            }
        }
        return false;
    }

    public boolean onLongClick(H viewHolder) {
        if (mOnItemLongClickListener != null) {
            return mOnItemLongClickListener.onItemLongClick(viewHolder, viewHolder.getAdapterPosition());
        }
        return false;
    }

    private OnItemClickListener<H> mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener<H> listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener<H> {
        void onItemClick(H holder, int position);
    }

    private OnItemLongClickListener<H> mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener<H> listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener<H> {
        boolean onItemLongClick(H holder, int position);
    }

    public static View inflateItem(ViewGroup parent, int layout) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }
}
