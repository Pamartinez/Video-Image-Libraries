package com.samsung.android.gallery.support.library.v0.media;

import M4.j;
import com.samsung.android.gallery.support.library.abstraction.MediaTranscodeCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.library.sef.SefParser;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemMediaTranscodeCompat extends MediaTranscodeCompat {
    private String getStickerBgmKey(SefParser sefParser) {
        return (String) Arrays.stream(sefParser.getKeyNameArray()).filter(new j(27)).findFirst().orElse((Object) null);
    }

    public long[] getAudioStreamInfoFromSticker(String str) {
        SefParser unpack = new SefParser(str).unpack();
        String stickerBgmKey = getStickerBgmKey(unpack);
        if (stickerBgmKey != null) {
            return unpack.getDataPosition(stickerBgmKey);
        }
        return null;
    }

    public long[] getVideoStreamInfoFromMotionPhoto(String str) {
        return SefParser.getDataPosition(str, "MotionPhoto_Data");
    }

    public boolean hasAudioStreamInSticker(String str) {
        try {
            SefParser unpack = new SefParser(str).unpack();
            if (!unpack.available() || !unpack.hasData(SefInfo.CAMERA_STICKER_INFO.keyName) || getStickerBgmKey(unpack) == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            N2.j.D(e, new StringBuilder("hasAudioStreamInSticker e="), this.TAG);
            return false;
        }
    }

    public String tag() {
        return "SemMediaTranscodeCompat";
    }
}
