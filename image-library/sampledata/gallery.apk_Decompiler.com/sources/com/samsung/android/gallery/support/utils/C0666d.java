package com.samsung.android.gallery.support.utils;

import android.app.Activity;
import com.samsung.android.gallery.support.library.SeApiCompat;

/* renamed from: com.samsung.android.gallery.support.utils.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0666d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Activity e;

    public /* synthetic */ C0666d(Activity activity, int i2) {
        this.d = i2;
        this.e = activity;
    }

    public final void run() {
        int i2 = this.d;
        Activity activity = this.e;
        switch (i2) {
            case 0:
                SimpleThreadPool.getInstance().execute(new C0666d(activity, 1));
                return;
            default:
                SeApiCompat.setAutoBrightnessLimit(activity, -1, -1);
                return;
        }
    }
}
