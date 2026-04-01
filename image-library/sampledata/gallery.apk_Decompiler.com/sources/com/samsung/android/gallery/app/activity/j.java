package com.samsung.android.gallery.app.activity;

import com.samsung.android.gallery.module.secured.PrivateDatabase;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final void run() {
        PrivateDatabase.getInstance().deleteTrashExpired();
    }
}
