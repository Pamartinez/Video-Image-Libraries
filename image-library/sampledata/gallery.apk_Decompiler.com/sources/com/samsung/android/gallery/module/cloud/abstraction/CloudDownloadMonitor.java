package com.samsung.android.gallery.module.cloud.abstraction;

import Sd.C0836a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface CloudDownloadMonitor {
    void addProgressListener(String str, CloudDownloadListener cloudDownloadListener);

    C0836a getDownloadRequest(String str) {
        return null;
    }

    CloudDownloadListener getProgressListener(String str) {
        return null;
    }

    void clear(String str) {
    }

    void addDownloadRequest(String str, C0836a aVar) {
    }
}
