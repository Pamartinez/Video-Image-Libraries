package com.samsung.android.gallery.widget.livemotion.transform;

import android.view.View;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageTransformAlpha extends PageTransform {
    public PageTransformAlpha(int i2) {
        super(i2);
    }

    public KenBurnsInfo.Property copyProperty(KenBurnsInfo.Property property) {
        return property.setInitAlpha(this.mInitialValue).setTargetAlpha(this.mTargetValue).setDelay(this.mDelayProgress);
    }

    public void transformPageInternal(View view, float f) {
        if (this.mDelayProgress < f) {
            view.setAlpha(getValueDelta(f) + this.mInitialValue);
            return;
        }
        float alpha = view.getAlpha();
        float f5 = this.mInitialValue;
        if (alpha != f5) {
            view.setAlpha(f5);
        }
    }

    public void transformStart(View view, float f) {
        view.setAlpha(this.mInitialValue);
    }
}
