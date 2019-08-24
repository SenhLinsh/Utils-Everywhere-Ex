package com.linsh.lshutils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.linsh.lshutils.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/14
 *    desc   : 自动实现加载更多尾部的 RecyclerView Adapter 基类
 * </pre>
 */
public abstract class LoadMoreFooterRcvAdapterEx2 extends BaseRcvAdapter {
    private boolean mCanLoadMore;
    private final int itemTypeFooter = 101;
    private RecyclerView.OnScrollListener mOnScrollListener;

    public abstract int getContentItemCount();

    public int getContentItemViewType(int position) {
        return 0;
    }

    public abstract RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindContentViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        int itemCount = getContentItemCount();
        return itemCount + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return itemTypeFooter;
        }
        return getContentItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == itemTypeFooter) {
            return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_footer, parent, false));
        } else {
            return onCreateContentViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterHolder) {
            FooterHolder mHolder = (FooterHolder) holder;
            mHolder.layout.setVisibility(mCanLoadMore ? View.VISIBLE : View.GONE);
            setHolder(mHolder);
        } else {
            onBindContentViewHolder(holder, position);
        }
    }

    public void setHolder(FooterHolder mHolder) {
        if (mStatus == LoadMoreStatus.Error) {
            mHolder.pbLoading.setVisibility(View.GONE);
            mHolder.tvText.setText("加载失败, 点击重新加载");
            mHolder.layout.setOnClickListener(mOnClickMoreListener);
        } else {
            mHolder.pbLoading.setVisibility(View.VISIBLE);
            mHolder.tvText.setText("加载中...");
            mHolder.layout.setOnClickListener(null);
        }
    }

    // 设置是否显示加载更多
    public void canLoadMore(boolean canLoadMore) {
        if (mCanLoadMore != canLoadMore) {
            mCanLoadMore = canLoadMore;
            if (!canLoadMore) {
                setLoadMoreStatus(LoadMoreStatus.Finished);
            } else {
                notifyDataSetChanged();
            }
        }
    }

    // 设置加载更多的状态(如正在加载\加载失败)
    public void setLoadMoreStatus(LoadMoreStatus status) {
        if (mStatus != status) {
            mStatus = status;
            View view = findViewById(R.id.pll_layout_item_footer_layout);
            if (view != null) {
                setHolder(new FooterHolder(view));
            }
        }
    }

    private class FooterHolder extends RecyclerView.ViewHolder {
        private final ProgressBar pbLoading;
        private final TextView tvText;
        private final View layout;

        public FooterHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.pll_layout_item_footer_layout);
            pbLoading = (ProgressBar) view.findViewById(R.id.pb_layout_item_footer_loading);
            tvText = (TextView) view.findViewById(R.id.tv_layout_item_footer_text);
        }
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    private LoadMoreStatus mStatus = LoadMoreStatus.Finished;

    public enum LoadMoreStatus {
        Finished, Loading, Error
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visible = layoutManager.getChildCount();
                int total = layoutManager.getItemCount();
                int past = layoutManager.findFirstCompletelyVisibleItemPosition();
                if ((visible + past) >= total && mStatus == LoadMoreStatus.Finished && mCanLoadMore) {
                    setLoadMoreStatus(LoadMoreStatus.Loading);
                    mOnLoadMoreListener.onLoadMore();
                }
            }
        };
        recyclerView.addOnScrollListener(mOnScrollListener);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mOnScrollListener != null)
            recyclerView.addOnScrollListener(mOnScrollListener);
    }

    private View.OnClickListener mOnClickMoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setLoadMoreStatus(LoadMoreStatus.Loading);
            mOnLoadMoreListener.onLoadMore();
        }
    };
}
