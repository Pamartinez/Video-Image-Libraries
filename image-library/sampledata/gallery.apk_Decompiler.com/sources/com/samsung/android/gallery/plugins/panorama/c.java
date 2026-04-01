package com.samsung.android.gallery.plugins.panorama;

import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ PanoramaDelegate d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ BiConsumer f;

    public /* synthetic */ c(PanoramaDelegate panoramaDelegate, MediaItem mediaItem, BiConsumer biConsumer) {
        this.d = panoramaDelegate;
        this.e = mediaItem;
        this.f = biConsumer;
    }

    public final void run() {
        this.d.lambda$bindView$0(this.e, this.f);
    }
}
