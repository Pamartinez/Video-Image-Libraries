package com.samsung.android.gallery.app.ui.viewer2.details;

import com.samsung.android.gallery.app.ui.viewer2.details.DetailsLoadHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsDataLoadCallback;
import com.samsung.android.gallery.module.details.DetailsLoadResult;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DetailsDataLoadCallback {
    public final /* synthetic */ DetailsLoadHandler.AnonymousClass1 d;

    public /* synthetic */ b(DetailsLoadHandler.AnonymousClass1 r1) {
        this.d = r1;
    }

    public final void onLoaded(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        this.d.lambda$onLayoutChange$0(mediaItem, detailsLoadResult);
    }
}
