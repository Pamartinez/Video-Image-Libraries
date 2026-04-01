package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class z implements InstantSubscriberListener {
    public final /* synthetic */ DetailsItemRelated d;

    public /* synthetic */ z(DetailsItemRelated detailsItemRelated) {
        this.d = detailsItemRelated;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.lambda$subscribeForReturnTransition$0(obj, bundle);
    }
}
