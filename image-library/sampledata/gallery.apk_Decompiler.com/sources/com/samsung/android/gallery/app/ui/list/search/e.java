package com.samsung.android.gallery.app.ui.list.search;

import android.view.WindowInsets;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ e(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((CategoryCardListAdapterV2) this.e).lambda$bindMapViewIfLocationCard$1((ICategoryCardViewHolder) this.f, (MediaData) this.g);
                return;
            default:
                FloatingToolbarLayout floatingToolbarLayout = (FloatingToolbarLayout) this.f;
                ((CollectionFragment) this.e).lambda$adjustEmptyViewMargin$4(floatingToolbarLayout, (WindowInsets) this.g);
                return;
        }
    }
}
