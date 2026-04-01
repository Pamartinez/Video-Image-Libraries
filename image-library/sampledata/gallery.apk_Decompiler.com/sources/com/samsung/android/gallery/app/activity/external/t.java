package com.samsung.android.gallery.app.activity.external;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewNavigatorExternal e;

    public /* synthetic */ t(ViewNavigatorExternal viewNavigatorExternal, int i2) {
        this.d = i2;
        this.e = viewNavigatorExternal;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        ViewNavigatorExternal viewNavigatorExternal = this.e;
        switch (i2) {
            case 0:
                viewNavigatorExternal.onActivityCreateExternal(obj, bundle);
                return;
            case 1:
                viewNavigatorExternal.onRequestQuickViewItem(obj, bundle);
                return;
            case 2:
                viewNavigatorExternal.onTimelineDataLoaded(obj, bundle);
                return;
            case 3:
                viewNavigatorExternal.onSharingsDataLoaded(obj, bundle);
                return;
            default:
                viewNavigatorExternal.onRequestCropImage(obj, bundle);
                return;
        }
    }
}
