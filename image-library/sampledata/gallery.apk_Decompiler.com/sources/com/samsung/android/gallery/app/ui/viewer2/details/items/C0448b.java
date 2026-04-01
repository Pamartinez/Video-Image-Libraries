package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.app.ui.viewer2.details.items.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0448b implements SubscriberListener {
    public final /* synthetic */ DetailsItem3rdParty d;

    public /* synthetic */ C0448b(DetailsItem3rdParty detailsItem3rdParty) {
        this.d = detailsItem3rdParty;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.lambda$onResume$0(obj, bundle);
    }
}
