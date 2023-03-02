package com.linsh.lshutils.utils;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

import com.linsh.utilseverywhere.BitmapUtils;
import com.linsh.utilseverywhere.ColorUtils;
import com.linsh.utilseverywhere.DrawableUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/09
 *    desc   : 工具类: View 背景相关
 * </pre>
 */
public class BackgroundUtilsEx {

    private BackgroundUtilsEx() {
    }

    /**
     * 给 View 添加触按效果, 触按效果为混合一个透明度为 30% 的黑色背景
     *
     * @param view 指定添加效果的 View
     */
    public static void addPressedEffect(View view) {
        addPressedEffect(view, 0x33333333);
    }

    /**
     * 给 View 添加一个颜色或者颜色蒙层作为触按效果<br/>
     * 该方法将自动在原本背景的基础上给 View 添加一个触按状态下的颜色混合 (新状态颜色 = 原本颜色 + color)
     *
     * @param view  指定添加效果的 View
     * @param color 覆盖触按状态的颜色
     */
    public static void addPressedEffect(View view, int color) {
        addStateColorCover(view, new int[][]{new int[]{android.R.attr.state_pressed}}, color);
    }

    /**
     * 给 View 添加选择效果, 触按效果为混合一个透明度为 30% 的黑色背景
     *
     * @param view 指定添加效果的 View
     */
    public static void addSelectedEffect(View view) {
        addSelectedEffect(view, 0x33333333);
    }

    /**
     * 给 View 添加一个颜色或者颜色蒙层作为选择效果<br/>
     * 该方法将自动在原本背景的基础上给 View 添加一个触按状态下的颜色混合 (新状态颜色 = 原本颜色 + color)
     *
     * @param view  指定添加效果的 View
     * @param color 覆盖触按状态的颜色
     */
    public static void addSelectedEffect(View view, int color) {
        addStateColorCover(view, new int[][]{new int[]{android.R.attr.state_selected}}, color);
    }

    /**
     * 给 View 添加触按和选择效果, 触按效果为混合一个透明度为 30% 的黑色背景
     *
     * @param view 指定添加效果的 View
     */
    public static void addPressedAndSelectedEffect(View view) {
        addStateColorCover(view, new int[][]{new int[]{android.R.attr.state_pressed}, new int[]{android.R.attr.state_selected}}, 0x33333333);
    }

    /**
     * 给 View 添加一个颜色或者颜色蒙层作为触按和选择效果<br/>
     * 该方法将自动在原本背景的基础上给 View 添加一个触按状态下的颜色混合 (新状态颜色 = 原本颜色 + color)
     *
     * @param view  指定添加效果的 View
     * @param color 覆盖触按状态的颜色
     */
    public static void addPressedAndSelectedEffect(View view, int color) {
        addStateColorCover(view, new int[][]{new int[]{android.R.attr.state_pressed}, new int[]{android.R.attr.state_selected}}, color);
    }

    /**
     * 给控件添加指定状态的颜色覆盖
     * <p>
     * 指定的颜色会在默认的颜色上进行覆盖得出该状态的颜色
     * <p>
     * 注: 覆盖的颜色建议使用具有透明度的颜色, 才能达到覆盖效果
     */
    public static void addStateColorCover(View view, int[][] stateSets, int color) {
        // 透明度为 FF 的遮罩, 不需要颜色混淆
        if (Color.alpha(color) == 0xFF) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            for (int[] stateSet : stateSets) {
                stateListDrawable.addState(stateSet, new ColorDrawable(color));
            }
            stateListDrawable.addState(new int[]{}, view.getBackground());
            view.setBackground(stateListDrawable);
            return;
        }
        Drawable stateDr = null;
        Drawable background = view.getBackground();
        if (background == null) {
            stateDr = new ColorDrawable(color);
        } else if (background instanceof ColorDrawable) {
            int fgColor = ((ColorDrawable) background).getColor();
            int stateColor = ColorUtils.coverColor(fgColor, color);
            stateDr = new ColorDrawable(stateColor);
        } else if (background instanceof BitmapDrawable) {
            Bitmap fgBitmap = ((BitmapDrawable) background).getBitmap();
            Bitmap stateBitmap = BitmapUtils.addColorMask(fgBitmap, color, false);
            stateDr = BitmapUtils.toDrawable(stateBitmap);
        } else if (background instanceof GradientDrawable) {
            GradientDrawable gradientDr = (GradientDrawable) background;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                ColorStateList stateList = gradientDr.getColor();
                if (stateList != null) {
                    int normalColor = stateList.getColorForState(new int[]{}, Color.TRANSPARENT);
                    int stateColor = ColorUtils.coverColor(normalColor, color);
                    stateDr = new ColorDrawable(stateColor);
                } else {
                    stateDr = new ColorDrawable(color);
                }
            }
        } else if (background instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) background;
            int[] temp = stateListDrawable.getState();
            stateListDrawable.setState(new int[]{});
            Drawable normalStateDr = stateListDrawable.getCurrent();
            stateListDrawable.setState(temp);
            stateDr = DrawableUtils.addColorMask(normalStateDr, color);
        }
        if (stateDr == null) {
            Bitmap bitmap = DrawableUtils.toBitmap(background);
            Bitmap stateBitmap = BitmapUtils.addColorMask(bitmap, color, false);
            stateDr = BitmapUtils.toDrawable(stateBitmap);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int[] stateSet : stateSets) {
            stateListDrawable.addState(stateSet, stateDr);
        }
        stateListDrawable.addState(new int[]{}, view.getBackground());
        view.setBackground(stateListDrawable);
    }
}
