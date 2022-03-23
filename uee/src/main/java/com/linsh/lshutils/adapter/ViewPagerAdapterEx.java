package com.linsh.lshutils.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/14
 *    desc   : 用于简化 ViewPager Adapter 操作的基类
 * </pre>
 */
public abstract class ViewPagerAdapterEx<T> extends PagerAdapter {

    private List<? extends T> data;

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(container, position);
        container.addView(view);
        return view;
    }

    protected abstract View getView(ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setData(List<? extends T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<? extends T> getData() {
        return data;
    }
}
