package com.samsung.android.gallery.module.abstraction;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ f(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        String str = (String) obj;
        switch (this.d) {
            case 0:
                VisualSearchCategory.addActivity();
                return;
            case 1:
                VisualSearchCategory.addShotMode();
                return;
            default:
                VisualSearchCategory.addMyTag();
                return;
        }
    }
}
