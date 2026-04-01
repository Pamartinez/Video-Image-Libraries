package com.samsung.android.gallery.support.library.v9.media;

import A.a;
import N2.j;
import android.graphics.Bitmap;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.gallery.support.library.v0.media.SemMediaPlayerCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem115MediaPlayerCompat extends SemMediaPlayerCompat {
    public Sem115MediaPlayerCompat(int i2) {
        super(i2);
    }

    public Bitmap getCurrentFrame(int i2, int i7) {
        try {
            return this.mMediaPlayer.semGetCurrentFrame(i2, i7);
        } catch (Error | Exception e) {
            C0212a.y(e, a.h(i2, i7, "getCurrentFrame {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "} failed. e="), this.TAG);
            return null;
        }
    }

    public int getRenderingPosition() {
        try {
            return this.mMediaPlayer.semGetLastRenderedVideoPosition();
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getLastRenderedVideoPosition failed e="), this.TAG);
            return -1;
        }
    }

    public void seekToAdaptively(int i2) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.mMediaPlayer.semSeekTo(i2, 5);
            String str = this.TAG;
            Log.i(str, "seekToAdaptively {" + i2 + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (IllegalStateException e) {
            j.t(e, C0086a.o(i2, "seekToAdaptively {", "} failed e="), this.TAG);
        }
    }
}
