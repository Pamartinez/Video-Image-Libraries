package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* renamed from: com.samsung.android.gallery.module.publisher.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0628h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;

    public /* synthetic */ C0628h(Cursor[] cursorArr, int i2) {
        this.d = i2;
        this.e = cursorArr;
    }

    public final void run() {
        int i2 = this.d;
        Cursor[] cursorArr = this.e;
        switch (i2) {
            case 0:
                AlbumDataPublisher.lambda$publishAlbumsData$21(cursorArr);
                return;
            case 1:
                ListDataPublisher.lambda$publishPrivateTrashData$17(cursorArr);
                return;
            case 2:
                ListDataPublisher.lambda$publishPrivateTrashData$18(cursorArr);
                return;
            case 3:
                ListDataPublisher.lambda$publishPrivateAlbumData$13(cursorArr);
                return;
            case 4:
                ListDataPublisher.lambda$publishPrivateAlbumData$14(cursorArr);
                return;
            default:
                ListDataPublisher.lambda$publishPrivateAlbumData$15(cursorArr);
                return;
        }
    }
}
