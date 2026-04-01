package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class X implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchDataPublisher e;
    public final /* synthetic */ Cursor[] f;

    public /* synthetic */ X(SearchDataPublisher searchDataPublisher, Cursor[] cursorArr, int i2) {
        this.d = i2;
        this.e = searchDataPublisher;
        this.f = cursorArr;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishSearchAutoCompleteV1$15(this.f);
                return;
            default:
                this.e.lambda$publishSearchAutoCompleteV1$16(this.f);
                return;
        }
    }
}
