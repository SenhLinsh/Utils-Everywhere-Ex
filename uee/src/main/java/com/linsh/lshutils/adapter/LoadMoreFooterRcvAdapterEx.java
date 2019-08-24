package com.linsh.lshutils.adapter;

import android.view.ViewGroup;

import com.linsh.lshutils.viewholder.LoadMoreFooterHolderEx;
import com.linsh.lshutils.viewholder.SimpleViewHolderEx;

import java.util.List;


/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/14
 *    desc   : 自动实现加载更多尾部的 RecyclerView Adapter 基类
 * </pre>
 */
public abstract class LoadMoreFooterRcvAdapterEx<T, H extends SimpleViewHolderEx<T>> extends SimpleRcvAdapterEx<T, SimpleViewHolderEx<T>> {

    private LoadMoreStatus mStatus = LoadMoreStatus.Loaded;
    private final int itemTypeFooter = 101;
    /**
     * 显示加载更多时机的偏移量, 默认为 3, 即当距离底部还有 3 个条目时, 执行加载更多的回调
     */
    private int offsetToLoad = 3;


    @Override
    public void setData(List<T> data) {
        setData(data, false);
    }

    public void setData(List<T> data, boolean hasMore) {
        if (data == null || data.size() == 0) {
            setLoadMoreStatus(LoadMoreStatus.Closed, false);
        } else if (hasMore) {
            setLoadMoreStatus(LoadMoreStatus.Loaded, false);
        } else {
            setLoadMoreStatus(LoadMoreStatus.Finished, false);
        }
        super.setData(data);
    }

    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount();
        if (itemCount == 0)
            return 0;
        return itemCount + (mStatus != LoadMoreStatus.Closed ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && mStatus != LoadMoreStatus.Closed) {
            return itemTypeFooter;
        }
        return super.getItemViewType(position);
    }

    @Override
    protected SimpleViewHolderEx<T> getViewHolder(ViewGroup parent, int viewType) {
        if (viewType == itemTypeFooter) {
            return getFooterHolder(parent);
        } else {
            return getContentViewHolder(parent, viewType);
        }
    }

    protected abstract H getContentViewHolder(ViewGroup parent, int viewType);

    protected abstract LoadMoreFooterHolderEx getFooterHolder(ViewGroup parent);

    @Override
    protected void onBindViewHolder(SimpleViewHolderEx<T> holder, T data, int position) {
        if (mStatus == LoadMoreStatus.Loaded) {
            // 计算当前位置和末尾的偏移量, 如果接近末尾, 则回调加载更多
            if (position >= getItemCount() - 1 - offsetToLoad) {
                setLoadMoreStatus(LoadMoreStatus.Loading, false);
                if (mOnLoadMoreListener != null) {
                    mOnLoadMoreListener.onLoadMore();
                }
            }
        }
        if (holder instanceof LoadMoreFooterHolderEx) {
            // 如果是 Footer, 则更新状态
            LoadMoreFooterHolderEx footerHolder = (LoadMoreFooterHolderEx) holder;
            footerHolder.setStatus(mStatus);
        }
        super.onBindViewHolder(holder, data, position);
    }

    public LoadMoreStatus getLoadMoreStatus() {
        return mStatus;
    }

    public void setLoadMoreStatus(LoadMoreStatus status) {
        setLoadMoreStatus(status, true);
    }

    /**
     * 设置加载更多的状态(如正在加载\加载失败)
     */
    private void setLoadMoreStatus(LoadMoreStatus status, boolean refresh) {
        if (mStatus != status) {
            mStatus = status;
            if (refresh) {
                // 找到最后一个条目, 如果是 Footer, 则更新状态
                int itemCount = getItemCount();
                if (itemCount > 0) {
                    notifyItemChanged(itemCount - 1);
                }
            }
        }
    }

    /**
     * 设置显示加载更多时机的偏移量, 即当距离底部还有 offsetToLoad 个条目时, 执行加载更多的回调
     *
     * @param offsetToLoad 显示加载更多时机的偏移量
     */
    public void setLoadTime(int offsetToLoad) {
        this.offsetToLoad = Math.max(offsetToLoad, 0);
    }

    protected OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public enum LoadMoreStatus {
        /**
         * 加载中
         */
        Loading,
        /**
         * 加载结束 (单次)
         */
        Loaded,
        /**
         * 加载失败
         */
        Error,
        /**
         * 加载完成 (全部加载完成, 不再需要加载)
         */
        Finished,
        /**
         * 关闭加载更多功能
         */
        Closed
    }
}
