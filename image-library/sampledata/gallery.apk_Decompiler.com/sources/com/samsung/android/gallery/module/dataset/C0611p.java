package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.BufferedArray;
import java.util.function.BooleanSupplier;

/* renamed from: com.samsung.android.gallery.module.dataset.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0611p implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ MediaDataRef g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2989h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2990i;

    public /* synthetic */ C0611p(MediaDataCollection mediaDataCollection, int i2, int i7, BufferedArray bufferedArray, Cursor cursor) {
        this.g = mediaDataCollection;
        this.e = i2;
        this.f = i7;
        this.f2989h = bufferedArray;
        this.f2990i = cursor;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((MediaDataCollection) this.g).lambda$load$0(this.e, this.f, (BufferedArray) this.f2989h, (Cursor) this.f2990i);
                return;
            default:
                ((MediaDataEntire) this.g).lambda$readAsync$0((BooleanSupplier) this.f2989h, this.e, this.f, (MediaData.OnDataReadListener) this.f2990i);
                return;
        }
    }

    public /* synthetic */ C0611p(MediaDataEntire mediaDataEntire, BooleanSupplier booleanSupplier, int i2, int i7, MediaData.OnDataReadListener onDataReadListener) {
        this.g = mediaDataEntire;
        this.f2989h = booleanSupplier;
        this.e = i2;
        this.f = i7;
        this.f2990i = onDataReadListener;
    }
}
