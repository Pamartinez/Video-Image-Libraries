package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class L implements Consumer {
    public final /* synthetic */ MediaDataMdeSpace d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ HashMap f;
    public final /* synthetic */ Cursor[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Cursor[] f2950h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ HashMap f2951i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ HashMap f2952j;
    public final /* synthetic */ int k;
    public final /* synthetic */ long l;

    public /* synthetic */ L(MediaDataMdeSpace mediaDataMdeSpace, ArrayList arrayList, HashMap hashMap, Cursor[] cursorArr, Cursor[] cursorArr2, HashMap hashMap2, HashMap hashMap3, int i2, long j2) {
        this.d = mediaDataMdeSpace;
        this.e = arrayList;
        this.f = hashMap;
        this.g = cursorArr;
        this.f2950h = cursorArr2;
        this.f2951i = hashMap2;
        this.f2952j = hashMap3;
        this.k = i2;
        this.l = j2;
    }

    public final void accept(Object obj) {
        this.d.lambda$swap$4(this.e, this.f, this.g, this.f2950h, this.f2951i, this.f2952j, this.k, this.l, (Boolean) obj);
    }
}
