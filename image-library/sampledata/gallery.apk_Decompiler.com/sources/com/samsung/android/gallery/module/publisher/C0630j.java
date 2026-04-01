package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* renamed from: com.samsung.android.gallery.module.publisher.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0630j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    public /* synthetic */ C0630j(Cursor[] cursorArr, int i2, int i7, int i8) {
        this.d = i8;
        this.e = cursorArr;
        this.f = i2;
        this.g = i7;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                AlbumDataPublisher.lambda$publishAlbumFileData$2(this.e, this.f, this.g);
                return;
            default:
                AlbumDataPublisher.lambda$publishAlbumFileData$4(this.e, this.f, this.g);
                return;
        }
    }
}
