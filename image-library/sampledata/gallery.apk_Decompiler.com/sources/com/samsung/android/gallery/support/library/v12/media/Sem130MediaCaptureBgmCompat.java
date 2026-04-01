package com.samsung.android.gallery.support.library.v12.media;

import N2.j;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureBgmCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.media.SemFragmentedBackgroundMusic;
import com.samsung.android.media.mediacapture.SemMediaCapture;
import java.io.FileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem130MediaCaptureBgmCompat extends MediaCaptureBgmCompat {
    private SemFragmentedBackgroundMusic mSemFragmentedBackgroundMusic;

    public Sem130MediaCaptureBgmCompat() {
        Log.d(this.TAG, "create");
        try {
            this.mSemFragmentedBackgroundMusic = new SemFragmentedBackgroundMusic();
        } catch (Exception e) {
            j.D(e, new StringBuilder("init fail="), this.TAG);
        }
    }

    public void addBody(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            this.mSemFragmentedBackgroundMusic.addBody(fileDescriptor, i2, i7);
        } catch (Exception e) {
            j.D(e, new StringBuilder("addBody fail="), this.TAG);
        }
    }

    public boolean available() {
        if (this.mSemFragmentedBackgroundMusic != null) {
            return true;
        }
        return false;
    }

    public void setBgmToMediaCapture(MediaCaptureCompat mediaCaptureCompat) {
        try {
            ((SemMediaCapture) mediaCaptureCompat.getMediaCapture()).setBackgroundMusic(this.mSemFragmentedBackgroundMusic);
        } catch (Exception e) {
            j.D(e, new StringBuilder("setBgmToMediaCapture fail="), this.TAG);
        }
    }

    public void setIntro(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            this.mSemFragmentedBackgroundMusic.setIntro(fileDescriptor, i2, i7);
        } catch (Exception e) {
            j.D(e, new StringBuilder("setIntro fail="), this.TAG);
        }
    }

    public void setOutro(FileDescriptor fileDescriptor, int i2, int i7) {
        try {
            this.mSemFragmentedBackgroundMusic.setOutro(fileDescriptor, i2, i7);
        } catch (Exception e) {
            j.D(e, new StringBuilder("setOutro fail="), this.TAG);
        }
    }

    public void setPlaybackRule(int i2, int i7, boolean z) {
        try {
            this.mSemFragmentedBackgroundMusic.setPlaybackRule(i2, i7, z);
        } catch (Exception e) {
            j.D(e, new StringBuilder("setPlaybackRule fail="), this.TAG);
        }
    }
}
