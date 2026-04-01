package com.samsung.android.gallery.support.library.v11;

import N2.j;
import android.media.MediaMetadataRetriever;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.library.v11.remaster.SemVslMesDetectorCompat121;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem121ApiCompatImpl extends Sem120ApiCompatImpl {
    public VslMesDetectorCompat getVslMesDetectorCompat(String str) {
        return new SemVslMesDetectorCompat121(str);
    }

    public void setVideoHwCodecEnabled(MediaMetadataRetriever mediaMetadataRetriever, boolean z) {
        try {
            mediaMetadataRetriever.semSetDetailedThumbnailMode(z ^ true ? 1 : 0);
        } catch (Exception e) {
            j.C(e, new StringBuilder("setVideoHwCodecEnabled failed. e="), this.TAG);
        }
    }
}
