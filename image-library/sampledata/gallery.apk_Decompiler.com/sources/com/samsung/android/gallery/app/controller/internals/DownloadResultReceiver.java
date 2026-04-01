package com.samsung.android.gallery.app.controller.internals;

import O3.l;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadResultReceiver extends ResultReceiver {
    private final Runnable mDirty;
    private final MediaItem mMediaItem;

    public DownloadResultReceiver(MediaItem mediaItem, Runnable runnable) {
        super((Handler) null);
        this.mMediaItem = mediaItem;
        this.mDirty = runnable;
    }

    public void onReceiveResult(int i2, Bundle bundle) {
        if (i2 == 1) {
            Log.d("DownloadResultReceiver", "download completed");
            if (PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW && DirectorsViewCache.isDualVideo(this.mMediaItem)) {
                DirectorsViewCache.getInstance().forceReload();
            }
            Optional.ofNullable(this.mDirty).ifPresent(new l(0));
        }
    }
}
