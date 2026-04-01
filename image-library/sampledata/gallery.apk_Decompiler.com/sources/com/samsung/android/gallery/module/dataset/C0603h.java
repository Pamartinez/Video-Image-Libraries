package com.samsung.android.gallery.module.dataset;

import java.util.ArrayList;

/* renamed from: com.samsung.android.gallery.module.dataset.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0603h implements Runnable {
    public final /* synthetic */ MediaDataAlbum d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f2977h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f2978i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ int f2979j;
    public final /* synthetic */ long k;

    public /* synthetic */ C0603h(MediaDataAlbum mediaDataAlbum, ArrayList arrayList, ArrayList arrayList2, boolean z, boolean z3, int i2, int i7, long j2) {
        this.d = mediaDataAlbum;
        this.e = arrayList;
        this.f = arrayList2;
        this.g = z;
        this.f2977h = z3;
        this.f2978i = i2;
        this.f2979j = i7;
        this.k = j2;
    }

    public final void run() {
        this.d.lambda$processFullLoading$2(this.e, this.f, this.g, this.f2977h, this.f2978i, this.f2979j, this.k);
    }
}
