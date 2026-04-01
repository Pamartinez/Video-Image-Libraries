package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class M implements Runnable {
    public final /* synthetic */ MediaDataMdeSpace d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ ArrayList g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ HashMap f2953h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ HashMap f2954i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ HashMap f2955j;
    public final /* synthetic */ int k;
    public final /* synthetic */ boolean l;
    public final /* synthetic */ long m;

    public /* synthetic */ M(MediaDataMdeSpace mediaDataMdeSpace, Cursor[] cursorArr, Cursor[] cursorArr2, ArrayList arrayList, HashMap hashMap, HashMap hashMap2, HashMap hashMap3, int i2, boolean z, long j2) {
        this.d = mediaDataMdeSpace;
        this.e = cursorArr;
        this.f = cursorArr2;
        this.g = arrayList;
        this.f2953h = hashMap;
        this.f2954i = hashMap2;
        this.f2955j = hashMap3;
        this.k = i2;
        this.l = z;
        this.m = j2;
    }

    public final void run() {
        this.d.lambda$swap$3(this.e, this.f, this.g, this.f2953h, this.f2954i, this.f2955j, this.k, this.l, this.m);
    }
}
