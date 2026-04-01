package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ VirtualAlbumDataPublisher e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ long g;

    public /* synthetic */ g0(VirtualAlbumDataPublisher virtualAlbumDataPublisher, Cursor[] cursorArr, long j2, int i2) {
        this.d = i2;
        this.e = virtualAlbumDataPublisher;
        this.f = cursorArr;
        this.g = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishRepairData$9(this.f, this.g);
                return;
            case 1:
                this.e.lambda$publishVideoData$2(this.f, this.g);
                return;
            default:
                this.e.lambda$publishRepairData$5(this.f, this.g);
                return;
        }
    }
}
