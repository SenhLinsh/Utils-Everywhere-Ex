package com.linsh.lshutils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.linsh.lshutils.R;

import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/06/28
 *    desc   : 简单 RecyclerView.Adapter 的集成
 * </pre>
 */
public abstract class SimpleRcvAdapterEx<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H>
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

    protected T getItem(int position) {
        if (this.data != null && position < this.data.size())
            return data.get(position);
        return null;
    }

    @NonNull
    @Override
    public H onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        H viewHolder = initViewHolder(parent, viewType);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setOnLongClickListener(this);
        return viewHolder;
    }

    protected abstract H initViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.itemView.setTag(R.id.uee_tag_view_holder, holder);
        T data = getItem(position);
        onBindViewHolder(holder, data, position);
    }

    protected abstract void onBindViewHolder(H holder, T t, int position);

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

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    protected View findViewById(int id) {
        return mRecyclerView.findViewById(id);
    }

    protected View findViewWithTag(Object tag) {
        return mRecyclerView.findViewWithTag(tag);
    }

    protected View inflateView(ViewGroup parent, int layout) {
        return LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
    }

    @Override
    public void onClick(View v) {
        ViewGroup.LayoutParams params = v.getLayoutParams();
        if (params instanceof RecyclerView.LayoutParams) {
            Object tag = v.getTag(R.id.uee_tag_view_holder);
            H viewHolder = (H) tag;
            onClick(viewHolder);
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
            H viewHolder = (H) tag;
            return onLongClick(viewHolder);
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
}
