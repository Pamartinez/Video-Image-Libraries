package com.samsung.android.gallery.module.bgm.updater;

import android.net.Uri;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DownloadListener {
    void onDownloadedAll(boolean z) {
    }

    void onDownloaded(boolean z, String str, ArrayList<Uri> arrayList) {
    }
}
