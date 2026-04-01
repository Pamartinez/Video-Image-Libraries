package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.MediaDataRemasterV2;

/* renamed from: com.samsung.android.gallery.module.dataset.b0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0593b0 implements Runnable {
    public final /* synthetic */ MediaDataRemasterV2 d;
    public final /* synthetic */ MediaDataRemasterV2.DataInfo e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Cursor[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Cursor[] f2967h;

    public /* synthetic */ C0593b0(MediaDataRemasterV2 mediaDataRemasterV2, MediaDataRemasterV2.DataInfo dataInfo, boolean z, Cursor[] cursorArr, Cursor[] cursorArr2) {
        this.d = mediaDataRemasterV2;
        this.e = dataInfo;
        this.f = z;
        this.g = cursorArr;
        this.f2967h = cursorArr2;
    }

    public final void run() {
        this.d.lambda$swap$2(this.e, this.f, this.g, this.f2967h);
    }
}
