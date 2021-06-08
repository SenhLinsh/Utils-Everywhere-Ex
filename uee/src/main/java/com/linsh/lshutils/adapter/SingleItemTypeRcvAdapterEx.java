package com.linsh.lshutils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.linsh.lshutils.R;

import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/14
 *    desc   : 简单抽取的 RecyclerView Adapter 基类, 适用于单条目类型
 * </pre>
 */
public abstract class SingleItemTypeRcvAdapterEx<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H>
        implements View.OnClickListener, View.OnLongClickListener {

    private List<T> data;

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return createViewHolder(view, viewType);
    }

    protected abstract int getLayout();

    protected abstract H createViewHolder(View itemView, int viewType);

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.itemView.setTag(R.id.uee_tag_item_view, position);
        onBindViewHolder(holder, data.get(position), position);
    }

    protected abstract void onBindViewHolder(H holder, T t, int position);

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag(R.id.uee_tag_item_view);
        if (mOnItemClickListener != null && tag instanceof Integer) {
            mOnItemClickListener.onItemClick((Integer) tag);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public boolean onLongClick(View v) {
        Object tag = v.getTag(R.id.uee_tag_item_view);
        if (mOnItemLongClickListener != null && tag instanceof Integer) {
            mOnItemLongClickListener.onItemLongClick(v, (Integer) tag);
            return true;
        }
        return false;
    }

    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
