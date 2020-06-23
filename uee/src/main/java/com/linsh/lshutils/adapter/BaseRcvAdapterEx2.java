package com.linsh.lshutils.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.linsh.lshutils.R;
import com.linsh.lshutils.viewholder.ViewHolderEx;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/06/23
 *    desc   :
 * </pre>
 */
public abstract class BaseRcvAdapterEx2<T, H extends ViewHolderEx> extends BaseRcvAdapter<H>
        implements View.OnClickListener, View.OnLongClickListener {

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        H viewHolder = onCreateItemViewHolder(parent, viewType);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setOnLongClickListener(this);
        return viewHolder;
    }

    protected abstract T getItem(int position);

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
            if (tag instanceof ViewHolderEx) {
                ViewHolderEx holder = (ViewHolderEx) tag;
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
            if (tag instanceof ViewHolderEx) {
                ViewHolderEx holder = (ViewHolderEx) tag;
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
        void onItemClick(ViewHolderEx holder, int position);
    }

    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(ViewHolderEx holder, int position);
    }
}
