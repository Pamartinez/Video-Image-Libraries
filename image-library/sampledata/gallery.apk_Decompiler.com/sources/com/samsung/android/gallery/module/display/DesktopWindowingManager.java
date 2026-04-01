package com.samsung.android.gallery.module.display;

import android.content.Context;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DesktopWindowingManager {
    private boolean mIsDexMode;
    private Runnable mMainScreenChangedListener;

    public void configChanged(Context context) {
        Runnable runnable;
        DeviceInfo.clearDexMode();
        boolean isDexMode = DeviceInfo.isDexMode(context);
        Log.d("DesktopWindowingManager", "desktopWindowingState", Boolean.valueOf(isDexMode));
        if (!(this.mIsDexMode == isDexMode || (runnable = this.mMainScreenChangedListener) == null)) {
            runnable.run();
        }
        this.mIsDexMode = isDexMode;
    }

    public void init(Context context, Runnable runnable) {
        this.mIsDexMode = DeviceInfo.isDexMode(context);
        this.mMainScreenChangedListener = runnable;
    }

    public void release() {
        this.mMainScreenChangedListener = null;
    }
}
