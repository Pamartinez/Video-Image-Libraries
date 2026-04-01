package com.samsung.android.gallery.module.cloud.sdk;

import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadListener;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.scsp.framework.core.listeners.ProgressListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadMonitor implements ProgressListener, CloudDownloadMonitor {
    private CloudDownloadListener mListener;

    public void addProgressListener(String str, CloudDownloadListener cloudDownloadListener) {
        this.mListener = cloudDownloadListener;
    }

    public void onProgress(long j2, long j3) {
        CloudDownloadListener cloudDownloadListener = this.mListener;
        if (cloudDownloadListener != null) {
            cloudDownloadListener.onProgress(j2, j3);
        }
    }
}
