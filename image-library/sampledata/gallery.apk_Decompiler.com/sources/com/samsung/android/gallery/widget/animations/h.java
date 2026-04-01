package com.samsung.android.gallery.widget.animations;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements DynamicAnimation.OnAnimationEndListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Runnable f3196a;

    public /* synthetic */ h(Runnable runnable) {
        this.f3196a = runnable;
    }

    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f5) {
        this.f3196a.run();
    }
}
