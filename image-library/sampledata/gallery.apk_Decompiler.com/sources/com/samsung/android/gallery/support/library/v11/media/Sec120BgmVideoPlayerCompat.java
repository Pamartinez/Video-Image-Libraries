package com.samsung.android.gallery.support.library.v11.media;

import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.v9.media.Sec115MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec120BgmVideoPlayerCompat extends Sec115MediaPlayerCompat {
    public Sec120BgmVideoPlayerCompat(int i2) {
        super(i2);
    }

    public boolean hasAudioTrack() {
        return false;
    }

    public void initBackgroundMusic(BgmOptions bgmOptions) {
        if (bgmOptions.size() > 0) {
            bgmOptions.release();
        }
    }

    public void setVolume(float f, float f5) {
    }
}
