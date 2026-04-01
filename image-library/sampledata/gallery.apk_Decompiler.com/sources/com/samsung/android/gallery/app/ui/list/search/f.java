package com.samsung.android.gallery.app.ui.list.search;

import android.view.WindowInsets;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.map.search.SearchMapFragmentContainerView;
import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ f(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((CategoryLocation2CardHolder) this.e).lambda$bindLocationInfo$1((MediaData) this.f, (SearchMapFragmentContainerView) obj);
                return;
            default:
                ((CollectionFragment) this.e).lambda$adjustEmptyViewMargin$5((WindowInsets) this.f, (FloatingToolbarLayout) obj);
                return;
        }
    }
}
