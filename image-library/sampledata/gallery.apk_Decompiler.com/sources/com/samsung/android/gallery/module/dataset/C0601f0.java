package com.samsung.android.gallery.module.dataset;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.module.dataset.f0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0601f0 implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaDataSearch e;

    public /* synthetic */ C0601f0(MediaDataSearch mediaDataSearch, int i2) {
        this.d = i2;
        this.e = mediaDataSearch;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MediaDataSearch mediaDataSearch = this.e;
        switch (i2) {
            case 0:
                mediaDataSearch.swapPartialCursors(obj, bundle);
                return;
            default:
                mediaDataSearch.updateCategoryLocationKeyOrder(obj, bundle);
                return;
        }
    }
}
