package com.linsh.lshutils.tools;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.os.PowerManager;

import com.linsh.utilseverywhere.ContextUtils;

import androidx.annotation.RequiresPermission;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/02
 *    desc   :
 * </pre>
 */
public class PowerHelperEx {

    private PowerManager.WakeLock mWakeLock;

    @Deprecated
    @RequiresPermission(Manifest.permission.WAKE_LOCK)
    public void keepScreenOn() {
        PowerManager powerManager = (PowerManager) getContext().getSystemService(Service.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, getClass().getSimpleName());
        mWakeLock.setReferenceCounted(false);
        mWakeLock.acquire();
    }

    @RequiresPermission(Manifest.permission.WAKE_LOCK)
    public void keepScreenOn(long timeOut) {
        PowerManager powerManager = (PowerManager) getContext().getSystemService(Service.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, getClass().getSimpleName());
        mWakeLock.setReferenceCounted(false);
        mWakeLock.acquire(timeOut);
    }

    public void turnScreenOff() {
        if (mWakeLock != null) {
            mWakeLock.release();
            mWakeLock = null;
        }
    }

    private static Context getContext() {
        return ContextUtils.get();
    }
}
