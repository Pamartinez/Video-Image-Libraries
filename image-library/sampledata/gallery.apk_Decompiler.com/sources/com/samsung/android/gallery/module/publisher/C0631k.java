package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* renamed from: com.samsung.android.gallery.module.publisher.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0631k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumDataPublisher e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ QueryParams g;

    public /* synthetic */ C0631k(AlbumDataPublisher albumDataPublisher, Cursor[] cursorArr, QueryParams queryParams, int i2) {
        this.d = i2;
        this.e = albumDataPublisher;
        this.f = cursorArr;
        this.g = queryParams;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishAlbumFileData$12(this.f, this.g);
                return;
            default:
                this.e.lambda$publishAlbumFileData$9(this.f, this.g);
                return;
        }
    }
}
