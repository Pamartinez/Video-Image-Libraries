package com.samsung.android.gallery.widget.animations;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.animations.SimpleDragShrinkHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ g(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SimpleDragShrinkHandler) this.e).lambda$restartViewer$1((Bitmap) this.f);
                return;
            case 1:
                ((SimpleDragShrinkHandler) this.e).lambda$onDragMove$4((View) this.f);
                return;
            default:
                ((SimpleDragShrinkHandler.AnonymousClass2) this.e).lambda$onAnimationEnd$1((ImageView) this.f);
                return;
        }
    }
}
