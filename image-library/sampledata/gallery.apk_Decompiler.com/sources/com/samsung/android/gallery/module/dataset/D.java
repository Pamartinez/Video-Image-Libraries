package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataList e;

    public /* synthetic */ D(MediaDataList mediaDataList, int i2) {
        this.d = i2;
        this.e = mediaDataList;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MediaDataList mediaDataList = this.e;
        switch (i2) {
            case 0:
                mediaDataList.onDataChanged(obj, bundle);
                return;
            default:
                mediaDataList.onDataChangedGmp(obj, bundle);
                return;
        }
    }
}
