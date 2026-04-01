package com.samsung.android.gallery.app.activity;

import android.os.Bundle;
import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryActivityHandler.BottomBarHandler e;

    public /* synthetic */ a(GalleryActivityHandler.BottomBarHandler bottomBarHandler, int i2) {
        this.d = i2;
        this.e = bottomBarHandler;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        GalleryActivityHandler.BottomBarHandler bottomBarHandler = this.e;
        switch (i2) {
            case 0:
                bottomBarHandler.close(obj, bundle);
                return;
            case 1:
                bottomBarHandler.inflate(obj, bundle);
                return;
            case 2:
                bottomBarHandler.add(obj, bundle);
                return;
            case 3:
                bottomBarHandler.show(obj, bundle);
                return;
            case 4:
                bottomBarHandler.hide(obj, bundle);
                return;
            default:
                bottomBarHandler.restore(obj, bundle);
                return;
        }
    }
}
