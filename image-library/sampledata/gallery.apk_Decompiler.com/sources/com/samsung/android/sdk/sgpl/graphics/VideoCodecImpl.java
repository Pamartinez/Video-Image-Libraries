package com.samsung.android.sdk.sgpl.graphics;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class VideoCodecImpl {
    protected final String TAG = getClass().getSimpleName();

    public Bitmap decodeFrame(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2) {
        return mediaMetadataRetriever.getFrameAtTime(j2, i2);
    }

    public void adjustVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2) {
    }
}
