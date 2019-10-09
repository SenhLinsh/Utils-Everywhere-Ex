package com.linsh.lshutils.utils;

import com.linsh.utilseverywhere.UnitConverseUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/10/10
 *    desc   :
 * </pre>
 */
public class Dps {

    public static final int TOOLBAR_HEIGHT = 56;
    public static final int TOOLBAR_HEIGHT_LANDSCAPE = 48;

    public static int toPx(int dp) {
        return UnitConverseUtils.dp2px(dp);
    }

    public static int toPx(float dp) {
        return UnitConverseUtils.dp2px(dp);
    }
}
