package com.samsung.android.gallery.support.library.abstraction;

import android.util.Log;
import c0.C0086a;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.FileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaCaptureBgmCompat {
    protected final String TAG = getClass().getSimpleName();
    protected BgmOptions mBgmOptions;

    public boolean available() {
        return false;
    }

    public void initBackgroundMusic(MediaCaptureCompat mediaCaptureCompat, BgmOptions bgmOptions) {
        this.mBgmOptions = bgmOptions;
        int size = bgmOptions.size();
        if (size <= 0 || !available()) {
            String str = this.TAG;
            StringBuilder o2 = C0086a.o(size, "initBackgroundMusic failed.{", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            o2.append(available());
            o2.append("}");
            Log.w(str, o2.toString());
            return;
        }
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
        setPlaybackRule(bgmOptions.mBodyRepeatCount, bgmOptions.mBodyLastIndex, bgmOptions.mUseOutro);
        setBgmToMediaCapture(mediaCaptureCompat);
    }

    public void release() {
        BgmOptions bgmOptions = this.mBgmOptions;
        if (bgmOptions != null) {
            bgmOptions.release();
            this.mBgmOptions = null;
        }
    }

    public void setBgmToMediaCapture(MediaCaptureCompat mediaCaptureCompat) {
    }

    public void addBody(FileDescriptor fileDescriptor, int i2, int i7) {
    }

    public void setIntro(FileDescriptor fileDescriptor, int i2, int i7) {
    }

    public void setOutro(FileDescriptor fileDescriptor, int i2, int i7) {
    }

    public void setPlaybackRule(int i2, int i7, boolean z) {
    }
}
