package com.samsung.android.gallery.module.details;

import com.samsung.android.gallery.module.fileio.redact.OnProgress;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements OnProgress {
    public final /* synthetic */ EditDetailsUpdater2 d;
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f3009h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ boolean f3010i;

    public /* synthetic */ i(EditDetailsUpdater2 editDetailsUpdater2, Runnable runnable, boolean z, boolean z3, boolean z7, boolean z9) {
        this.d = editDetailsUpdater2;
        this.e = runnable;
        this.f = z;
        this.g = z3;
        this.f3009h = z7;
        this.f3010i = z9;
    }

    public final void onCompleted(int i2, int i7, int i8) {
        this.d.lambda$onSave$2(this.e, this.f, this.g, this.f3009h, this.f3010i, i2, i7, i8);
    }
}
