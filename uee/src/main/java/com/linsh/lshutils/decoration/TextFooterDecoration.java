package com.linsh.lshutils.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

import com.linsh.utilseverywhere.UnitConverseUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/01/26
 *    desc   : RecyclerView 底部文本"装饰"
 *
 *             1. 可替代没有点击效果的文本 Item Footer, 更为简单高效
 *             2. 不会对 RecyclerView Adapter 的 Item Count 产生影响, 避免条目计算问题
 *             3. 可以使用 Spannable String 富文本
 * </pre>
 */
public class TextFooterDecoration extends RecyclerView.ItemDecoration {

    private TextPaint mTextPaint;
    private final Rect mBounds = new Rect();
    private int mBgColor;

    private int mHeight;
    private int mWidth;
    private int mCurHeight;

    private CharSequence mText;
    private Layout mTextLayout;
    private boolean isSingleLineString;

    public TextFooterDecoration(@NonNull CharSequence text) {
        this(text, -1);
    }

    public TextFooterDecoration(@NonNull CharSequence text, int height) {
        this(text, new TextPaint(), height, -1);
        mTextPaint.setTextSize(UnitConverseUtils.sp2px(18));
    }

    public TextFooterDecoration(@NonNull CharSequence text, @NonNull TextPaint textPaint, int height, int width) {
        mText = text;
        mTextPaint = textPaint;
        mHeight = height;
        mWidth = width;
    }

    public void setText(@NonNull CharSequence text) {
        mText = text;
        // 文字更新后,
        // 需要重新判断是否为单行普通文本,
        // 文字布局可能会发生变化, TextLayout 需要重新设置
        isSingleLineString = false;
        mTextLayout = null;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public void setTextSize(float textSize) {
        mTextPaint.setTextSize(textSize);
    }

    public void setTextColor(int color) {
        mTextPaint.setColor(color);
    }

    public void setTextBgColor(int color) {
        mTextPaint.bgColor = color;
    }

    public void setBackgroundColor(int color) {
        mBgColor = color;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemCount = parent.getAdapter().getItemCount();
        // 对最后一个条目进行偏移
        int position = parent.getChildAdapterPosition(view);
        if (position == itemCount - 1) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int width = mWidth > 0 ? mWidth : right - left;
                /*
                * 如果是单行普通文本, 则直接通过 mTextPaint 设置的文字大小为基础来获取偏移高度,
                * 并使用 Canvas 来绘制普通文本
                * 如果是多行文本或富文本, 则需通过 BoringLayout(单行)或 StaticLayout(多行)为基础
                * 来计算偏移高度, 并用该 TextLayout 来绘制文本
                */
            if (isSingleLineString) { // 已经判断过是单行普通文本
                // 不执行操作
            } else if (mTextLayout != null) { // 已经创建过的 TextLayout, 根据情况可进行复用
                checkRefreshTextLayout(width);
            } else {
                if (mText instanceof String) { // 普通文本
                    // 判断是否是要多行显示
                    mTextPaint.getTextBounds((String) mText, 0, mText.length(), mBounds);
                    if (width >= mBounds.width() && !((String) mText).contains("\n")) { // 宽度足够且没有换行符
                        mTextLayout = null;
                        isSingleLineString = true;
                        mTextPaint.setTextAlign(Paint.Align.CENTER);
                    } else {
                        createTextLayout(width, null);
                    }
                } else {
                    // 判断是否单行显示
                    BoringLayout.Metrics boring = BoringLayout.isBoring(mText, mTextPaint);
                    if (boring != null && boring.width <= width) {
                        createTextLayout(width, boring);
                    } else {
                        createTextLayout(width, null);
                    }
                }
            }
            // 设置偏移&绘制高度
            if (mHeight >= 0) { // 自定义高度
                mCurHeight = mHeight;
            } else if (mTextLayout != null) { // mTextLayout 高度
                mCurHeight = mTextLayout.getHeight() + ((int) mTextPaint.getTextSize()) * 4 / 3;
            } else { // 单行普通文本高度
                mCurHeight = (int) mTextPaint.getTextSize() * 7 / 3;
            }
            outRect.bottom = mCurHeight;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int itemCount = parent.getAdapter().getItemCount();
        View view = parent.getChildAt(parent.getChildCount() - 1);
        int position = parent.getChildAdapterPosition(view);
        if (position == itemCount - 1) {
            c.save();
            // 计算 Footer 所占矩形范围
            parent.getDecoratedBoundsWithMargins(view, mBounds);
            int bottom = mBounds.bottom + Math.round(view.getTranslationY());
            int top = bottom - mCurHeight;
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            // 裁切范围
            c.clipRect(left, top, right, bottom);
            // 绘制背景
            if (mBgColor != 0) c.drawColor(mBgColor);
            // 绘制文字
            if (mTextLayout != null) {
                c.translate(0, (bottom + top) / 2f - mTextLayout.getHeight() / 2f);
                mTextLayout.draw(c);
            } else {
                drawText(c, bottom, top, left, right);
            }
            c.restore();
        }
    }

    // 绘制普通单行文本
    private void drawText(Canvas c, int bottom, int top, int left, int right) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float yOff = (bottom + top) / 2 - (fontMetrics.bottom + fontMetrics.top) / 2;
        c.drawText(mText, 0, mText.length(), (right - left) / 2f, yOff, mTextPaint);
    }

    // 检查绘制宽度是否发生变化, 如果发生变化, 需要重新创建 mTextLayout
    private void checkRefreshTextLayout(int width) {
        if (mTextLayout.getWidth() != width) {
            if (mTextLayout instanceof BoringLayout) {
                BoringLayout.Metrics boring = BoringLayout.isBoring(mText, mTextPaint);
                if (boring != null) {
                    mTextLayout = new BoringLayout(mText, mTextPaint, width, Layout.Alignment.ALIGN_CENTER,
                            1.0F, 1.0F, boring, true);
                    return;
                }
            }
            mTextLayout = new StaticLayout(mText, mTextPaint, width, Layout.Alignment.ALIGN_CENTER,
                    1.0F, 0.0F, true);
        }
    }

    // 创建 mTextLayout
    private void createTextLayout(int width, BoringLayout.Metrics boring) {
        if (boring != null) {
            mTextLayout = new BoringLayout(mText, mTextPaint, width, Layout.Alignment.ALIGN_CENTER,
                    1.0F, 1.0F, boring, true);
        } else {
            mTextLayout = new StaticLayout(mText, mTextPaint, width, Layout.Alignment.ALIGN_CENTER,
                    1.0F, 0.0F, true);
        }
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

}
