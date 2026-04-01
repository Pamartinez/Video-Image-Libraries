package com.samsung.android.gallery.plugins.portrait;

import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ i(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((LiveEffectDelegate) this.e).lambda$bindView$4((BiConsumer) this.f);
                return;
            case 1:
                ((LiveEffectDelegate) this.e).lambda$save$12((BiConsumer) this.f);
                return;
            default:
                ((LiveEffectBixbyDelegate) this.e).lambda$onBixbyAction$0(this.f);
                return;
        }
    }
}
