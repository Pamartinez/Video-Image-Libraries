package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* renamed from: com.samsung.android.gallery.module.publisher.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0642w implements InstantSubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ DataChangeEventPublisher e;

    public /* synthetic */ C0642w(DataChangeEventPublisher dataChangeEventPublisher, int i2) {
        this.d = i2;
        this.e = dataChangeEventPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        DataChangeEventPublisher dataChangeEventPublisher = this.e;
        switch (i2) {
            case 0:
                dataChangeEventPublisher.onThumbnailLoadDone(obj, bundle);
                return;
            default:
                dataChangeEventPublisher.onPause(obj, bundle);
                return;
        }
    }
}
