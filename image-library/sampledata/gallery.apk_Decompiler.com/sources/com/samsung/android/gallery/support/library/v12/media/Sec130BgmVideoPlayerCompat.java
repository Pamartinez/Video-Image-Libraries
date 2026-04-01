package com.samsung.android.gallery.support.library.v12.media;

import A.a;
import N2.j;
import Ua.b;
import Ua.c;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.library.v11.media.Sec120BgmVideoPlayerCompat;
import com.samsung.android.media.SemFragmentedBackgroundMusic;
import com.samsung.android.media.SemMediaPlayer;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.FileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec130BgmVideoPlayerCompat extends Sec120BgmVideoPlayerCompat {
    private BgmOptions mBgmOptions;
    protected final SemMediaPlayer mBgmPlayer;
    private boolean mBgmPrepared;
    private final SemFragmentedBackgroundMusic mFragmentedBgMusic;
    private boolean mMute = true;
    private boolean mOnError;
    private boolean mPlayerPrepared;
    private boolean mPlayerStarted;

    public Sec130BgmVideoPlayerCompat(int i2) {
        super(i2);
        SemMediaPlayer semMediaPlayer = new SemMediaPlayer();
        this.mBgmPlayer = semMediaPlayer;
        semMediaPlayer.setOnErrorListener(new b(this));
        semMediaPlayer.setOnPlaybackCompleteListener(new c(this));
        this.mFragmentedBgMusic = new SemFragmentedBackgroundMusic();
    }

    private int addBody(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            return this.mFragmentedBgMusic.addBody(fileDescriptor, i2, i7);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "addBody failed e=" + e);
            return -1;
        }
    }

    private void closeInternal() {
        this.mFragmentedBgMusic.clear();
        this.mMute = true;
        this.mOnError = false;
        this.mPlayerStarted = false;
        this.mPlayerPrepared = false;
        this.mBgmPrepared = false;
        BgmOptions bgmOptions = this.mBgmOptions;
        if (bgmOptions != null) {
            bgmOptions.release();
            this.mBgmOptions = null;
        }
    }

    private boolean isBgmPlayable() {
        if (this.mOnError || !this.mBgmPrepared || this.mPlayerStarted) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onBgmError(SemMediaPlayer semMediaPlayer, int i2, int i7) {
        Log.e(this.TAG, a.d(i2, i7, "onError {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
        this.mOnError = true;
        return true;
    }

    /* access modifiers changed from: private */
    public void onCompletion(SemMediaPlayer semMediaPlayer) {
        this.mPlayerStarted = false;
    }

    /* access modifiers changed from: private */
    public void onPlayerPrepared(SemMediaPlayer semMediaPlayer, SemMediaPlayer.TrackInfo[] trackInfoArr) {
        Log.v(this.TAG, "onPlayerPrepared");
        this.mPlayerPrepared = true;
        setAudioMuteInternal(this.mMute);
        notifyPrepared();
    }

    private void setAudioMuteInternal(boolean z) {
        float f;
        boolean z3;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (this.mPlayerPrepared) {
            BgmOptions bgmOptions = this.mBgmOptions;
            if (bgmOptions != null && bgmOptions.mUseFadeOut) {
                setAudioVolumeFadeOut(!z, bgmOptions.mDuration);
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("setAudioMuteInternal m=");
            sb2.append(z);
            sb2.append(",fade=");
            BgmOptions bgmOptions2 = this.mBgmOptions;
            if (bgmOptions2 == null || !bgmOptions2.mUseFadeOut) {
                z3 = false;
            } else {
                z3 = true;
            }
            sb2.append(z3);
            Log.d(str, sb2.toString());
            setVolume(f, f);
        }
    }

    private void setAudioVolumeFadeOut(boolean z, int i2) {
        int i7;
        int i8 = -1;
        int i10 = 0;
        if (z) {
            try {
                i7 = Math.max(i2 - 2000, 0);
            } catch (Exception e) {
                Exception exc = e;
                Log.e(this.TAG, "setAudioVolumeFade failed e=" + exc);
                return;
            }
        } else {
            i7 = -1;
        }
        if (z) {
            i8 = Math.min(i2, 2000);
        }
        int i11 = i8;
        if (z) {
            i10 = 2;
        }
        this.mBgmPlayer.setAudioVolumeFade(i10, -1, -1, i7, i11);
    }

    private void setBackgroundMusic() {
        try {
            this.mBgmPrepared = true;
            this.mBgmPlayer.setBackgroundMusic(this.mFragmentedBgMusic);
            Log.d(this.TAG, "setBackgroundMusic");
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setBackgroundMusic failed e=" + e);
        }
    }

    private void setIntro(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            this.mFragmentedBgMusic.setIntro(fileDescriptor, i2, i7);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setIntro failed e=" + e);
        }
    }

    private void setOutro(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            this.mFragmentedBgMusic.setOutro(fileDescriptor, i2, i7);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setOutro failed e=" + e);
        }
    }

    private void setPlaybackRule(int i2, int i7, boolean z) {
        try {
            this.mFragmentedBgMusic.setPlaybackRule(i2, i7, z);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setPlaybackRule failed e=" + e);
        }
    }

    public boolean hasAudioTrack() {
        return !this.mOnError;
    }

    public void initBackgroundMusic(BgmOptions bgmOptions) {
        this.mBgmOptions = bgmOptions;
        int size = bgmOptions.size();
        if (size > 0) {
            int i2 = size - 1;
            for (int i7 = 0; i7 < size; i7++) {
                BgmOptions.BgmOption bgmOption = bgmOptions.getBgmOption(i7);
                FileDescriptor fileDescriptor = bgmOption.f3141fd;
                if (fileDescriptor == null) {
                    Log.w(this.TAG, "fd is null");
                } else if (i7 == 0) {
                    setIntro(fileDescriptor, 0, bgmOption.duration);
                } else if (i7 == i2) {
                    setOutro(fileDescriptor, 0, bgmOption.duration);
                } else {
                    addBody(fileDescriptor, 0, bgmOption.duration);
                }
            }
            setPlaybackRange(0, bgmOptions.mDuration);
            Log.d(this.TAG, "bgmPlaybackRange{0," + bgmOptions.mDuration);
            setPlaybackRule(bgmOptions.mBodyRepeatCount, bgmOptions.mBodyLastIndex, bgmOptions.mUseOutro);
            setBackgroundMusic();
        }
    }

    public void notifyPrepared() {
        if (isPrepared() && this.mPlayerPrepared) {
            super.notifyPrepared();
        }
    }

    public boolean pause() {
        try {
            if (this.mBgmPlayer.isPlaying()) {
                this.mBgmPlayer.pause();
                this.mPlayerStarted = false;
            }
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("pause failed e="), this.TAG);
            onBgmError(this.mBgmPlayer, 9000007, 0);
        }
        return super.pause();
    }

    public void prepareAsync() {
        super.prepareAsync();
        initMediaPlayer(this.mBgmPlayer);
    }

    public void release() {
        super.release();
        try {
            closeInternal();
            this.mBgmPlayer.release();
        } catch (Exception e) {
            j.D(e, new StringBuilder("release failed e="), this.TAG);
        }
    }

    public void reset() {
        super.reset();
        try {
            closeInternal();
            this.mBgmPlayer.reset();
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("reset failed e="), this.TAG);
        }
    }

    public void seekToBgm(int i2) {
        try {
            this.mBgmPlayer.seekTo(i2);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "seekToBtm failed e=" + e);
        }
    }

    public void setAudioMute(boolean z) {
        super.setAudioMute(true);
        this.mMute = z;
        setAudioMuteInternal(z);
    }

    public boolean setBgmPlaybackRange(int i2, int i7) {
        try {
            this.mBgmPlayer.setPlaybackRange(i2, i7);
            return true;
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setBgmPlaybackRange failed e=" + e);
            return false;
        }
    }

    public void setLooping(boolean z) {
        super.setLooping(z);
        this.mBgmPlayer.setLooping(false);
    }

    public void setOnPreparedListener(MediaPlayerCompat.OnPreparedListener onPreparedListener) {
        super.setOnPreparedListener(onPreparedListener);
        this.mBgmPlayer.setOnInitCompleteListener(new Ua.a(this));
    }

    public void setVolume(float f, float f5) {
        try {
            this.mBgmPlayer.setVolume(f, f5);
        } catch (Exception e) {
            j.D(e, new StringBuilder("setVolume failed e="), this.TAG);
        }
    }

    public void start() {
        super.start();
        try {
            if (isBgmPlayable()) {
                this.mPlayerStarted = true;
                this.mBgmPlayer.setParameter(10973, 1);
                this.mBgmPlayer.start();
                Log.d(this.TAG, "start bgm");
            }
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("start failed e="), this.TAG);
        }
    }

    public void stop() {
        super.stop();
    }
}
