package com.linsh.lshutils.decoration;


import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/28
 *    desc   : 为 RecyclerView 绘制空数据状态显示
 * </pre>
 */
public class EmptyStatusDecoration extends StatusItemDecoration {

    public EmptyStatusDecoration(int layout) {
        super(layout);
    }

    @Override
    public boolean shouldShowStatus(RecyclerView parent, RecyclerView.State state) {
        return parent.getAdapter() == null || parent.getAdapter().getItemCount() == 0;
    }
}
