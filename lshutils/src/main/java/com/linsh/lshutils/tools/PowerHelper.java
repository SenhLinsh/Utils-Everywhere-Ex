package com.linsh.lshutils.tools;

import android.app.Service;
import android.os.PowerManager;

import com.linsh.utilseverywhere.ContextUtils;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/02/02
 *    desc   :
 * </pre>
 */
public class PowerHelper {

    private PowerManager.WakeLock mWakeLock;

    public void keepScreenOn() {
        PowerManager powerManager = (PowerManager) ContextUtils.getSystemService(Service.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        mWakeLock.setReferenceCounted(false);
        mWakeLock.acquire();
    }

    public void keepScreenOn(long timeOut) {
        PowerManager powerManager = (PowerManager) ContextUtils.getSystemService(Service.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
        mWakeLock.setReferenceCounted(false);
        mWakeLock.acquire(timeOut);
    }

    public void turnScreenOff() {
        if (mWakeLock != null) {
            mWakeLock.release();
            mWakeLock = null;
        }
    }
}
