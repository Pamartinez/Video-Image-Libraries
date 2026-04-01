package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.dataset.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0617w implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataCursor e;

    public /* synthetic */ C0617w(MediaDataCursor mediaDataCursor, int i2) {
        this.d = i2;
        this.e = mediaDataCursor;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MediaDataCursor mediaDataCursor = this.e;
        switch (i2) {
            case 0:
                mediaDataCursor.onDataCursorChanged(obj, bundle);
                return;
            case 1:
                mediaDataCursor.onFilesDataChanged(obj, bundle);
                return;
            case 2:
                mediaDataCursor.onLocationDataChanged(obj, bundle);
                return;
            case 3:
                mediaDataCursor.onDataChanged(obj, bundle);
                return;
            case 4:
                mediaDataCursor.onDataChangedGmp(obj, bundle);
                return;
            default:
                mediaDataCursor.onDataDirty(obj, bundle);
                return;
        }
    }
}
