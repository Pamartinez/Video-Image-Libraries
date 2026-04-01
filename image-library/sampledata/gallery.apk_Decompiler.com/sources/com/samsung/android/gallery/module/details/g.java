package com.samsung.android.gallery.module.details;

import com.samsung.android.gallery.module.location.LocationUpdater;
import com.samsung.android.gallery.support.utils.MediaScannerListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements MediaScannerListener, LocationUpdater.OnUpdateCompletionListener {
    public final /* synthetic */ EditDetailsUpdater d;

    public /* synthetic */ g(EditDetailsUpdater editDetailsUpdater) {
        this.d = editDetailsUpdater;
    }

    public void onCompleted() {
        this.d.lambda$onTitleAndDateTimeUpdated$4();
    }

    public void onCompleted(boolean z) {
        this.d.lambda$onUpdateLocation$8(z);
    }
}
