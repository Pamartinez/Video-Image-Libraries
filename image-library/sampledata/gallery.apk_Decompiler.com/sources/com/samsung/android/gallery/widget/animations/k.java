package com.samsung.android.gallery.widget.animations;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ SimpleDragShrinkHandler d;
    public final /* synthetic */ View e;
    public final /* synthetic */ float f;
    public final /* synthetic */ double g;

    public /* synthetic */ k(SimpleDragShrinkHandler simpleDragShrinkHandler, View view, float f5, double d2) {
        this.d = simpleDragShrinkHandler;
        this.e = view;
        this.f = f5;
        this.g = d2;
    }

    public final void run() {
        this.d.lambda$onDragMove$3(this.e, this.f, this.g);
    }
}
