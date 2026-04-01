package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ CoordinatorLayout d;
    public final /* synthetic */ AppBarLayout e;
    public final /* synthetic */ AppBarLayout.BaseBehavior f;

    public a(CoordinatorLayout coordinatorLayout, AppBarLayout.BaseBehavior baseBehavior, AppBarLayout appBarLayout) {
        this.f = baseBehavior;
        this.d = coordinatorLayout;
        this.e = appBarLayout;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f.g(this.d, this.e, ((Integer) valueAnimator.getAnimatedValue()).intValue());
    }
}
