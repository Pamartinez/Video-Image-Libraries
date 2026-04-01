package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaData;

/* renamed from: com.samsung.android.gallery.module.dataset.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0616v implements Runnable {
    public final /* synthetic */ MediaDataCursor d;
    public final /* synthetic */ int e;
    public final /* synthetic */ MediaData.OnDataReadListener f;
    public final /* synthetic */ int g;

    public /* synthetic */ C0616v(int i2, int i7, MediaData.OnDataReadListener onDataReadListener, MediaDataCursor mediaDataCursor) {
        this.d = mediaDataCursor;
        this.e = i2;
        this.f = onDataReadListener;
        this.g = i7;
    }

    public final void run() {
        MediaData.OnDataReadListener onDataReadListener = this.f;
        this.d.lambda$readAsync$0(this.e, onDataReadListener, this.g);
    }
}
