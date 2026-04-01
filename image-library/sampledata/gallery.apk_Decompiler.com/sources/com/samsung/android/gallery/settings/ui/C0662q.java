package com.samsung.android.gallery.settings.ui;

import com.samsung.android.gallery.support.utils.BooleanFeatures;
import com.samsung.android.gallery.support.utils.GalleryPreference;

/* renamed from: com.samsung.android.gallery.settings.ui.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0662q implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ BooleanFeatures e;

    public /* synthetic */ C0662q(BooleanFeatures booleanFeatures, int i2) {
        this.d = i2;
        this.e = booleanFeatures;
    }

    public final void run() {
        int i2 = this.d;
        BooleanFeatures booleanFeatures = this.e;
        switch (i2) {
            case 0:
                GalleryPreference.getInstance().removeState(booleanFeatures.getKey());
                return;
            default:
                GalleryPreference.getInstance().removeState(booleanFeatures.getKey());
                return;
        }
    }
}
