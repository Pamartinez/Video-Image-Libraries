package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ String f;

    public /* synthetic */ a0(Cursor[] cursorArr, String str, int i2) {
        this.d = i2;
        this.e = cursorArr;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                SearchDataPublisherV2.lambda$publishPeopleSelectForRelation$20(this.e, this.f);
                return;
            case 1:
                VirtualAlbumDataPublisher.lambda$publishActionAlbumViewPicturesData$12(this.e, this.f);
                return;
            case 2:
                VirtualAlbumDataPublisher.lambda$publishActionAlbumViewPicturesData$14(this.e, this.f);
                return;
            default:
                VirtualAlbumDataPublisher.lambda$publishActionAlbumViewPicturesData$16(this.e, this.f);
                return;
        }
    }
}
