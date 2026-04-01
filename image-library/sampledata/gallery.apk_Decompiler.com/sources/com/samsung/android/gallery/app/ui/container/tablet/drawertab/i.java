package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ i(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        ((DrawerSlideAnimationManager) obj).setState(this.d);
    }
}
