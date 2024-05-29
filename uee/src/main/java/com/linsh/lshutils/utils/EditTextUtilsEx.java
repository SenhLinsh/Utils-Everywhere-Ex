package com.linsh.lshutils.utils;

import android.widget.EditText;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/10
 *    desc   : 工具类: 处理 EditText 相关
 * </pre>
 */
public class EditTextUtilsEx {

    private EditTextUtilsEx() {
    }

    /**
     * 将 EditText 的光标移动至所显示文字的末尾
     *
     * @param editText EditText
     */
    public static void moveCursorToLast(EditText editText) {
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
    }

    /**
     * 关闭 EditText 的输入 & 编辑功能
     *
     * @param editText EditText
     */
    public static void disableEditState(EditText editText) {
        editText.clearFocus();
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    /**
     * 打开 EditText 的输入 & 编辑功能
     *
     * @param editText    EditText
     * @param focusNeeded 是否需要获取光标
     */
    public static void enableEditState(EditText editText, boolean focusNeeded) {
        editText.setFocusableInTouchMode(true);
        editText.setFocusable(true);
        if (focusNeeded) {
            editText.requestFocus();
            moveCursorToLast(editText);
        }
    }

    /**
     * 根据行数选中指定行
     *
     * @param editText EditText
     * @param line     行数
     */
    public static void selectLine(EditText editText, int line) {
        int start = editText.getLayout().getLineStart(line);
        int end = editText.getLayout().getLineEnd(line);
        editText.requestFocus();
        editText.setSelection(start, end);
    }

    /**
     * 根据位置选中指定行
     *
     * @param editText        EditText
     * @param x               x 坐标
     * @param y               y 坐标
     * @param clearWhitespace 是否去掉空格和换行符
     */
    public static void selectLine(EditText editText, float x, float y, boolean clearWhitespace) {
        int offset = editText.getOffsetForPosition(x, y);
        int start = editText.getLayout().getLineStart(editText.getLayout().getLineForOffset(offset));
        int end = editText.getLayout().getLineEnd(editText.getLayout().getLineForOffset(offset));
        while (clearWhitespace && start < end && Character.isWhitespace(editText.getText().charAt(start))) {
            start++;
        }
        while (clearWhitespace && start < end && Character.isWhitespace(editText.getText().charAt(end - 1))) {
            end--;
        }
        editText.requestFocus();
        editText.setSelection(start, end);
    }

    /**
     * 获取指定行的文字
     *
     * @param editText EditText
     * @param line     行数
     * @return 指定行的文字
     */
    public static String getLineText(EditText editText, int line) {
        int start = editText.getLayout().getLineStart(line);
        int end = editText.getLayout().getLineEnd(line);
        return editText.getText().subSequence(start, end).toString();
    }

    /**
     * 获取指定行的文字
     *
     * @param editText EditText
     * @param x        x 坐标
     * @param y        y 坐标
     * @return 指定行的文字
     */
    public static String getLineText(EditText editText, float x, float y) {
        int offset = editText.getOffsetForPosition(x, y);
        int start = editText.getLayout().getLineStart(editText.getLayout().getLineForOffset(offset));
        int end = editText.getLayout().getLineEnd(editText.getLayout().getLineForOffset(offset));
        return editText.getText().subSequence(start, end).toString();
    }
}
