package com.samsung.android.gallery.plugins.panorama;

import com.samsung.android.gallery.plugins.panorama.PanoramaDelegate;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ PanoramaDelegate.AnonymousClass1 e;
    public final /* synthetic */ BiConsumer f;

    public /* synthetic */ f(PanoramaDelegate.AnonymousClass1 r1, BiConsumer biConsumer, int i2) {
        this.d = i2;
        this.e = r1;
        this.f = biConsumer;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onRenderStart$1(this.f);
                return;
            default:
                this.e.lambda$onFailed$0(this.f);
                return;
        }
    }
}
