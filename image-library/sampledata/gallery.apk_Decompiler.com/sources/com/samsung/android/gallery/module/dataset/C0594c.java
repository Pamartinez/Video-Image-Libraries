package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.module.dataset.MediaDataList;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.dataset.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0594c implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataRef e;

    public /* synthetic */ C0594c(MediaDataRef mediaDataRef, int i2) {
        this.d = i2;
        this.e = mediaDataRef;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MediaDataRef mediaDataRef = this.e;
        switch (i2) {
            case 0:
                ((MediaDataAlbum) mediaDataRef).onCacheDataLoaded(obj, bundle);
                return;
            case 1:
                ((MediaDataFilter) mediaDataRef).onKeywordChanged(obj, bundle);
                return;
            case 2:
                ((MediaDataList.MediaDataArray) mediaDataRef).onDataDirty(obj, bundle);
                return;
            case 3:
                mediaDataRef.onPppCompleted(obj, bundle);
                return;
            case 4:
                ((MediaDataQuickView) mediaDataRef).onFirstData(obj, bundle);
                return;
            default:
                ((MediaDataScreenShotFilter) mediaDataRef).lambda$createSubscriberList$0(obj, bundle);
                return;
        }
    }
}
