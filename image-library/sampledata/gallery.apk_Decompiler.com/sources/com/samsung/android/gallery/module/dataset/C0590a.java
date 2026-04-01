package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.samsung.android.gallery.module.dataset.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0590a implements Runnable {
    public final /* synthetic */ AbstractMediaDataMde d;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ HashMap f;
    public final /* synthetic */ Cursor[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f2964h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f2965i;

    public /* synthetic */ C0590a(AbstractMediaDataMde abstractMediaDataMde, ArrayList arrayList, HashMap hashMap, Cursor[] cursorArr, boolean z, long j2) {
        this.d = abstractMediaDataMde;
        this.e = arrayList;
        this.f = hashMap;
        this.g = cursorArr;
        this.f2964h = z;
        this.f2965i = j2;
    }

    public final void run() {
        this.d.lambda$swap$0(this.e, this.f, this.g, this.f2964h, this.f2965i);
    }
}
