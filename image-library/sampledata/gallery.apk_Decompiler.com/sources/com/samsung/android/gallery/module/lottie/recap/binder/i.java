package com.samsung.android.gallery.module.lottie.recap.binder;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import x0.C0332j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecapTemplateBinder e;
    public final /* synthetic */ AtomicBoolean f;
    public final /* synthetic */ BiConsumer g;

    public /* synthetic */ i(RecapTemplateBinder recapTemplateBinder, AtomicBoolean atomicBoolean, BiConsumer biConsumer, int i2) {
        this.d = i2;
        this.e = recapTemplateBinder;
        this.f = atomicBoolean;
        this.g = biConsumer;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$createLottie$4(this.f, this.g, (C0332j) obj);
                return;
            default:
                this.e.lambda$createLottie$2(this.f, this.g, (C0332j) obj);
                return;
        }
    }
}
