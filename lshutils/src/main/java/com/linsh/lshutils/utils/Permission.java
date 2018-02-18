package com.linsh.lshutils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.linsh.utilseverywhere.IntentUtils;
import com.linsh.utilseverywhere.PermissionUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    date   : 2018/02/05
 *    desc   :
 * </pre>
 */
public class Permission {

    public static int REQUEST_CODE = 101;

    /*
    public static class Test {

        public static final String NAME = Manifest.permission.TEST;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public static boolean check() {
            return PermissionUtils.checkPermission(NAME);
        }

        public static boolean check() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return PermissionUtils.checkPermission(NAME);
            }
            return true;
        }

        public static boolean checkBeforeAndroidM() {
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public static void request(Activity activity, @IntRange(from = 0) int requestCode) {
            Permission.requestPermission(activity, NAME, requestCode);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public static void checkOrRequest(Activity activity, @IntRange(from = 0) int requestCode) {
            boolean check = check();
            if (!check) {
                request(activity, requestCode);
            }
        }

        public static boolean checkResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
            return Permission.checkResult(NAME, permissions, grantResults);
        }

        public static boolean isNeverAsked(Activity activity) {
            return Permission.isNeverAsked(activity, NAME);
        }
    }
    */


    public static class Camera {

        public static final String NAME = Manifest.permission.CAMERA;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public static boolean check() {
            return PermissionUtils.checkPermission(NAME);
        }

        /**
         * @return 返回 false 不一定是没有权限, 也有一定的可能是摄像头被占用的情况(可能性较低)
         */
        public static boolean checkBeforeAndroidM() {
            android.hardware.Camera cam = null;
            try {
                cam = android.hardware.Camera.open();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (cam != null) {
                        cam.release();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }

        public static void request(Activity activity, @IntRange(from = 0) int requestCode) {
            Permission.requestPermission(activity, NAME, requestCode);
        }

        public static boolean checkResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
            for (int i = 0; i < permissions.length; i++) {
                if (NAME.equals(permissions[i])) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }

        public static boolean isNeverAsked(Activity activity) {
            return Permission.isNeverAsked(activity, NAME);
        }
    }

    public static class SystemAlertWindow {

        public static final String NAME = Manifest.permission.SYSTEM_ALERT_WINDOW;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public static boolean check(Activity activity) {
            return Settings.canDrawOverlays(activity);
        }

        public static void gotoPermissionSetting(Activity activity) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(intent, REQUEST_CODE);
            } else {
                IntentUtils.gotoPermissionSetting();
            }
        }

        /**
         * 悬浮窗权限默认是关闭的, 常规的请求无法弹出请求窗口, 需要自己动手打开 (目前发现小米系统是这样的)
         *
         * @param activity   Activity
         * @param requestMsg 没有权限时, 自动跳转权限设置界面的提示语
         * @return
         */
        @RequiresApi(api = Build.VERSION_CODES.M)
        public static boolean checkOrRequest(Activity activity, String requestMsg) {
            boolean check = check(activity);
            if (!check && requestMsg != null && requestMsg.length() > 0) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(intent, REQUEST_CODE);
                Toast.makeText(activity, requestMsg, Toast.LENGTH_SHORT).show();
            }
            return check;
        }
    }

    //================================================ 方法 ================================================//

    private static void requestPermission(Activity activity, String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    public static boolean checkResult(@NonNull String name, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            if (name.equals(permissions[i])) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isNeverAsked(Activity activity, String name) {
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, name);
    }
}
