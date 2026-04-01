package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class N implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataMdeTimeline e;

    public /* synthetic */ N(MediaDataMdeTimeline mediaDataMdeTimeline, int i2) {
        this.d = i2;
        this.e = mediaDataMdeTimeline;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MediaDataMdeTimeline mediaDataMdeTimeline = this.e;
        switch (i2) {
            case 0:
                mediaDataMdeTimeline.onGroupMemberDataCursorChanged(obj, bundle);
                return;
            default:
                mediaDataMdeTimeline.onGroupMemberDataSynced(obj, bundle);
                return;
        }
    }
}
