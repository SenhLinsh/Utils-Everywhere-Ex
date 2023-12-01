package com.linsh.lshutils.utils;

import com.linsh.utilseverywhere.DateUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2023/12/01
 *    desc   :
 * </pre>
 */
public class DateUtilsEx {

    /**
     * 智能格式化时间
     * <p>
     * 支持的日期
     * 20200919
     * 202009191000
     * 20200919100000
     * 2020/09/19
     * 2020.09.19
     * 2020_09_19
     * 2020-09-19
     * 2020-09-19 10:00
     * 2020-09-19 10:00:00
     * 2020-09-19 10:00:00.000
     * 2020-09-19T10:00:00
     * 2020年9月19日10时0分0秒
     * <p>
     * 暂不支持：
     * 2020-09-19T10:00:00+08:00
     * 2020-09-19T10:00:00.000+08:00
     *
     * @param date 日期字符串
     */
    public static String smartFormat(String date) {
        if (date == null) return null;
        date = date.trim();
        if (date.length() == 0) return null;
        String formated = null;
        String[] args = date.replaceAll("[^0-9]+", " ").trim().split(" +");
        if (args.length == 1) {
            if (args[0].length() == 8) {
                formated = args[0].substring(0, 4) + "-" + args[0].substring(4, 6) + "-" + args[0].substring(6, 8);
            } else if (args[0].length() == 12) {
                formated = args[0].substring(0, 4) + "-" + args[0].substring(4, 6) + "-" + args[0].substring(6, 8)
                        + " " + args[0].substring(8, 10) + ":" + args[0].substring(10, 12);
            } else if (args[0].length() == 14) {
                formated = args[0].substring(0, 4) + "-" + args[0].substring(4, 6) + "-" + args[0].substring(6, 8)
                        + " " + args[0].substring(8, 10) + ":" + args[0].substring(10, 12) + ":" + args[0].substring(12, 14);
            }
        } else if (args.length == 3) {
            formated = fillY(args[0]) + "-" + fill2(args[1]) + "-" + fill2(args[2]);
        } else if (args.length == 4) {
            formated = fillY(args[0]) + "-" + fill2(args[1]) + "-" + fill2(args[2]) + " " + fill2(args[3]) + ":00";
        } else if (args.length == 5) {
            formated = fillY(args[0]) + "-" + fill2(args[1]) + "-" + fill2(args[2]) + " " + fill2(args[3]) + ":" + fill2(args[4]);
        } else if (args.length == 6) {
            formated = fillY(args[0]) + "-" + fill2(args[1]) + "-" + fill2(args[2]) + " " + fill2(args[3]) + ":" + fill2(args[4]) + ":" + fill2(args[5]);
        } else if (args.length == 7) {
            formated = fillY(args[0]) + "-" + fill2(args[1]) + "-" + fill2(args[2]) + " " + fill2(args[3]) + ":" + fill2(args[4]) + ":" + fill2(args[5]) + "." + fill3(args[6]);
        }
        return formated;
    }

    /**
     * 智能解析时间
     * <p>
     * 支持的日期
     * 20200919
     * 202009191000
     * 20200919100000
     * 2020/09/19
     * 2020.09.19
     * 2020_09_19
     * 2020-09-19
     * 2020-09-19 10:00
     * 2020-09-19 10:00:00
     * 2020-09-19 10:00:00.000
     * 2020-09-19T10:00:00
     * 2020年9月19日10时0分0秒
     * <p>
     * 暂不支持：
     * 2020-09-19T10:00:00+08:00
     * 2020-09-19T10:00:00.000+08:00
     *
     * @param date 日期字符串
     */
    public static long smartParse(String date) {
        String formated = smartFormat(date);
        System.out.println(formated);
        if (formated == null) return 0;
        if (formated.length() == 10)
            return DateUtils.parse(formated, "yyyy-MM-dd");
        else if (formated.length() == 16)
            return DateUtils.parse(formated, "yyyy-MM-dd HH:mm");
        else if (formated.length() == 19)
            return DateUtils.parse(formated, "yyyy-MM-dd HH:mm:ss");
        else if (formated.length() == 23)
            return DateUtils.parse(formated, "yyyy-MM-dd HH:mm:ss.SSS");
        return 0;
    }

    private static String fillY(String str) {
        return str.length() == 2 ? "20" + str : str;
    }

    private static String fill2(String str) {
        return str.length() == 1 ? "0" + str : str;
    }

    private static String fill3(String str) {
        return str.length() == 1 ? "00" + str : str.length() == 2 ? "0" + str : str;
    }
}
