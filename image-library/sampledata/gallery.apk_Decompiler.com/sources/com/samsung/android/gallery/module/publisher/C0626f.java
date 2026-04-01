package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* renamed from: com.samsung.android.gallery.module.publisher.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0626f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumDataPublisher e;
    public final /* synthetic */ Cursor[] f;

    public /* synthetic */ C0626f(AlbumDataPublisher albumDataPublisher, Cursor[] cursorArr, int i2) {
        this.d = i2;
        this.e = albumDataPublisher;
        this.f = cursorArr;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishAlbumsData$17(this.f);
                return;
            case 1:
                this.e.lambda$publishAlbumHideData$24(this.f);
                return;
            default:
                this.e.lambda$publishAlbumHideData$25(this.f);
                return;
        }
    }
}
