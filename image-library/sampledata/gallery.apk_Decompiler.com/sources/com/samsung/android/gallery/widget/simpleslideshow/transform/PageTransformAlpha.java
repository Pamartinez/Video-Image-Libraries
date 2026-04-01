package com.samsung.android.gallery.widget.simpleslideshow.transform;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageTransformAlpha extends PageTransform {
    public PageTransformAlpha(int i2) {
        super(i2);
    }

    public void transformPageInternal(View view, float f) {
        if (this.mDelayProgress < f) {
            view.setAlpha(getValueDelta(f) + this.mInitialValue);
        }
    }

    public void transformStart(View view, float f) {
        view.setAlpha(this.mInitialValue);
    }
}
