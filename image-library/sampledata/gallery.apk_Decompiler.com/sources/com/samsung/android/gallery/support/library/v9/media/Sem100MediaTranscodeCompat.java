package com.samsung.android.gallery.support.library.v9.media;

import N2.j;
import com.samsung.android.gallery.support.library.v0.media.SemMediaTranscodeCompat;
import com.samsung.android.media.SemHEIFCodec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem100MediaTranscodeCompat extends SemMediaTranscodeCompat {
    public boolean convertHeif2Jpeg(String str, String str2) {
        try {
            return SemHEIFCodec.transcode(str, str2, 1);
        } catch (Exception e) {
            j.D(e, new StringBuilder("convertHeif2Jpeg failed e="), this.TAG);
            return false;
        }
    }

    public String tag() {
        return "Sem100MediaTranscodeCompat";
    }
}
