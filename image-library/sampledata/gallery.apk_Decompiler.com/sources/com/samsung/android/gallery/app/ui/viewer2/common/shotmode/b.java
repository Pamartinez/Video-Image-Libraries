package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ PanoramaShotHandler d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ EventContext f;

    public /* synthetic */ b(PanoramaShotHandler panoramaShotHandler, MediaItem mediaItem, EventContext eventContext) {
        this.d = panoramaShotHandler;
        this.e = mediaItem;
        this.f = eventContext;
    }

    public final void accept(Object obj) {
        this.d.lambda$executeInternal$1(this.e, this.f, (Bitmap) obj);
    }
}
