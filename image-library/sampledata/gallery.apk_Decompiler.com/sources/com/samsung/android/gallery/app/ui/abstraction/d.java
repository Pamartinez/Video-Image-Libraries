package com.samsung.android.gallery.app.ui.abstraction;

import android.animation.Animator;
import android.view.animation.PathInterpolator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ PathInterpolator e;

    public /* synthetic */ d(int i2, PathInterpolator pathInterpolator) {
        this.d = i2;
        this.e = pathInterpolator;
    }

    public final void accept(Object obj) {
        MvpBaseDelegate.lambda$customizeAnimation$1(this.d, this.e, (Animator) obj);
    }
}
