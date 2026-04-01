package com.samsung.android.gallery.app.controller.trash;

import com.samsung.android.gallery.app.controller.trash.OneTrashMigrationCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ OneTrashMigrationCmd.OneTrashMigrationTask d;

    public /* synthetic */ a(OneTrashMigrationCmd.OneTrashMigrationTask oneTrashMigrationTask) {
        this.d = oneTrashMigrationTask;
    }

    public final void run() {
        this.d.lambda$onPreExecute$0();
    }
}
