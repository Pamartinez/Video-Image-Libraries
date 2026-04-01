package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ PanoramaShotHandler d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ Blackboard f;
    public final /* synthetic */ Consumer g;

    public /* synthetic */ c(PanoramaShotHandler panoramaShotHandler, MediaItem mediaItem, Blackboard blackboard, Consumer consumer) {
        this.d = panoramaShotHandler;
        this.e = mediaItem;
        this.f = blackboard;
        this.g = consumer;
    }

    public final void run() {
        this.d.lambda$loadBitmap$2(this.e, this.f, this.g);
    }
}
