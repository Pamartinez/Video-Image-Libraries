package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.publisher.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0638s implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DataChangeEventPublisher e;

    public /* synthetic */ C0638s(DataChangeEventPublisher dataChangeEventPublisher, int i2) {
        this.d = i2;
        this.e = dataChangeEventPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        DataChangeEventPublisher dataChangeEventPublisher = this.e;
        switch (i2) {
            case 0:
                dataChangeEventPublisher.onContext(obj, bundle);
                return;
            case 1:
                dataChangeEventPublisher.onPppCompleted(obj, bundle);
                return;
            case 2:
                dataChangeEventPublisher.onDataStamp(obj, bundle);
                return;
            case 3:
                dataChangeEventPublisher.onCollect(obj, bundle);
                return;
            case 4:
                dataChangeEventPublisher.notifyOnChangeOnResume(obj, bundle);
                return;
            case 5:
                dataChangeEventPublisher.onResume(obj, bundle);
                return;
            case 6:
                dataChangeEventPublisher.onForceRefreshOnResume(obj, bundle);
                return;
            default:
                dataChangeEventPublisher.onRefreshWithoutDelay(obj, bundle);
                return;
        }
    }
}
