package com.linsh.lshutils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2021/08/11
 *    desc   : 线程相关
 * </pre>
 */
public class ThreadUtilsEx {

    /**
     * 使当前线程进入睡眠
     * <p>
     * 注：该方法抓取了线程睡眠的异常
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
