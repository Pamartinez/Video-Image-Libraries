package com.samsung.android.gallery.app.ui.list.stories.category;

import com.samsung.android.gallery.app.ui.list.stories.category.TopColorEffectHandler;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ TopColorEffectHandler.EffectItem d;
    public final /* synthetic */ int e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ int g;

    public /* synthetic */ n(TopColorEffectHandler.EffectItem effectItem, int i2, MediaItem mediaItem, int i7) {
        this.d = effectItem;
        this.e = i2;
        this.f = mediaItem;
        this.g = i7;
    }

    public final void run() {
        this.d.lambda$bindColor$0(this.e, this.f, this.g);
    }
}
