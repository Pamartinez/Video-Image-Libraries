package com.samsung.android.gallery.support.library.v9.media;

import A.a;
import Xa.f;
import android.graphics.Bitmap;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.media.SemMediaPlayer;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec115MediaPlayerCompat extends Sec100MediaPlayerCompat {
    public Sec115MediaPlayerCompat(int i2) {
        super(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaybackCompleteListener$0(MediaPlayerCompat.OnPlayBackCompleteListener onPlayBackCompleteListener, SemMediaPlayer semMediaPlayer) {
        onPlayBackCompleteListener.onPlaybackComplete(this);
    }

    public Bitmap getCurrentFrame(int i2, int i7) {
        try {
            System.currentTimeMillis();
            return this.mSemMediaPlayer.getCurrentFrame(i2, i7);
        } catch (Error | Exception e) {
            C0212a.y(e, a.h(i2, i7, "getCurrentFrame {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "} failed. e="), this.TAG);
            return null;
        }
    }

    public int getRenderingPosition() {
        try {
            return this.mSemMediaPlayer.getLastRenderedVideoPosition();
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("getLastRenderedVideoPosition failed e="), this.TAG);
            return -1;
        }
    }

    public void seekToAdaptively(int i2) {
        try {
            this.mSemMediaPlayer.seekTo(i2, 4);
        } catch (Error | Exception e) {
            C0212a.y(e, C0086a.o(i2, "seekToAdaptively {", "} failed e="), this.TAG);
        }
    }

    public void setPlaybackCompleteListener(MediaPlayerCompat.OnPlayBackCompleteListener onPlayBackCompleteListener) {
        if (onPlayBackCompleteListener != null) {
            try {
                this.mSemMediaPlayer.setOnPlaybackCompleteListener(new f(this, onPlayBackCompleteListener));
            } catch (IllegalArgumentException | IllegalStateException e) {
                String str = this.TAG;
                Log.e(str, "setPlaybackCompleteListener failed e=" + e.getMessage());
            }
        } else {
            this.mSemMediaPlayer.setOnPlaybackCompleteListener((SemMediaPlayer.OnPlaybackCompleteListener) null);
        }
    }

    public boolean setPlaybackRange(int i2, int i7) {
        try {
            this.mSemMediaPlayer.setPlaybackRange(i2, i7);
            return true;
        } catch (IllegalArgumentException | IllegalStateException e) {
            String str = this.TAG;
            Log.e(str, "setPlaybackRange failed e=" + e.getMessage());
            return false;
        }
    }
}
