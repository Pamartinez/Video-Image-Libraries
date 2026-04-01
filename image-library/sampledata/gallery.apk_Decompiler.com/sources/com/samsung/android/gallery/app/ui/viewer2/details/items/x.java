package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements DetailsViewUpdater {
    public final /* synthetic */ int d;

    public /* synthetic */ x(int i2) {
        this.d = i2;
    }

    public final void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        switch (this.d) {
            case 0:
                DetailsItemLocation.lambda$registerViewUpdater$3(mediaItem, detailsLoadResult);
                return;
            default:
                DetailsItemLocationText.lambda$registerViewUpdater$1(mediaItem, detailsLoadResult);
                return;
        }
    }
}
