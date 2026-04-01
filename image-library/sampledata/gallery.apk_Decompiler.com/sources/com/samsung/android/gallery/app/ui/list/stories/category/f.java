package com.samsung.android.gallery.app.ui.list.stories.category;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ f(boolean z, int i2) {
        this.d = i2;
        this.e = z;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        boolean z = this.e;
        switch (i2) {
            case 0:
                ((StoriesCategory2Header) obj).handleMultiWindowModeChanged(z);
                return;
            default:
                ((TopColorEffectHandler) obj).setEnable(!z);
                return;
        }
    }
}
