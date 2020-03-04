package com.linsh.lshutils.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class EmptyStatusViewHolderEx extends ViewHolderEx {

    public ImageView ivImage;
    public TextView tvText;

    public EmptyStatusViewHolderEx(ViewGroup parent) {
        super(R.layout.uee_item_footer_empty, parent);
    }

    @Override
    public void initView(View itemView) {
        ivImage = itemView.findViewById(R.id.iv_item_footer_empty_image);
        tvText = itemView.findViewById(R.id.tv_item_footer_empty_text);
    }
}
