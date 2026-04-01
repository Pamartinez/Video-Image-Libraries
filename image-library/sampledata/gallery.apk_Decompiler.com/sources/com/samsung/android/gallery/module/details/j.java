package com.samsung.android.gallery.module.details;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ EditDetailsUpdater2 d;
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f3011h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ boolean f3012i;

    public /* synthetic */ j(EditDetailsUpdater2 editDetailsUpdater2, Runnable runnable, boolean z, boolean z3, boolean z7, boolean z9) {
        this.d = editDetailsUpdater2;
        this.e = runnable;
        this.f = z;
        this.g = z3;
        this.f3011h = z7;
        this.f3012i = z9;
    }

    public final void run() {
        this.d.lambda$onSave$1(this.e, this.f, this.g, this.f3011h, this.f3012i);
    }
}
