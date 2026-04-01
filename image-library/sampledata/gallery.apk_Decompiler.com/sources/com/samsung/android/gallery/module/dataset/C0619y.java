package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.dataset.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0619y implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataEntire e;

    public /* synthetic */ C0619y(MediaDataEntire mediaDataEntire, int i2) {
        this.d = i2;
        this.e = mediaDataEntire;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MediaDataEntire mediaDataEntire = this.e;
        switch (i2) {
            case 0:
                mediaDataEntire.onDataCursorChanged(obj, bundle);
                return;
            case 1:
                mediaDataEntire.onDataChangedGmp(obj, bundle);
                return;
            case 2:
                mediaDataEntire.onDataChanged(obj, bundle);
                return;
            default:
                mediaDataEntire.onDataDirty(obj, bundle);
                return;
        }
    }
}
