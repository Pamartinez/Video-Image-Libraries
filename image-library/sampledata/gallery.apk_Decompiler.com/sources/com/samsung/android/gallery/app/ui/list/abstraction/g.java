package com.samsung.android.gallery.app.ui.list.abstraction;

import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Consumer {
    public final /* synthetic */ AbsListFragment d;
    public final /* synthetic */ float e;

    public /* synthetic */ g(AbsListFragment absListFragment, float f) {
        this.d = absListFragment;
        this.e = f;
    }

    public final void accept(Object obj) {
        this.d.lambda$onAppbarVisibleRatio$3(this.e, (GalleryAppBarLayout) obj);
    }
}
