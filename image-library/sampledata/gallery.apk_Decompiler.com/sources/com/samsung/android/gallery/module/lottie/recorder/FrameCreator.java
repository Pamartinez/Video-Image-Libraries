package com.samsung.android.gallery.module.lottie.recorder;

import A.a;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FrameCreator {
    private int currentFrame = 0;
    private int currentFrameTotal = 0;
    private int durationInFrames;
    private int durationInFramesTotal;
    private w lottieDrawable;
    private final ArrayList<w> lottieDrawableList = new ArrayList<>();
    int mDrawableIdx = 0;

    public FrameCreator(w wVar) {
        this.lottieDrawable = wVar;
        int c5 = (int) wVar.d.c();
        this.durationInFrames = c5;
        this.durationInFramesTotal = c5;
    }

    public w generateFrame() {
        try {
            this.lottieDrawable.o(this.currentFrame);
            int i2 = this.currentFrame + 1;
            this.currentFrame = i2;
            this.currentFrameTotal++;
            if (i2 > this.durationInFrames) {
                Log.e("FrameCreator", "error. " + this.currentFrame + "/" + this.durationInFrames);
            }
            w wVar = this.lottieDrawable;
            if (this.currentFrame > this.durationInFrames) {
                int i7 = this.mDrawableIdx + 1;
                this.mDrawableIdx = i7;
                if (i7 < this.lottieDrawableList.size()) {
                    a.w(new StringBuilder("move next ani. "), this.mDrawableIdx, "FrameCreator");
                    w wVar2 = this.lottieDrawableList.get(this.mDrawableIdx);
                    this.lottieDrawable = wVar2;
                    this.durationInFrames = (int) wVar2.d.c();
                    this.currentFrame = 0;
                }
            }
            return wVar;
        } catch (Throwable th) {
            if (this.currentFrame > this.durationInFrames) {
                int i8 = this.mDrawableIdx + 1;
                this.mDrawableIdx = i8;
                if (i8 < this.lottieDrawableList.size()) {
                    a.w(new StringBuilder("move next ani. "), this.mDrawableIdx, "FrameCreator");
                    w wVar3 = this.lottieDrawableList.get(this.mDrawableIdx);
                    this.lottieDrawable = wVar3;
                    this.durationInFrames = (int) wVar3.d.c();
                    this.currentFrame = 0;
                }
            }
            throw th;
        }
    }

    public int getFrameCount() {
        return this.durationInFramesTotal;
    }

    public boolean hasEnded() {
        if (this.currentFrameTotal > this.durationInFramesTotal) {
            return true;
        }
        return false;
    }
}
