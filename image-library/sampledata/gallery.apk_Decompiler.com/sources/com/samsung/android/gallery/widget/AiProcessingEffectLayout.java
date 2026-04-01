package com.samsung.android.gallery.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightConfig;
import com.samsung.android.sesl.visualeffect.lighteffects.processinglight.ProcessingLightEffect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiProcessingEffectLayout extends RelativeLayout {
    private static final boolean SUPPORT = Features.isEnabled(Features.SUPPORT_AI_PROCESSING_EFFECT);
    private ProcessingLightEffect mEffect;

    public AiProcessingEffectLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void initEffect() {
        if (this.mEffect == null && Build.VERSION.SDK_INT >= 33 && SUPPORT) {
            ProcessingLightConfig vi_config_overlay = ProcessingLightConfig.Companion.getVI_CONFIG_OVERLAY();
            vi_config_overlay.setFrameRate(60.0f);
            this.mEffect = new ProcessingLightEffect(getContext(), this, vi_config_overlay);
        }
    }

    public void playEffect() {
        if (SUPPORT) {
            if (this.mEffect == null) {
                initEffect();
            }
            ProcessingLightEffect processingLightEffect = this.mEffect;
            if (processingLightEffect != null && Build.VERSION.SDK_INT >= 33) {
                processingLightEffect.start();
            }
        }
    }

    public void stopEffect() {
        ProcessingLightEffect processingLightEffect = this.mEffect;
        if (processingLightEffect != null && Build.VERSION.SDK_INT >= 33 && SUPPORT) {
            processingLightEffect.stop();
            this.mEffect.remove(this);
            this.mEffect.release();
            this.mEffect = null;
        }
    }
}
