package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Q implements Runnable {
    public final /* synthetic */ SearchDataPublisher d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Cursor[] f3054h;

    public /* synthetic */ Q(SearchDataPublisher searchDataPublisher, String str, String str2, String str3, Cursor[] cursorArr) {
        this.d = searchDataPublisher;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.f3054h = cursorArr;
    }

    public final void run() {
        this.d.lambda$publishScreenShotFiles$17(this.e, this.f, this.g, this.f3054h);
    }
}
