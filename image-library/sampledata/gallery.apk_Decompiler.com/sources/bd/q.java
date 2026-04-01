package bd;

import androidx.dynamicanimation.animation.DynamicAnimation;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.TouchInteractionHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ float d;
    public final /* synthetic */ float e;
    public final /* synthetic */ TouchInteractionHandler f;

    public /* synthetic */ q(float f5, float f8, TouchInteractionHandler touchInteractionHandler) {
        this.d = f5;
        this.e = f8;
        this.f = touchInteractionHandler;
    }

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f5, float f8) {
        TouchInteractionHandler.animateIntensity$lambda$2(this.d, this.e, this.f, dynamicAnimation, f5, f8);
    }
}
