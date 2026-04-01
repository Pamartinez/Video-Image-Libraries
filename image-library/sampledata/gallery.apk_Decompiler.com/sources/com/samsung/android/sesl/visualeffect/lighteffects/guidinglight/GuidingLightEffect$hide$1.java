package com.samsung.android.sesl.visualeffect.lighteffects.guidinglight;

import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"com/samsung/android/sesl/visualeffect/lighteffects/guidinglight/GuidingLightEffect$hide$1", "Lcom/samsung/android/sesl/visualeffect/lighteffects/guidinglight/AnimationManager$AnimationCallback;", "Lme/x;", "onAnimationStart", "()V", "onAnimationEnd", "onAnimationCancel", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GuidingLightEffect$hide$1 implements AnimationManager.AnimationCallback {
    final /* synthetic */ GuidingLightEffect this$0;

    public GuidingLightEffect$hide$1(GuidingLightEffect guidingLightEffect) {
        this.this$0 = guidingLightEffect;
    }

    public void onAnimationEnd() {
        this.this$0.stopControls();
    }

    public void onAnimationCancel() {
    }

    public void onAnimationStart() {
    }
}
