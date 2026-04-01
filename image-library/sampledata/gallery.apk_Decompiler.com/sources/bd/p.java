package bd;

import android.graphics.PointF;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.TouchInteractionHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ PointF d;
    public final /* synthetic */ PointF e;
    public final /* synthetic */ TouchInteractionHandler f;

    public /* synthetic */ p(PointF pointF, PointF pointF2, TouchInteractionHandler touchInteractionHandler) {
        this.d = pointF;
        this.e = pointF2;
        this.f = touchInteractionHandler;
    }

    public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f5, float f8) {
        TouchInteractionHandler.moveToPosition$lambda$1(this.d, this.e, this.f, dynamicAnimation, f5, f8);
    }
}
