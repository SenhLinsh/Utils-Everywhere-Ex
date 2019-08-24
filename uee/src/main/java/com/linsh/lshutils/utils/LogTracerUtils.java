package com.linsh.lshutils.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.linsh.utilseverywhere.ContextUtils;
import com.linsh.utilseverywhere.LogUtils;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/28
 *    desc   : 工具类: 日志追踪相关
 *             简介: 该类将打印并缓存最近调用的 Log 日志, 再需要时(比如不明真相的崩溃后, 可以通过查看 Log 日志来分析崩溃发生的步骤)
 * </pre>
 */
public class LogTracerUtils {

    private static int mainCount = 10;
    private static int maxCount = 20;
    private static SoftReference<LinkedList<String>> extraTracesReference;
    private static LinkedList<String> traces;

    static {
        traces = new LinkedList<>();
        extraTracesReference = new SoftReference<>(new LinkedList<String>());
    }

    private LogTracerUtils() {
    }

    private static Context getContext() {
        return ContextUtils.get();
    }

    public static void init(int mainCount, int maxCount) {
        LogTracerUtils.mainCount = mainCount;
        LogTracerUtils.maxCount = maxCount;
    }

    public static void i(String msg) {
        LogUtils.i(msg);
        traces.add(msg);
        while (traces.size() > 10) {
            String first = traces.removeFirst();
            LinkedList<String> extraTraces = extraTracesReference.get();
            if (extraTraces == null) {
                extraTraces = new LinkedList<>();
                extraTracesReference = new SoftReference<>(extraTraces);
            }
            extraTraces.add(first);
            while (extraTraces.size() > 10) {
                extraTraces.removeFirst();
            }
        }
    }

    public static List<String> getTraces() {
        LinkedList<String> list = new LinkedList<>();
        list.addAll(traces);
        LinkedList<String> extraTraces = extraTracesReference.get();
        if (extraTraces != null) {
            list.addAll(extraTraces);
        }
        return list;
    }

    private static boolean checkDebugMode() {
        ApplicationInfo applicationInfo = getContext().getApplicationInfo();
        return applicationInfo != null && (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
