package com.samsung.android.gallery.module.cloud.abstraction;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DownloadCanceller {
    void onCancel(Context context);

    void setMonitor(CloudDownloadMonitor cloudDownloadMonitor) {
    }
}
