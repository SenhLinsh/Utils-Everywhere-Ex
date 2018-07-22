package com.linsh.lshutils.utils;

import android.content.Context;
import android.os.Environment;

import com.linsh.utilseverywhere.ContextUtils;
import com.linsh.utilseverywhere.DateUtils;
import com.linsh.utilseverywhere.FileUtils;
import com.linsh.utilseverywhere.LogUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2017/11/28
 *    desc   : 工具类: 打印日志到本地相关
 *
 *             目前还不完善, 没有开子线程加上频繁的 IO 调用对系统的损耗较多
 * </pre>
 */
public class LogPrinterUtils {

    // 保存本地log文件的目录
    private static String sLogFilePath;

    static {
        sLogFilePath = Environment.getExternalStorageDirectory() + "/" + getContext().getPackageName() + "/LshLog.txt";
    }

    private LogPrinterUtils() {
    }

    private static Context getContext() {
        return ContextUtils.get();
    }

    public static void setLogFilePath(File filePath) {
        setLogFilePath(filePath.toString());
    }

    public static void setLogFilePath(String filePath) {
        sLogFilePath = filePath;
    }

    public static void i(String msg) {
        LogUtils.i(msg);
        print(msg);
    }

    public static void i(List<String> msgs) {
        if (LogUtils.isDebugMode()) {
            for (String msg : msgs) {
                LogUtils.i(msg);
            }
        }
        print(msgs);
    }

    public static void e(Throwable thr) {
        e(null, thr);
    }

    public static void e(String msg, Throwable thr) {
        msg = msg == null ? "msg = null" : msg;
        if (LogUtils.isDebugMode()) {
            LogUtils.e(msg, thr);
        }
        List<String> logs = new ArrayList<>();
        logs.add("  ---------------------Throw an ERROR----------------  ");
        if (thr == null) {
            logs.add(msg);
        } else {
            logs.add(msg + " (##" + getClassName() + "##" + callMethodAndLine() + ")");
            logs.add("Message = " + thr.getMessage());
            StackTraceElement[] stackTrace = thr.getStackTrace();
            for (StackTraceElement stack : stackTrace) {
                logs.add(stack.toString());
            }
            logs.add("\r\n");
        }
        print(logs);
    }

    private static void print(String... logs) {
        print(Arrays.asList(logs));
    }

    private static void print(List<String> logs) {
        if (!FileUtils.checkPermission()) return;

        BufferedWriter bw = null;
        try {
            File file = new File(sLogFilePath);
            if (file.exists()) {
                // 超过一个星期没有修改, 直接删除
                if (System.currentTimeMillis() - file.lastModified() > 1000L * 60 * 60 * 24 * 7) {
                    file.delete();
                }
            } else if (!FileUtils.makeParentDirs(file)) {
                return;
            }

            String curTime = "[" + DateUtils.getCurDateTimeStr() + "] ";
            bw = new BufferedWriter(new FileWriter(file, true));
            for (String msg : logs) {
                bw.append(curTime).append(msg);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取调用 Log 方法的类名
     */
    private static String getClassName() {
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        String result = thisMethodStack.getClassName();
        String[] split = result.split("\\.");
        try {
            result = split[split.length - 1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取调用 Log 方法的方法和行数
     */
    private static String callMethodAndLine() {
        String result = "at ";
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result += thisMethodStack.getClassName() + "";
        result += thisMethodStack.getMethodName();
        result += "(" + thisMethodStack.getFileName();
        result += ":" + thisMethodStack.getLineNumber() + ")  ";
        return result;
    }
}
