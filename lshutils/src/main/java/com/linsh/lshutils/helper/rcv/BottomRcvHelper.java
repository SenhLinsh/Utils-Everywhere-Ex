package com.linsh.lshutils.helper.rcv;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.linsh.lshutils.R;
import com.linsh.lshutils.viewholder.LshViewHolder;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/01/29
 *    desc   :
 * </pre>
 */
public class BottomRcvHelper extends RcvHelper {

    private int layout = R.layout.item_footer_bottom;
    private int itemType = generateItemType();

    public BottomRcvHelper() {
    }

    public BottomRcvHelper(int layout) {
        this.layout = layout;
    }

    @Override
    public int getItemCount(int itemCount) {
        return itemCount + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getAdapter().getDataItemCount()) {
            return itemType;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == itemType) {
            return new BottomFooterViewHolder(layout, parent);
        }
        return null;
    }

    @Override
    public boolean onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BottomFooterViewHolder) {
            return true;
        }
        return false;
    }

    @Override
    public int consumePosition(int position) {
        int itemCount = getAdapter().getDataItemCount();
        if (position == itemCount)
            return -1;
        else if (position > itemCount) {
            return position - 1;
        }
        return position;
    }

    private static class BottomFooterViewHolder extends LshViewHolder {

        public BottomFooterViewHolder(int layout, ViewGroup parent) {
            super(layout, parent);
        }
    }
}
