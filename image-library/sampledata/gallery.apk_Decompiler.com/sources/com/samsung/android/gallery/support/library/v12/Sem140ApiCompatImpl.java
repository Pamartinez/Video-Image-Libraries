package com.samsung.android.gallery.support.library.v12;

import androidx.appcompat.util.SeslKoreanGeneralizer;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v12.media.Sec140MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem140ApiCompatImpl extends Sem135ApiCompatImpl {
    public MediaPlayerCompat createSecMediaPlayer(int i2) {
        return new Sec140MediaPlayerCompat(i2);
    }

    public String naturalizeText(String str) {
        try {
            return new SeslKoreanGeneralizer().naturalizeText(str);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}
