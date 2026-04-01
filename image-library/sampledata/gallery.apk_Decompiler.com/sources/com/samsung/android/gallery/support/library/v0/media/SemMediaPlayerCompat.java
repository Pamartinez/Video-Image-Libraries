package com.samsung.android.gallery.support.library.v0.media;

import N2.j;
import Ra.f;
import android.media.MediaPlayer;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemMediaPlayerCompat extends GedMediaPlayerCompat {
    private int mFrameRate;
    private final AtomicBoolean mSeekPrepared = new AtomicBoolean(false);
    private int mSeekTime;
    private SoundAliveCompat mSoundAliveCompat;
    private boolean mStreamingEnabled;

    public SemMediaPlayerCompat(int i2) {
        super(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$completeSeekTo$0(MediaPlayer mediaPlayer) {
        Log.d(this.TAG, "seekComplete");
        MediaPlayer mediaPlayer2 = this.mMediaPlayer;
        mediaPlayer2.semSeekTo(mediaPlayer2.getDuration(), 1);
        this.mMediaPlayer.setOnSeekCompleteListener((MediaPlayer.OnSeekCompleteListener) null);
    }

    public final void completeSeekTo(boolean z) {
        if (this.mSeekPrepared.getAndSet(false)) {
            if (z) {
                try {
                    if (this.mMediaPlayer.getDuration() - this.mSeekTime < 10) {
                        if ((this.mConfig & 2) > 0) {
                            this.mMediaPlayer.setOnSeekCompleteListener(new f(this));
                        }
                        MediaPlayer mediaPlayer = this.mMediaPlayer;
                        mediaPlayer.semSeekTo(mediaPlayer.getDuration(), 1);
                        return;
                    }
                } catch (IllegalStateException e) {
                    j.t(e, new StringBuilder("completeSeekTo failed e="), this.TAG);
                    return;
                }
            }
            this.mMediaPlayer.semSeekTo(this.mSeekTime, 5);
        }
    }

    public boolean computeTimeTickOnCompletion() {
        return !this.mSeekPrepared.get();
    }

    public int getFrameRate() {
        return this.mFrameRate;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mFrameRate = this.mMediaPlayer.semGetIntParameter(31505);
        super.onPrepared(mediaPlayer);
    }

    public void prepareSeekTo() {
        this.mSeekPrepared.set(true);
    }

    public void release() {
        super.release();
        releaseSoundAlive();
        this.mSeekPrepared.set(false);
    }

    public void releaseSoundAlive() {
        SoundAliveCompat soundAliveCompat;
        if ((this.mConfig & 4) > 0 && (soundAliveCompat = this.mSoundAliveCompat) != null) {
            soundAliveCompat.release();
            this.mSoundAliveCompat = null;
        }
    }

    public final void seekTo(int i2) {
        try {
            if (this.mPlayerState == 7 && i2 < getDurationMs()) {
                this.mPlayerState = 6;
            }
            if (this.mSeekPrepared.get()) {
                this.mSeekTime = i2;
                this.mMediaPlayer.semSeekTo(i2, 0);
                return;
            }
            this.mMediaPlayer.seekTo((long) i2, 2);
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("seekTo failed e="), this.TAG);
        }
    }

    public void set3dEffectPosition(double d) {
        if ((this.mConfig & 4) > 0) {
            try {
                if (this.mSoundAliveCompat == null) {
                    this.mSoundAliveCompat = new SoundAliveCompat(this.mMediaPlayer.getAudioSessionId());
                }
                this.mSoundAliveCompat.set3dEffectPosition(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setAudioMute(boolean z) {
        this.mMediaPlayer.semSetParameter(35004, z ? 1 : 0);
        if (this.mStreamingEnabled) {
            super.setAudioMute(z);
        }
    }

    public void setWfdTcpDisable() {
        this.mMediaPlayer.semSetParameter(2500, 1);
    }
}
