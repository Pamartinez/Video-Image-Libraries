package com.samsung.android.gallery.support.library.v12.media;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec150MediaPlayerCompat extends Sec140MediaPlayerCompat {
    public Sec150MediaPlayerCompat(int i2) {
        super(i2);
    }

    public void beginInstantSlowMoPlay() {
        try {
            this.mSemMediaPlayer.setTemporalZoom(4);
        } catch (Exception e) {
            String str = this.TAG;
            Log.w(str, "beginInstantSlowMoPlay failed. e=" + e.getMessage());
            setSpeedWithPlaybackParam(0.25f);
        }
    }

    public void endInstantSlowMoPlay() {
        try {
            this.mSemMediaPlayer.setTemporalZoom(1);
        } catch (Exception e) {
            String str = this.TAG;
            Log.w(str, "endInstantSlowMoPlay failed. e=" + e.getMessage());
            setSpeedWithPlaybackParam(1.0f);
        }
    }

    public void setSpeedWithPlaybackParam(float f) {
    }
}
