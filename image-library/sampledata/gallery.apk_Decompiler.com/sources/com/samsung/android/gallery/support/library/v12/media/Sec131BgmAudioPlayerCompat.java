package com.samsung.android.gallery.support.library.v12.media;

import N2.j;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.library.v9.media.Sec115MediaPlayerCompat;
import com.samsung.android.media.SemFragmentedBackgroundMusic;
import com.samsung.android.media.SemMediaPlayer;
import i.C0212a;
import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sec131BgmAudioPlayerCompat extends Sec115MediaPlayerCompat {
    private BgmOptions mBgmOptions;
    private final SemFragmentedBackgroundMusic mFragmentedBackgroundMusic = new SemFragmentedBackgroundMusic();
    private boolean mIsReleased;
    private boolean mMute = true;

    public Sec131BgmAudioPlayerCompat(int i2) {
        super(i2);
    }

    private int addBody(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            return this.mFragmentedBackgroundMusic.addBody(fileDescriptor, i2, i7);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "addBody failed e=" + e);
            return -1;
        }
    }

    private void closeInternal() {
        this.mFragmentedBackgroundMusic.clear();
        this.mMute = true;
        this.mIsReleased = true;
        BgmOptions bgmOptions = this.mBgmOptions;
        if (bgmOptions != null) {
            bgmOptions.release();
            this.mBgmOptions = null;
        }
    }

    private void setAudioMuteInternal(boolean z) {
        float f;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        try {
            if (isPrepared() && !this.mIsReleased) {
                setVolume(f, f);
            }
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setAudioMuteInternal failed e=" + e);
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
        this.mSemMediaPlayer.setAudioVolumeFade(i10, -1, -1, i7, i11);
    }

    private void setIntro(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            this.mFragmentedBackgroundMusic.setIntro(fileDescriptor, i2, i7);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setIntro failed e=" + e);
        }
    }

    private void setOutro(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            this.mFragmentedBackgroundMusic.setOutro(fileDescriptor, i2, i7);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setOutro failed e=" + e);
        }
    }

    private void setPlaybackRule(int i2, int i7, boolean z) {
        try {
            this.mFragmentedBackgroundMusic.setPlaybackRule(i2, i7, z);
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setPlaybackRule failed e=" + e);
        }
    }

    public boolean hasAudioTrack() {
        return true;
    }

    public void initBackgroundMusic(BgmOptions bgmOptions) {
        this.mBgmOptions = bgmOptions;
        int size = bgmOptions.size();
        if (size > 0) {
            int i2 = size - 1;
            boolean z = false;
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
            Log.d(this.TAG, "bgmPlaybackRange{0," + bgmOptions.mDuration);
            setPlaybackRule(bgmOptions.mBodyRepeatCount, bgmOptions.mBodyLastIndex, bgmOptions.mUseOutro);
            initBackgroundMusic();
            if (bgmOptions.mUseFadeOut) {
                setAudioVolumeFadeOut(!this.mMute, this.mBgmOptions.mDuration);
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("setAudioMuteInternal m=");
            sb2.append(this.mMute);
            sb2.append(",fade=");
            BgmOptions bgmOptions2 = this.mBgmOptions;
            if (bgmOptions2 != null && bgmOptions2.mUseFadeOut) {
                z = true;
            }
            sb2.append(z);
            Log.d(str, sb2.toString());
        }
    }

    public void release() {
        super.release();
        try {
            closeInternal();
        } catch (Exception e) {
            j.D(e, new StringBuilder("release failed e="), this.TAG);
        }
    }

    public void reset() {
        super.reset();
        try {
            closeInternal();
            this.mIsReleased = false;
        } catch (IllegalStateException e) {
            j.t(e, new StringBuilder("reset failed e="), this.TAG);
        }
    }

    public void seekToBgm(int i2) {
        seekTo(i2);
    }

    public void setAudioMute(boolean z) {
        this.mMute = z;
        setAudioMuteInternal(z);
    }

    public boolean setBgmPlaybackRange(int i2, int i7) {
        try {
            this.mSemMediaPlayer.setPlaybackRange(i2, i7);
            return true;
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setBgmPlaybackRange failed e=" + e);
            return false;
        }
    }

    public void setDynamicViewConfiguration(ArrayList<MediaPlayback> arrayList) {
        try {
            ArrayList arrayList2 = new ArrayList();
            Iterator<MediaPlayback> it = arrayList.iterator();
            while (it.hasNext()) {
                MediaPlayback next = it.next();
                arrayList2.add(new SemMediaPlayer.DynamicViewingConfiguration(next.startMs, next.endMs, next.speed));
            }
            this.mSemMediaPlayer.setDynamicViewingConfigurations(arrayList2);
        } catch (Exception | NoSuchMethodError e) {
            C0212a.y(e, new StringBuilder("setDynamicViewingConfiguration failed = "), this.TAG);
        }
    }

    private void initBackgroundMusic() {
        try {
            this.mSemMediaPlayer.setBackgroundMusic(this.mFragmentedBackgroundMusic);
            Log.d(this.TAG, "setBackgroundMusic");
        } catch (Exception e) {
            String str = this.TAG;
            Log.e(str, "setBackgroundMusic failed e=" + e);
        }
    }
}
