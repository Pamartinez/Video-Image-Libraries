package com.samsung.android.gallery.widget.photoview;

import android.graphics.Canvas;
import com.samsung.android.gallery.widget.photoview.AliveZoomCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ AliveZoomCompat d;
    public final /* synthetic */ PhotoView e;
    public final /* synthetic */ Canvas f;

    public /* synthetic */ d(AliveZoomCompat aliveZoomCompat, PhotoView photoView, Canvas canvas) {
        this.d = aliveZoomCompat;
        this.e = photoView;
        this.f = canvas;
    }

    public final void accept(Object obj) {
        this.d.lambda$renderTileOnDraw$3(this.e, this.f, (AliveZoomCompat.TileCompat) obj);
    }
}
