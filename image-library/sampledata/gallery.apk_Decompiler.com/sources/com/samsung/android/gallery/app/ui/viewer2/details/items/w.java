package com.samsung.android.gallery.app.ui.viewer2.details.items;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsLoadResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements DetailsViewUpdater {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetailsItemLocation e;

    public /* synthetic */ w(DetailsItemLocation detailsItemLocation, int i2) {
        this.d = i2;
        this.e = detailsItemLocation;
    }

    public final void updateViewInternal(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        int i2 = this.d;
        DetailsItemLocation detailsItemLocation = this.e;
        switch (i2) {
            case 0:
                detailsItemLocation.preUpdateView(mediaItem, detailsLoadResult);
                return;
            case 1:
                detailsItemLocation.updateNoAuthView(mediaItem, detailsLoadResult);
                return;
            case 2:
                detailsItemLocation.updateAddressView(mediaItem, detailsLoadResult);
                return;
            case 3:
                detailsItemLocation.updatePoiView(mediaItem, detailsLoadResult);
                return;
            case 4:
                detailsItemLocation.updateMapSnap(mediaItem, detailsLoadResult);
                return;
            default:
                detailsItemLocation.updateMapMarker(mediaItem, detailsLoadResult);
                return;
        }
    }
}
