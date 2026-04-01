package com.samsung.android.gallery.app.controller.internals;

import android.os.Bundle;
import com.samsung.android.gallery.app.controller.internals.UpdateFavoriteInListCmd;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements InstantSubscriberListener {
    public final /* synthetic */ UpdateFavoriteInListCmd.UpdateFavoriteTask d;

    public /* synthetic */ h(UpdateFavoriteInListCmd.UpdateFavoriteTask updateFavoriteTask) {
        this.d = updateFavoriteTask;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.setInterrupted();
    }
}
