package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* renamed from: com.samsung.android.gallery.module.publisher.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0645z implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ListDataPublisher e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ long g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f3075h;

    public /* synthetic */ C0645z(ListDataPublisher listDataPublisher, Cursor[] cursorArr, long j2, String str, int i2) {
        this.d = i2;
        this.e = listDataPublisher;
        this.f = cursorArr;
        this.g = j2;
        this.f3075h = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishPrivateTrashData$19(this.f, this.g, this.f3075h);
                return;
            default:
                this.e.lambda$publishPrivateAlbumData$16(this.f, this.g, this.f3075h);
                return;
        }
    }
}
