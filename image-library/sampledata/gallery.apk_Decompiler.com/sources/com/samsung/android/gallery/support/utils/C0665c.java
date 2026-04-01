package com.samsung.android.gallery.support.utils;

import android.app.Activity;

/* renamed from: com.samsung.android.gallery.support.utils.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0665c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Activity e;
    public final /* synthetic */ int f;

    public /* synthetic */ C0665c(Activity activity, int i2, int i7) {
        this.d = i7;
        this.e = activity;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                SimpleThreadPool.getInstance().execute(new C0665c(this.e, this.f, 1));
                return;
            default:
                BrightnessModeHelper.lambda$setBrightnessLowerBoundLimit$0(this.e, this.f);
                return;
        }
    }
}
