package com.linsh.lshutils.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/14
 *    desc   : 简化的 RecyclerView Adapter 基类, 可直接使用内部类创建继承的子类实现 Adapter 功能
 * </pre>
 */
public abstract class SimplifiedRcvAdapterEx<T> extends RecyclerView.Adapter<SimplifiedRcvAdapterEx.SimplifiedViewHolderEx> {

    private final int layoutId;
    private List<T> list;

    public SimplifiedRcvAdapterEx(int layoutId) {
        this.layoutId = layoutId;
    }

    public SimplifiedRcvAdapterEx(int layoutId, List<T> list) {
        this.layoutId = layoutId;
        this.list = list;
    }

    @Override
    public SimplifiedViewHolderEx onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        SimplifiedViewHolderEx viewHolder = new SimplifiedViewHolderEx(itemView);
        if (mOnItemClickListener != null) {
            viewHolder.setOnItemClickListener(new OnItemClickListener<Void>() {
                @Override
                public void onItemClick(SimplifiedViewHolderEx viewHolder, Void data, int position) {
                    mOnItemClickListener.onItemClick(viewHolder, list.get(position), position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            viewHolder.setOnItemLongClickListener(new OnItemLongClickListener<Void>() {
                @Override
                public boolean onItemLongClick(SimplifiedViewHolderEx viewHolder, Void data, int position) {
                    return mOnItemLongClickListener.onItemLongClick(viewHolder, list.get(position), position);
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SimplifiedViewHolderEx holder, int position) {
        T item = list.get(position);
        onBindViewHolder(holder, item, position);
    }

    protected abstract void onBindViewHolder(SimplifiedViewHolderEx holder, T item, int position);

    public void setData(List<T> list) {
        this.list = list;
    }

    public List<T> getData() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private OnItemClickListener<T> mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(SimplifiedViewHolderEx viewHolder, T item, int position);
    }

    private OnItemLongClickListener<T> mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        mOnItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(SimplifiedViewHolderEx viewHolder, T item, int position);
    }

    public static class SimplifiedViewHolderEx extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private OnItemClickListener<Void> mOnItemClickListener;
        private OnItemLongClickListener<Void> mOnItemLongClickListener;
        private SparseArray<View> mViews;
        private int curItemPosition = -1;

        public SimplifiedViewHolderEx(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public SimplifiedViewHolderEx setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }

        public SimplifiedViewHolderEx setImageResource(int viewId, int resId) {
            ImageView view = getView(viewId);
            view.setImageResource(resId);
            return this;
        }

        public SimplifiedViewHolderEx setImageBitmap(int viewId, Bitmap bitmap) {
            ImageView view = getView(viewId);
            view.setImageBitmap(bitmap);
            return this;
        }

        public SimplifiedViewHolderEx setImageDrawable(int viewId, Drawable drawable) {
            ImageView view = getView(viewId);
            view.setImageDrawable(drawable);
            return this;
        }

        public SimplifiedViewHolderEx setBackgroundColor(int viewId, int color) {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        public SimplifiedViewHolderEx setBackgroundRes(int viewId, int backgroundRes) {
            View view = getView(viewId);
            view.setBackgroundResource(backgroundRes);
            return this;
        }

        public SimplifiedViewHolderEx setTextColor(int viewId, int textColor) {
            TextView view = getView(viewId);
            view.setTextColor(textColor);
            return this;
        }

        public SimplifiedViewHolderEx setTextColorRes(int viewId, int textColorRes) {
            TextView view = getView(viewId);
            view.setTextColor(view.getContext().getResources().getColor(textColorRes));
            return this;
        }

        @SuppressLint("NewApi")
        public SimplifiedViewHolderEx setAlpha(int viewId, float value) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                getView(viewId).setAlpha(value);
            } else {
                // Pre-honeycomb hack to set Alpha value
                AlphaAnimation alpha = new AlphaAnimation(value, value);
                alpha.setDuration(0);
                alpha.setFillAfter(true);
                getView(viewId).startAnimation(alpha);
            }
            return this;
        }

        public SimplifiedViewHolderEx setVisible(int viewId, boolean visible) {
            View view = getView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return this;
        }

        public SimplifiedViewHolderEx linkify(int viewId) {
            TextView view = getView(viewId);
            Linkify.addLinks(view, Linkify.ALL);
            return this;
        }

        public SimplifiedViewHolderEx setTypeface(Typeface typeface, int... viewIds) {
            for (int viewId : viewIds) {
                TextView view = getView(viewId);
                view.setTypeface(typeface);
                view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
            return this;
        }

        public SimplifiedViewHolderEx setProgress(int viewId, int progress) {
            ProgressBar view = getView(viewId);
            view.setProgress(progress);
            return this;
        }

        public SimplifiedViewHolderEx setProgress(int viewId, int progress, int max) {
            ProgressBar view = getView(viewId);
            view.setMax(max);
            view.setProgress(progress);
            return this;
        }

        public SimplifiedViewHolderEx setMax(int viewId, int max) {
            ProgressBar view = getView(viewId);
            view.setMax(max);
            return this;
        }

        public SimplifiedViewHolderEx setRating(int viewId, float rating) {
            RatingBar view = getView(viewId);
            view.setRating(rating);
            return this;
        }

        public SimplifiedViewHolderEx setRating(int viewId, float rating, int max) {
            RatingBar view = getView(viewId);
            view.setMax(max);
            view.setRating(rating);
            return this;
        }

        public SimplifiedViewHolderEx setTag(int viewId, Object tag) {
            View view = getView(viewId);
            view.setTag(tag);
            return this;
        }

        public SimplifiedViewHolderEx setTag(int viewId, int key, Object tag) {
            View view = getView(viewId);
            view.setTag(key, tag);
            return this;
        }

        public SimplifiedViewHolderEx setChecked(int viewId, boolean checked) {
            Checkable view = (Checkable) getView(viewId);
            view.setChecked(checked);
            return this;
        }

        public void setOnItemClickListener(OnItemClickListener<Void> listener) {
            mOnItemClickListener = listener;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(this, null, getAdapterPosition());
            }
        }

        public void setOnItemLongClickListener(OnItemLongClickListener<Void> listener) {
            mOnItemLongClickListener = listener;
        }

        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                return mOnItemLongClickListener.onItemLongClick(this, null, getAdapterPosition());
            }
            return false;
        }
    }
}
