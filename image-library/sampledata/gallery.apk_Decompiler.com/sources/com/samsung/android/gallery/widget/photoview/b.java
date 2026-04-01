package com.samsung.android.gallery.widget.photoview;

import android.graphics.Canvas;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AliveZoomCompat f3210a;
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ PhotoView f3211c;
    public final /* synthetic */ Canvas d;

    public /* synthetic */ b(AliveZoomCompat aliveZoomCompat, int i2, PhotoView photoView, Canvas canvas) {
        this.f3210a = aliveZoomCompat;
        this.b = i2;
        this.f3211c = photoView;
        this.d = canvas;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3210a.lambda$renderTileOnDraw$4(this.b, this.f3211c, this.d, (Integer) obj, (List) obj2);
    }
}
