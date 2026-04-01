package com.samsung.android.gallery.support.library.abstraction;

import android.util.Log;
import com.samsung.android.gallery.support.library.sef.MotionPhotoGED;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaTranscodeCompat {
    protected final String TAG;

    public MediaTranscodeCompat() {
        String tag = tag();
        this.TAG = tag;
        Log.v(tag, "construct");
    }

    public boolean convertHeif2Jpeg(String str, String str2) {
        return false;
    }

    public long[] getAudioStreamInfoFromSticker(String str) {
        return null;
    }

    public final long[] getVideoDataPosition(File file) {
        long j2;
        long j3;
        try {
            MotionPhotoGED.MotionPhotoInfoGED sEFDataPositionGED = MotionPhotoGED.getSEFDataPositionGED(file, "MotionPhoto_Data");
            if (sEFDataPositionGED != null) {
                j3 = sEFDataPositionGED.offset;
                j2 = sEFDataPositionGED.length;
            } else {
                j3 = 0;
                j2 = 0;
            }
            return new long[]{j3, j2};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public long[] getVideoStreamInfoFromMotionPhoto(String str) {
        return getVideoDataPosition(new File(str));
    }

    public boolean hasAudioStreamInSticker(String str) {
        return false;
    }

    public String tag() {
        return "MediaTranscodeCompat";
    }
}
