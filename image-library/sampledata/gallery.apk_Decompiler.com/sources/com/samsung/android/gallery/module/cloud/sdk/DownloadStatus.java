package com.samsung.android.gallery.module.cloud.sdk;

import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.scsp.framework.core.listeners.NetworkStatusListener;
import com.samsung.scsp.media.SamsungCloudMedia;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadStatus implements NetworkStatusListener, DownloadCanceller {
    private int mConnectionId = -1;
    private String mHash;
    private SamsungCloudMedia mMedia;

    public void onCancel(Context context) {
        int i2;
        Log.d("DownloadStatus", "download cancel", this.mHash, Integer.valueOf(this.mConnectionId));
        SamsungCloudMedia samsungCloudMedia = this.mMedia;
        if (samsungCloudMedia != null && (i2 = this.mConnectionId) != -1) {
            samsungCloudMedia.close(i2);
        }
    }

    public final void onClosed(int i2) {
        String str;
        String str2 = this.mHash;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i2);
        if (this.mConnectionId == i2) {
            str = "";
        } else {
            str = C0086a.l(new StringBuilder("("), this.mConnectionId, ")");
        }
        sb2.append(str);
        Log.d("DownloadStatus", "download close", str2, sb2.toString());
        this.mMedia = null;
        this.mConnectionId = -1;
    }

    public final void onStarted(int i2) {
        this.mConnectionId = i2;
        Log.d("DownloadStatus", "download start", this.mHash, Integer.valueOf(i2));
    }

    public final void setSamsungCloudMedia(SamsungCloudMedia samsungCloudMedia) {
        String hexString = Integer.toHexString(samsungCloudMedia.hashCode());
        this.mHash = hexString;
        this.mMedia = samsungCloudMedia;
        Log.d("DownloadStatus", "download setCM", hexString);
    }
}
