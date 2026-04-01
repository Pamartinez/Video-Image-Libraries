package com.samsung.android.gallery.module.cloud;

import com.samsung.android.gallery.module.cloud.SamsungCloudManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ SamsungCloudManager.CloudSyncOnTask d;

    public /* synthetic */ c(SamsungCloudManager.CloudSyncOnTask cloudSyncOnTask) {
        this.d = cloudSyncOnTask;
    }

    public final void run() {
        this.d.lambda$onPostExecute$0();
    }
}
