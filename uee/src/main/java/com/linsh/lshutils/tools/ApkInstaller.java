package com.linsh.lshutils.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

import com.linsh.utilseverywhere.ContextUtils;
import com.linsh.utilseverywhere.IntentUtils;

import java.io.File;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2020/06/06
 *    desc   : 安装 apk
 * </pre>
 */
public class ApkInstaller {

    private final File apkFile;

    public ApkInstaller(File apkFile) {
        if (apkFile == null)
            throw new IllegalArgumentException("apkFile == null");
        if (apkFile.isDirectory())
            throw new IllegalArgumentException("apkFile is directory");
        this.apkFile = apkFile;
    }

    /**
     * 判断是否可以安装未知来源应用的权限
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean canRequestPackageInstalls() {
        return ContextUtils.getPackageManager().canRequestPackageInstalls();
    }

    /**
     * 跳转打开安装未知来源应用权限的页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void gotoPermissionPage() {
        Uri uri = Uri.parse("package:" + ContextUtils.getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ContextUtils.startActivity(intent);
    }

    /**
     * 安装 APK
     */
    public void install() {
        Intent intent = IntentUtils.getInstallAppIntent(apkFile);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ContextUtils.startActivity(intent);
    }

    /**
     * 安装 APK
     */
    public void installForResult(Activity activity) {
        Intent intent = IntentUtils.getInstallAppIntent(apkFile);
        ContextUtils.startActivity(intent);
    }
}
