package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x0 implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataTimeline e;

    public /* synthetic */ x0(MediaDataTimeline mediaDataTimeline, int i2) {
        this.d = i2;
        this.e = mediaDataTimeline;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MediaDataTimeline mediaDataTimeline = this.e;
        switch (i2) {
            case 0:
                mediaDataTimeline.onDateTimeChanged(obj, bundle);
                return;
            case 1:
                mediaDataTimeline.onDataDirtyTimeline(obj, bundle);
                return;
            case 2:
                mediaDataTimeline.onCachedDataCursorChanged(obj, bundle);
                return;
            case 3:
                mediaDataTimeline.onFilesDataChanged(obj, bundle);
                return;
            case 4:
                mediaDataTimeline.onLocationDataChanged(obj, bundle);
                return;
            default:
                mediaDataTimeline.onDumpRealRatioData(obj, bundle);
                return;
        }
    }
}
