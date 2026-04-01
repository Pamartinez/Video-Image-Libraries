package com.samsung.android.gallery.widget.photoview;

import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AliveZoomCompat f3212a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ PhotoView f3213c;
    public final /* synthetic */ boolean d;

    public /* synthetic */ c(AliveZoomCompat aliveZoomCompat, int i2, PhotoView photoView, boolean z) {
        this.f3212a = aliveZoomCompat;
        this.b = i2;
        this.f3213c = photoView;
        this.d = z;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3212a.lambda$refreshRequiredTiles$6(this.b, this.f3213c, this.d, (Integer) obj, (List) obj2);
    }
}
