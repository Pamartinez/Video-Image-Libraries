package com.samsung.android.gallery.module.service.abstraction;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.gallery.support.utils.TimeUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseService extends Service {
    /* access modifiers changed from: protected */
    public final String TAG = getClass().getSimpleName();
    protected long mStartTime;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        Log.i(this.TAG, "onCreate");
        this.mStartTime = System.currentTimeMillis();
        ServiceManager.getInstance().add(this);
    }

    public void onDestroy() {
        String str = this.TAG;
        Log.i(str, "onDestroy" + Logger.vt(TimeUtil.getIsoLocalDateTime(this.mStartTime), Long.valueOf(this.mStartTime)));
        ServiceManager.getInstance().remove(this);
    }

    public void onTimeout(int i2, int i7) {
        String str = this.TAG;
        Log.majorEvent(str, "onTimeout" + Logger.v(Integer.valueOf(i2), Integer.valueOf(i7)));
        ServiceManager.getInstance().timeout(this);
    }
}
