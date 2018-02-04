package com.linsh.lshutils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import com.linsh.utilseverywhere.PermissionUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    date   : 2018/02/05
 *    desc   :
 * </pre>
 */
public class Permission {

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

        public static boolean check(Activity activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(activity)) {
                return false;
            }
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        public static void request(Activity activity, @IntRange(from = 0) int requestCode) {
            Permission.requestPermission(activity, NAME, requestCode);
        }

        public static boolean checkOrRequest(Activity activity, @IntRange(from = 0) int requestCode) {
            boolean check = check(activity);
            if (!check && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                request(activity, requestCode);
            }
            return check;
        }

        public static boolean checkResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
            return Permission.checkResult(NAME, permissions, grantResults);
        }

        public static boolean isNeverAsked(Activity activity) {
            return Permission.isNeverAsked(activity, NAME);
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
