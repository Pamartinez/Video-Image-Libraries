package com.samsung.android.gallery.widget.animations;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ NewPinchShrinkHandler d;
    public final /* synthetic */ float e;
    public final /* synthetic */ View f;

    public /* synthetic */ f(NewPinchShrinkHandler newPinchShrinkHandler, View view, float f5) {
        this.d = newPinchShrinkHandler;
        this.e = f5;
        this.f = view;
    }

    public final void run() {
        float f5 = this.e;
        this.d.lambda$onDragMove$0(f5, this.f);
    }
}
