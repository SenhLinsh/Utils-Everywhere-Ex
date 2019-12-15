package com.linsh.lshutils.utils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/09
 *    desc   : 工具类: 点击事件相关
 * </pre>
 */
public class ClickUtilsEx {

    private static long lastDoubleClickTime;
    private static long lastDoubleClickId;

    private ClickUtilsEx() {
    }

    /**
     * 判断是否为快速双击
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(0);
    }

    /**
     * 判断是否为快速双击
     */
    public static boolean isFastDoubleClick(int id) {
        return isDoubleClick(id, 500);
    }

    /**
     * 判断是否为双击
     */
    public static boolean isDoubleClick(long interval) {
        return isDoubleClick(0, interval);
    }

    /**
     * 判断是否为双击
     */
    public static boolean isDoubleClick(int id, long interval) {
        long time = System.currentTimeMillis();
        if (id == lastDoubleClickId) {
            long curInterval = time - lastDoubleClickTime;
            if (0 < curInterval && curInterval < interval) {
                return true;
            }
        }
        lastDoubleClickTime = time;
        lastDoubleClickId = id;
        return false;
    }
}
