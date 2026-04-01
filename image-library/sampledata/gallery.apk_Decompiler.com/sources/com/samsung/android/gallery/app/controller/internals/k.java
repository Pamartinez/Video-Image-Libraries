package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.app.controller.internals.UpdateFavoriteInListCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ UpdateFavoriteInListCmd.UpdateFavoriteTask e;

    public /* synthetic */ k(UpdateFavoriteInListCmd.UpdateFavoriteTask updateFavoriteTask, int i2) {
        this.d = i2;
        this.e = updateFavoriteTask;
    }

    public final void run() {
        int i2 = this.d;
        UpdateFavoriteInListCmd.UpdateFavoriteTask updateFavoriteTask = this.e;
        switch (i2) {
            case 0:
                updateFavoriteTask.lambda$dismissDialog$0();
                return;
            default:
                updateFavoriteTask.lambda$showDialog$3();
                return;
        }
    }
}
