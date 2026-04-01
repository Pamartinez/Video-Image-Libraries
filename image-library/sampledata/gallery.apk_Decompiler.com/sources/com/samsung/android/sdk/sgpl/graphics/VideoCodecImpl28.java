package com.samsung.android.sdk.sgpl.graphics;

import N2.j;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class VideoCodecImpl28 extends VideoCodecImpl {
    private static final int SEM_CONFIG_COLOR_ARGB8888 = 1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MethodHolder {
        static final Method api;

        static {
            Method method;
            Class<MediaMetadataRetriever> cls = MediaMetadataRetriever.class;
            try {
                Class cls2 = Long.TYPE;
                Class cls3 = Integer.TYPE;
                method = cls.getMethod("semGetFrameAtTime", new Class[]{cls2, cls3, cls3});
            } catch (Error | Exception unused) {
                method = null;
            }
            api = method;
        }
    }

    public void adjustVideoSize(MediaMetadataRetriever mediaMetadataRetriever, int i2) {
        int i7;
        int i8;
        try {
            String extractMetadata = mediaMetadataRetriever.extractMetadata(18);
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
            if (TextUtils.isEmpty(extractMetadata)) {
                i7 = 0;
            } else {
                i7 = Integer.parseInt(extractMetadata);
            }
            if (TextUtils.isEmpty(extractMetadata2)) {
                i8 = 0;
            } else {
                i8 = Integer.parseInt(extractMetadata2);
            }
            if (i7 > 0 && i8 > 0) {
                float max = ((float) i2) / ((float) Math.max(i7, i8));
                if (max < 1.0f) {
                    mediaMetadataRetriever.semSetVideoSize(Math.round(((float) i7) * max), Math.round(((float) i8) * max), false, true);
                }
            }
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("adjustVideoSize failed. e=")));
        }
    }

    public Bitmap decodeFrame(MediaMetadataRetriever mediaMetadataRetriever, long j2, int i2) {
        Method method = MethodHolder.api;
        if (method != null) {
            try {
                return (Bitmap) method.invoke(method, new Object[]{Long.valueOf(j2), Integer.valueOf(i2), 1});
            } catch (Exception e) {
                String str = this.TAG;
                Log.e(str, "decodeFrame failed. e=" + e.getMessage());
            }
        }
        return super.decodeFrame(mediaMetadataRetriever, j2, i2);
    }
}
