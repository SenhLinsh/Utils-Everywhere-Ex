package com.linsh.lshutils.helper.act;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;

import com.linsh.utilseverywhere.PermissionUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    date   : 2018/02/04
 *    desc   :
 * </pre>
 */
public class PermissionHelper extends ActivityHelper {

    private static int sRequestCode = 666;
    private SparseArray<PermissionListener> listeners;
    private Activity mActivity;

    public PermissionHelper(Activity activity) {
        mActivity = activity;
    }

    public void checkAndRequestPermission(@NonNull String permission, @NonNull PermissionListener listener) {
        if (PermissionUtils.checkPermission(permission)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                listener.onGranted();
            } else {
                listener.onBeforeAndroidM();
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int requestCode = sRequestCode++;
                listeners.put(requestCode, listener);
                listener.permission = permission;
                ActivityCompat.requestPermissions(mActivity, new String[]{permission}, requestCode);
            } else {
                listener.onDenied(true);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionListener listener = listeners.get(requestCode);
        if (listener != null) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                if (permission.equals(listener.permission)) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        listener.onGranted();
                    } else if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission)) {
                        listener.onDenied(false);
                    } else {
                        listener.onDenied(true);
                    }
                    return;
                }
            }
            listeners.remove(requestCode);
        }
    }

    public abstract static class PermissionListener {

        String permission;

        public abstract void onGranted();

        public abstract void onDenied(boolean isNeverAsked);

        /**
         * 部分权限在 Android M (6.0) 以下时无法得知是否拥有该权限, 需要实际运行才可得知
         */
        public abstract void onBeforeAndroidM();
    }

    public abstract static class PermissionListener2 extends PermissionListener {

        public PermissionListener2() {
            permission = getPermission();
        }

        protected abstract String getPermission();
    }

    public abstract static class StoragePermissionListener extends PermissionListener2 {

        @Override
        protected String getPermission() {
            return Manifest.permission.WRITE_EXTERNAL_STORAGE;
        }

        @Override
        public void onBeforeAndroidM() {
            onGranted();
        }
    }

    public abstract static class SystemAlertWindowPermissionListener extends PermissionListener2 {

        @Override
        protected String getPermission() {
            return Manifest.permission.SYSTEM_ALERT_WINDOW;
        }

        @Override
        public void onBeforeAndroidM() {
            // TODO: 18/2/5
        }
    }

}
