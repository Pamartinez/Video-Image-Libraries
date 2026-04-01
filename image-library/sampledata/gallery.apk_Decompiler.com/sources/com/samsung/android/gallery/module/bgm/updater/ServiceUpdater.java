package com.samsung.android.gallery.module.bgm.updater;

import android.net.Uri;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ServiceUpdater extends AbsUpdater {
    public ServiceUpdater(BgmUpdateListener bgmUpdateListener) {
        super(bgmUpdateListener);
    }

    public DownloadListener createDownloadListener() {
        return new DownloadListener() {
            public void onDownloaded(boolean z, String str, ArrayList<Uri> arrayList) {
                if (z) {
                    ServiceUpdater.this.mBgmCache.addBgmInfo(str, arrayList, "onDownloaded");
                }
                BgmUpdateListener bgmUpdateListener = ServiceUpdater.this.mUpdateListener;
                if (bgmUpdateListener != null) {
                    bgmUpdateListener.onDownloaded(z, str, arrayList);
                }
            }
        };
    }

    public boolean isDownloaded(String str) {
        if (this.mBgmCache.isPreloadBgm(str) || this.mBgmCache.hasBgm(str)) {
            return true;
        }
        return false;
    }
}
