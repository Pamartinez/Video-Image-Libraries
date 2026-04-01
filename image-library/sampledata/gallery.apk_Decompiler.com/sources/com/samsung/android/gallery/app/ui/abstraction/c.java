package com.samsung.android.gallery.app.ui.abstraction;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.animation.PathInterpolator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnimatorSet e;
    public final /* synthetic */ PathInterpolator f;

    public /* synthetic */ c(int i2, AnimatorSet animatorSet, PathInterpolator pathInterpolator) {
        this.d = i2;
        this.e = animatorSet;
        this.f = pathInterpolator;
    }

    public final void accept(Object obj) {
        MvpBaseDelegate.lambda$customizeAnimation$0(this.d, this.e, this.f, (Animator) obj);
    }
}
