package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.graphics.drawable.Drawable;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ d(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((GalleryAppBarLayout) obj).setExpanded(true, false);
                return;
            case 1:
                BottomSearchPicturesBehavior.lambda$resetListViewScroll$5((GalleryListView) obj);
                return;
            default:
                ((GalleryToolbar) obj).setNavigationIcon((Drawable) null);
                return;
        }
    }
}
