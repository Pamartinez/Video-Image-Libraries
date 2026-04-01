package androidx.transition;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.transition.Transition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DynamicAnimation.OnAnimationEndListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Transition.SeekController f1034a;

    public /* synthetic */ a(Transition.SeekController seekController) {
        this.f1034a = seekController;
    }

    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f5) {
        this.f1034a.lambda$ensureAnimation$0(dynamicAnimation, z, f, f5);
    }
}
