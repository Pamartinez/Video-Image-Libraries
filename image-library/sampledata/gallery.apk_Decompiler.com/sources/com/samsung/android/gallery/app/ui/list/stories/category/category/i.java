package com.samsung.android.gallery.app.ui.list.stories.category.category;

import com.samsung.android.gallery.widget.listview.GalleryListView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ StoriesCatTransitoryViewHolder e;

    public /* synthetic */ i(StoriesCatTransitoryViewHolder storiesCatTransitoryViewHolder, int i2) {
        this.d = i2;
        this.e = storiesCatTransitoryViewHolder;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        StoriesCatTransitoryViewHolder storiesCatTransitoryViewHolder = this.e;
        GalleryListView galleryListView = (GalleryListView) obj;
        switch (i2) {
            case 0:
                storiesCatTransitoryViewHolder.lambda$removeScrollListenerToRootList$1(galleryListView);
                return;
            default:
                storiesCatTransitoryViewHolder.lambda$addScrollListenerToRootList$0(galleryListView);
                return;
        }
    }
}
