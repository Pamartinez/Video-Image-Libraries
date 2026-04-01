package com.samsung.android.gallery.app.ui.list.timeline;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TimelinePresenter e;

    public /* synthetic */ d(TimelinePresenter timelinePresenter, int i2) {
        this.d = i2;
        this.e = timelinePresenter;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        TimelinePresenter timelinePresenter = this.e;
        switch (i2) {
            case 0:
                timelinePresenter.updateTipCard(obj, bundle);
                return;
            case 1:
                timelinePresenter.onCloudServiceChanged(obj, bundle);
                return;
            case 2:
                timelinePresenter.onCloudMediaSyncStatusChanged(obj, bundle);
                return;
            case 3:
                timelinePresenter.onCloudSyncOnOffChanged(obj, bundle);
                return;
            default:
                timelinePresenter.onViewChanged(obj, bundle);
                return;
        }
    }
}
