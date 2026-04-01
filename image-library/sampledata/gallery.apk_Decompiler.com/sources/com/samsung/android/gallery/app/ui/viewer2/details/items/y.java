package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class y implements DetailsViewUpdater {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsItem e;

    public /* synthetic */ y(DetailsItem detailsItem, int i2) {
        this.d = i2;
        this.e = detailsItem;
    }

    public final void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        int i2 = this.d;
        DetailsItem detailsItem = this.e;
        switch (i2) {
            case 0:
                ((DetailsItemLocationText) detailsItem).updateAddressView(mediaItem, detailsLoadResult);
                return;
            case 1:
                ((DetailsItemLocationText) detailsItem).updatePoiView(mediaItem, detailsLoadResult);
                return;
            default:
                ((DetailsItemDebugExif) detailsItem).updateFileData(mediaItem, detailsLoadResult);
                return;
        }
    }
}
