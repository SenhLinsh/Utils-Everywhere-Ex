package com.linsh.lshutils.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsh.lshutils.R;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/12/18
 *    desc   :
 * </pre>
 */
public class BottomFooterViewHolderEx extends ViewHolderEx {

    public TextView tvBottom;

    public BottomFooterViewHolderEx(ViewGroup parent) {
        super(R.layout.item_footer_bottom, parent);
    }

    @Override
    public void initView(View itemView) {
        tvBottom = (TextView) itemView;
    }
}
