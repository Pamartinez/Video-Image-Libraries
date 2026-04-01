package com.samsung.android.gallery.app.activity.external;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryExternalActivityHandler e;

    public /* synthetic */ a(GalleryExternalActivityHandler galleryExternalActivityHandler, int i2) {
        this.d = i2;
        this.e = galleryExternalActivityHandler;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        GalleryExternalActivityHandler galleryExternalActivityHandler = this.e;
        switch (i2) {
            case 0:
                galleryExternalActivityHandler.restoreContentViewBackground(obj, bundle);
                return;
            default:
                galleryExternalActivityHandler.clearContentViewBackground(obj, bundle);
                return;
        }
    }
}
