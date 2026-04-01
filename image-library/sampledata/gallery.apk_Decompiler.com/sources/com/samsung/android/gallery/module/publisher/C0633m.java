package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* renamed from: com.samsung.android.gallery.module.publisher.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0633m implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumDataPublisher e;
    public final /* synthetic */ QueryParams f;
    public final /* synthetic */ Cursor[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ long f3069h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f3070i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Bundle f3071j;
    public final /* synthetic */ boolean k;

    public /* synthetic */ C0633m(AlbumDataPublisher albumDataPublisher, QueryParams queryParams, Cursor[] cursorArr, long j2, Object obj, Bundle bundle, boolean z, int i2) {
        this.d = i2;
        this.e = albumDataPublisher;
        this.f = queryParams;
        this.g = cursorArr;
        this.f3069h = j2;
        this.f3070i = obj;
        this.f3071j = bundle;
        this.k = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishAlbumFileData$7(this.f, this.g, this.f3069h, this.f3070i, this.f3071j, this.k);
                return;
            default:
                this.e.lambda$publishAlbumFileData$11(this.f, this.g, this.f3069h, this.f3070i, this.f3071j, this.k);
                return;
        }
    }
}
