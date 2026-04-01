package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.dataset.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0609n implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataCollection e;

    public /* synthetic */ C0609n(MediaDataCollection mediaDataCollection, int i2) {
        this.d = i2;
        this.e = mediaDataCollection;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MediaDataCollection mediaDataCollection = this.e;
        switch (i2) {
            case 0:
                mediaDataCollection.onDataCursorChanged(obj, bundle);
                return;
            case 1:
                mediaDataCollection.onDataChanged(obj, bundle);
                return;
            default:
                mediaDataCollection.onDataDirty(obj, bundle);
                return;
        }
    }
}
