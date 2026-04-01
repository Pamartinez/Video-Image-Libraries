package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesV5;
import com.samsung.android.gallery.support.utils.BufferedArray;

/* renamed from: com.samsung.android.gallery.module.dataset.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0610o implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ MediaDataRef f2987h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2988i;

    public /* synthetic */ C0610o(MediaDataCollection mediaDataCollection, BufferedArray bufferedArray, Cursor[] cursorArr, int i2, Cursor[] cursorArr2) {
        this.f2987h = mediaDataCollection;
        this.f2988i = bufferedArray;
        this.e = cursorArr;
        this.g = i2;
        this.f = cursorArr2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaDataCollection) this.f2987h).lambda$swap$1((BufferedArray) this.f2988i, this.e, this.g, this.f);
                return;
            default:
                int i2 = this.g;
                ((MediaDataStoriesV5) this.f2987h).lambda$swap$2(this.e, this.f, (MediaDataStoriesV5.DataInfo) this.f2988i, i2);
                return;
        }
    }

    public /* synthetic */ C0610o(MediaDataStoriesV5 mediaDataStoriesV5, Cursor[] cursorArr, Cursor[] cursorArr2, MediaDataStoriesV5.DataInfo dataInfo, int i2) {
        this.f2987h = mediaDataStoriesV5;
        this.e = cursorArr;
        this.f = cursorArr2;
        this.f2988i = dataInfo;
        this.g = i2;
    }
}
