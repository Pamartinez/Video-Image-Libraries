package com.samsung.android.gallery.plugins.portrait;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        LiveEffectBixbyDelegate liveEffectBixbyDelegate = (LiveEffectBixbyDelegate) obj;
        switch (this.d) {
            case 0:
                liveEffectBixbyDelegate.destroy();
                return;
            case 1:
                liveEffectBixbyDelegate.handlePendedBixbyAction();
                return;
            default:
                liveEffectBixbyDelegate.updateStateCallback();
                return;
        }
    }
}
