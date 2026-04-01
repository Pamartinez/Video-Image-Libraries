package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListDataPublisher e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ long g;

    public /* synthetic */ B(ListDataPublisher listDataPublisher, Cursor[] cursorArr, long j2, int i2) {
        this.d = i2;
        this.e = listDataPublisher;
        this.f = cursorArr;
        this.g = j2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishTrashData$12(this.f, this.g);
                return;
            default:
                this.e.lambda$publishMtpData$9(this.f, this.g);
                return;
        }
    }
}
