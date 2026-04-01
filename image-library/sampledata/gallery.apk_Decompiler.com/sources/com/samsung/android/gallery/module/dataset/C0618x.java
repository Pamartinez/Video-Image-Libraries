package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.cache.MemoryCacheMgr;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;

/* renamed from: com.samsung.android.gallery.module.dataset.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0618x implements Runnable {
    public final /* synthetic */ MediaDataCursor d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ MemoryCacheMgr g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ ClusterTable[] f3000h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ long f3001i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ MemoryCacheMgr f3002j;

    public /* synthetic */ C0618x(MediaDataCursor mediaDataCursor, Cursor[] cursorArr, Cursor[] cursorArr2, MemoryCacheMgr memoryCacheMgr, ClusterTable[] clusterTableArr, long j2, MemoryCacheMgr memoryCacheMgr2) {
        this.d = mediaDataCursor;
        this.e = cursorArr;
        this.f = cursorArr2;
        this.g = memoryCacheMgr;
        this.f3000h = clusterTableArr;
        this.f3001i = j2;
        this.f3002j = memoryCacheMgr2;
    }

    public final void run() {
        this.d.lambda$swap$1(this.e, this.f, this.g, this.f3000h, this.f3001i, this.f3002j);
    }
}
