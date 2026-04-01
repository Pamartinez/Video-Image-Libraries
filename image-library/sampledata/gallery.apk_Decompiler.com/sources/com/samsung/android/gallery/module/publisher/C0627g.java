package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* renamed from: com.samsung.android.gallery.module.publisher.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0627g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ long f;
    public final /* synthetic */ CursorPublisher g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f3067h;

    public /* synthetic */ C0627g(AlbumDataPublisher albumDataPublisher, Cursor[] cursorArr, QueryParams queryParams, long j2) {
        this.d = 0;
        this.g = albumDataPublisher;
        this.e = cursorArr;
        this.f3067h = queryParams;
        this.f = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                long j2 = this.f;
                ((AlbumDataPublisher) this.g).lambda$publishAlbumsData$18(this.e, (QueryParams) this.f3067h, j2);
                return;
            case 1:
                ((SearchDataPublisher) this.g).lambda$publishScreenShotFiles$19(this.e, this.f, (Bundle) this.f3067h);
                return;
            default:
                ((VirtualAlbumDataPublisher) this.g).lambda$publishActionAlbumViewPicturesData$17(this.e, this.f, (String) this.f3067h);
                return;
        }
    }

    public /* synthetic */ C0627g(CursorPublisher cursorPublisher, Cursor[] cursorArr, long j2, Object obj, int i2) {
        this.d = i2;
        this.g = cursorPublisher;
        this.e = cursorArr;
        this.f = j2;
        this.f3067h = obj;
    }
}
