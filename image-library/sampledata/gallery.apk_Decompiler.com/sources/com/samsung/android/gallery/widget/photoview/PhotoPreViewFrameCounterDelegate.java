package com.samsung.android.gallery.widget.photoview;

import A.a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoPreViewFrameCounterDelegate {
    public final StringCompat TAG;
    private FrameCounterImpl mFrameCounter;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FrameCounterImpl {
        int mFrameCounter = 0;
        long mLastFrameTime;
        final StringCompat mTag;

        public FrameCounterImpl(StringCompat stringCompat) {
            this.mTag = stringCompat;
        }

        public void exec() {
            this.mFrameCounter++;
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = this.mLastFrameTime;
            long j3 = 0;
            if (j2 > 0) {
                j3 = currentTimeMillis - j2;
            }
            if (j3 > 30) {
                Log.e((CharSequence) this.mTag, a.f("frame-drop suspected for EnterTransition. dur=", j3), Integer.valueOf(this.mFrameCounter));
            }
            this.mLastFrameTime = currentTimeMillis;
        }
    }

    public PhotoPreViewFrameCounterDelegate(StringCompat stringCompat) {
        this.TAG = stringCompat;
        this.mFrameCounter = new FrameCounterImpl(stringCompat);
    }

    public void execFrameCounter() {
        FrameCounterImpl frameCounterImpl = this.mFrameCounter;
        if (frameCounterImpl != null) {
            frameCounterImpl.exec();
        }
    }

    public int getFrameCount() {
        int i2 = this.mFrameCounter.mFrameCounter;
        this.mFrameCounter = null;
        return i2;
    }
}
