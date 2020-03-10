package com.linsh.lshutils.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.linsh.utilseverywhere.ContextUtils;
import com.linsh.utilseverywhere.IntentUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/03/09
 *    desc   :
 * </pre>
 */
public class IntentUtilsEx {

    private IntentUtilsEx() {
    }

    private static Context getContext() {
        return ContextUtils.get();
    }

    /**
     * 跳转: 「权限设置」界面
     * <p>
     * 根据各大厂商的不同定制而跳转至其权限设置
     * 目前已测试成功机型: 小米V7V8V9, 华为, 三星, 锤子, 魅族; 测试失败: OPPO
     *
     * @return 成功跳转权限设置, 返回 true; 没有适配该厂商或不能跳转, 则自动默认跳转设置界面, 并返回 false
     */
    @Deprecated
    public static boolean gotoPermissionSetting() {
        boolean success = true;
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String packageName = getContext().getPackageName();

        OSUtilsEx.ROM romType = OSUtilsEx.getRomType();
        switch (romType) {
            case EMUI: // 华为
                intent.putExtra("packageName", packageName);
                intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
                break;
            case Flyme: // 魅族
                intent.setAction("com.meizu.safe.security.SHOW_APPSEC");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("packageName", packageName);
                break;
            case MIUI: // 小米
                String rom = getMiuiVersion();
                if ("V6".equals(rom) || "V7".equals(rom)) {
                    intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                    intent.putExtra("extra_pkgname", packageName);
                } else if ("V8".equals(rom) || "V9".equals(rom)) {
                    intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                    intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                    intent.putExtra("extra_pkgname", packageName);
                } else {
                    intent = IntentUtils.getAppDetailsSettingsIntent(packageName);
                }
                break;
            case Sony: // 索尼
                intent.putExtra("packageName", packageName);
                intent.setComponent(new ComponentName("com.sonymobile.cta", "com.sonymobile.cta.SomcCTAMainActivity"));
                break;
            case ColorOS: // OPPO
                intent.putExtra("packageName", packageName);
                intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.PermissionManagerActivity"));
                break;
            case EUI: // 乐视
                intent.putExtra("packageName", packageName);
                intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps"));
                break;
            case LG: // LG
                intent.setAction("android.intent.action.MAIN");
                intent.putExtra("packageName", packageName);
                ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
                intent.setComponent(comp);
                break;
            case SamSung: // 三星
            case SmartisanOS: // 锤子
                IntentUtils.gotoAppDetailSetting(packageName);
                break;
            default:
                intent.setAction(Settings.ACTION_SETTINGS);
                Log.i(IntentUtils.class.getSimpleName(), "没有适配该机型, 跳转普通设置界面");
                success = false;
                break;
        }
        try {
            getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            // 跳转失败, 前往普通设置界面
            IntentUtils.gotoSetting();
            success = false;
            Log.i(IntentUtils.class.getSimpleName(), "无法跳转权限界面, 开始跳转普通设置界面");
        }
        return success;
    }

    /**
     * 获取 MIUI 版本号
     */
    private static String getMiuiVersion() {
        String propName = "ro.miui.ui.version.name";
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(
                    new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i(IntentUtils.class.getSimpleName(), "MiuiVersion = " + line);
        return line;
    }
}
