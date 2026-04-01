package com.samsung.android.gallery.widget.photoview;

import com.samsung.android.gallery.widget.photoview.AliveZoomCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((AliveZoomCompat) obj2).lambda$onSceneDetected$1((PhotoView) obj);
                return;
            case 1:
                ((PhotoView) obj2).mImageProcessor.setTileInvisible((AliveZoomCompat.TileCompat) obj);
                return;
            default:
                ((ImageProcessor) obj2).setTileInvisible((Tile) obj);
                return;
        }
    }
}
