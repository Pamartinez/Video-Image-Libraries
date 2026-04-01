package com.samsung.android.gallery.widget.photoview;

import com.samsung.android.gallery.widget.photoview.AliveZoomCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ AliveZoomCompat d;
    public final /* synthetic */ Integer e;
    public final /* synthetic */ int f;
    public final /* synthetic */ PhotoView g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f3214h;

    public /* synthetic */ e(AliveZoomCompat aliveZoomCompat, Integer num, int i2, PhotoView photoView, boolean z) {
        this.d = aliveZoomCompat;
        this.e = num;
        this.f = i2;
        this.g = photoView;
        this.f3214h = z;
    }

    public final void accept(Object obj) {
        this.d.lambda$refreshRequiredTiles$5(this.e, this.f, this.g, this.f3214h, (AliveZoomCompat.TileCompat) obj);
    }
}
