package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.utils.Timer;

/* renamed from: com.samsung.android.gallery.support.utils.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0682u implements Timer.OnTimer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ InputBlock f3188a;

    public /* synthetic */ C0682u(InputBlock inputBlock) {
        this.f3188a = inputBlock;
    }

    public final void onTimer(int i2) {
        this.f3188a.release(i2);
    }
}
