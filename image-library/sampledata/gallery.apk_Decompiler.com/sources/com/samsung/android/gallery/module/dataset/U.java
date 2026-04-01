package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class U implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataMxAlbum e;
    public final /* synthetic */ int f;
    public final /* synthetic */ MediaData.OnDataReadListener g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ BooleanSupplier f2961h;

    public /* synthetic */ U(MediaDataMxAlbum mediaDataMxAlbum, int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier, int i7) {
        this.d = i7;
        this.e = mediaDataMxAlbum;
        this.f = i2;
        this.g = onDataReadListener;
        this.f2961h = booleanSupplier;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$readAsync$8(this.f, this.g, this.f2961h, (MediaData) obj);
                return;
            default:
                this.e.lambda$readAsync$9(this.f, this.g, this.f2961h, (MediaData) obj);
                return;
        }
    }
}
