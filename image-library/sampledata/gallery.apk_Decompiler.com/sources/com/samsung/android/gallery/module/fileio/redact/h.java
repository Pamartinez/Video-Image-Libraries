package com.samsung.android.gallery.module.fileio.redact;

import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ FileEditor d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ int f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Boolean f3023h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Runnable f3024i;

    public /* synthetic */ h(FileEditor fileEditor, ArrayList arrayList, int i2, boolean z, Boolean bool, Runnable runnable) {
        this.d = fileEditor;
        this.e = arrayList;
        this.f = i2;
        this.g = z;
        this.f3023h = bool;
        this.f3024i = runnable;
    }

    public final void run() {
        this.d.lambda$runGroupItems$4(this.e, this.f, this.g, this.f3023h, this.f3024i);
    }
}
