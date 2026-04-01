package com.samsung.android.gallery.module.dataset;

import java.util.ArrayList;

/* renamed from: com.samsung.android.gallery.module.dataset.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0602g implements Runnable {
    public final /* synthetic */ MediaDataAlbum d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ long f2974h;

    public /* synthetic */ C0602g(MediaDataAlbum mediaDataAlbum, ArrayList arrayList, ArrayList arrayList2, boolean z, long j2) {
        this.d = mediaDataAlbum;
        this.e = arrayList;
        this.f = arrayList2;
        this.g = z;
        this.f2974h = j2;
    }

    public final void run() {
        this.d.lambda$processCacheLoading$0(this.e, this.f, this.g, this.f2974h);
    }
}
