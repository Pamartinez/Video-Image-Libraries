package com.samsung.android.gallery.app.ui.list.search;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.LocationValue;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements MapContainer.OnSnapshotReadyListener {
    public final /* synthetic */ CategoryLocation2CardHolder d;
    public final /* synthetic */ LocationValue e;

    public /* synthetic */ j(CategoryLocation2CardHolder categoryLocation2CardHolder, LocationValue locationValue) {
        this.d = categoryLocation2CardHolder;
        this.e = locationValue;
    }

    public final void onSnapshotReady(Bitmap bitmap) {
        this.d.lambda$addMapView$7(this.e, bitmap);
    }
}
