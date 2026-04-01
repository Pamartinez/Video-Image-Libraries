package com.samsung.android.gallery.plugins.portrait;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Runnable {
    public final /* synthetic */ LiveEffectDelegate d;
    public final /* synthetic */ O3DPListener e;
    public final /* synthetic */ MediaItem f;
    public final /* synthetic */ Bitmap g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ BiConsumer f3108h;

    public /* synthetic */ q(LiveEffectDelegate liveEffectDelegate, O3DPListener o3DPListener, MediaItem mediaItem, Bitmap bitmap, BiConsumer biConsumer) {
        this.d = liveEffectDelegate;
        this.e = o3DPListener;
        this.f = mediaItem;
        this.g = bitmap;
        this.f3108h = biConsumer;
    }

    public final void run() {
        this.d.lambda$bindView$5(this.e, this.f, this.g, this.f3108h);
    }
}
