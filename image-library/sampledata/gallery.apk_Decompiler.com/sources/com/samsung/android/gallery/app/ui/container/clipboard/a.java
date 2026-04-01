package com.samsung.android.gallery.app.ui.container.clipboard;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ClipboardAnimDelegate d;
    public final /* synthetic */ float e;

    public /* synthetic */ a(ClipboardAnimDelegate clipboardAnimDelegate, float f) {
        this.d = clipboardAnimDelegate;
        this.e = f;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.d.lambda$createRotateAnimator$0(this.e, valueAnimator);
    }
}
