package com.samsung.android.gallery.app.ui.list.timeline;

import com.samsung.android.gallery.app.ui.list.timeline.SyncViewUpdateManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ SyncViewUpdateManager d;
    public final /* synthetic */ SyncViewUpdateManager.UpdateState e;

    public /* synthetic */ b(SyncViewUpdateManager.UpdateState updateState, SyncViewUpdateManager syncViewUpdateManager) {
        this.d = syncViewUpdateManager;
        this.e = updateState;
    }

    public final void run() {
        this.d.lambda$updateSyncState$2(this.e);
    }
}
