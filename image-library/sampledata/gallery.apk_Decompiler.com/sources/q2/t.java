package q2;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t implements DynamicAnimation.OnAnimationEndListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u f1889a;

    public t(u uVar) {
        this.f1889a = uVar;
    }

    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f5) {
        u uVar = this.f1889a;
        if (uVar.f1898h) {
            uVar.getProjectionView$material_release().f(true);
            uVar.f1898h = false;
        }
    }
}
