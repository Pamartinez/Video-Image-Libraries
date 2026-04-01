package com.samsung.android.gallery.module.publisher;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.publisher.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0637q implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BitmapDataPublisher e;

    public /* synthetic */ C0637q(BitmapDataPublisher bitmapDataPublisher, int i2) {
        this.d = i2;
        this.e = bitmapDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        BitmapDataPublisher bitmapDataPublisher = this.e;
        switch (i2) {
            case 0:
                bitmapDataPublisher.onContext(obj, bundle);
                return;
            case 1:
                bitmapDataPublisher.onBitmapRequested(obj, bundle);
                return;
            default:
                bitmapDataPublisher.onBitmapRequestCanceled(obj, bundle);
                return;
        }
    }
}
