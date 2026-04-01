package com.samsung.android.gallery.widget.listview.handler;

import N2.j;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.gesture.PinchGestureDetector;
import com.samsung.android.gallery.widget.listview.GalleryPinchView;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDepthPinchAnimationHandler extends AbsPinchAnimationHandler {
    private boolean mPreparingAnimation;
    protected ScaleType mScaleType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ScaleType {
        NONE,
        DEC,
        INC
    }

    public OneDepthPinchAnimationHandler(GalleryPinchView galleryPinchView) {
        super(galleryPinchView);
    }

    private boolean availableOnScale() {
        int findLastVisibleItemPositionCompat = this.mListView.findLastVisibleItemPositionCompat();
        if (findLastVisibleItemPositionCompat > 1) {
            return true;
        }
        boolean isData = this.mListView.isData(findLastVisibleItemPositionCompat);
        String str = this.TAG;
        Log.d(str, "availableOnScale() : " + findLastVisibleItemPositionCompat + ArcCommonLog.TAG_COMMA + isData);
        return isData;
    }

    private void createPinchEffect(int i2, int i7) {
        String str = this.TAG;
        Log.d(str, "createPinchEffect : " + this.mListView.getChildCount());
        if (this.mListView.getChildCount() > 0) {
            boolean z = true;
            this.mPreparingAnimation = true;
            if (this.mScaleType == ScaleType.DEC) {
                z = false;
            }
            prepareAnimation(z, i2, i7);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        if (r3 < -0.2f) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private float getNextProgress(float r3) {
        /*
            r2 = this;
            com.samsung.android.gallery.widget.listview.GalleryPinchView r0 = r2.mListView
            int r0 = r0.getWidth()
            float r0 = (float) r0
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            float r3 = r3 / r0
            com.samsung.android.gallery.widget.animator.AnimationManager r0 = r2.mAnimationManager
            float r0 = r0.getAnimationProgress()
            com.samsung.android.gallery.widget.listview.handler.OneDepthPinchAnimationHandler$ScaleType r2 = r2.mScaleType
            com.samsung.android.gallery.widget.listview.handler.OneDepthPinchAnimationHandler$ScaleType r1 = com.samsung.android.gallery.widget.listview.handler.OneDepthPinchAnimationHandler.ScaleType.INC
            if (r2 != r1) goto L_0x001a
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r3 = r3 * r2
        L_0x001a:
            r2 = 1045220557(0x3e4ccccd, float:0.2)
            int r1 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0023
        L_0x0021:
            r3 = r2
            goto L_0x002b
        L_0x0023:
            r2 = -1102263091(0xffffffffbe4ccccd, float:-0.2)
            int r1 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r1 >= 0) goto L_0x002b
            goto L_0x0021
        L_0x002b:
            float r0 = r0 + r3
            r2 = 981668463(0x3a83126f, float:0.001)
            float r2 = java.lang.Math.max(r2, r0)
            r3 = 1065353216(0x3f800000, float:1.0)
            float r2 = java.lang.Math.min(r3, r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.listview.handler.OneDepthPinchAnimationHandler.getNextProgress(float):float");
    }

    private boolean isAnimationIdle() {
        if (this.mPreparingAnimation) {
            return false;
        }
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager == null || !pinchAnimationManager.isAnimating()) {
            return true;
        }
        return false;
    }

    private void resetScaleType() {
        this.mScaleType = ScaleType.NONE;
    }

    private void setScaleType(boolean z) {
        ScaleType scaleType;
        if (z) {
            scaleType = ScaleType.INC;
        } else {
            scaleType = ScaleType.DEC;
        }
        this.mScaleType = scaleType;
    }

    public void endYearOrMonthForViewingAnimation() {
        setScaleType(false);
        super.endYearOrMonthForViewingAnimation();
    }

    public void finishAnimation() {
        super.finishAnimation();
        resetScaleType();
    }

    public boolean isAnimationAvailable() {
        if (this.mScaleType != ScaleType.NONE) {
            return true;
        }
        return false;
    }

    public boolean needRecoverColumn() {
        if (this.mAnimationManager.getAnimationProgress() < 0.09f) {
            return true;
        }
        return false;
    }

    public boolean onScale(float f, PinchGestureDetector pinchGestureDetector, int i2, int i7) {
        if (this.mAnimationManager.isEmpty()) {
            Log.d(this.TAG, "onScale prepare");
            prepareAnimation(pinchGestureDetector, i2, i7);
            return true;
        }
        this.mAnimationManager.setAnimationProgress(getNextProgress(f));
        return true;
    }

    public void prepareAnimation(PinchGestureDetector pinchGestureDetector, int i2, int i7) {
        if (isAnimationIdle() && availableOnScale() && pinchGestureDetector.hasPinchDirection()) {
            setScaleType(pinchGestureDetector.isPinchDirectionIn());
            createPinchEffect(i2, i7);
        }
    }

    public void recoverColumn() {
        resetScaleType();
    }

    public void startAnimation() {
        super.startAnimation();
        this.mPreparingAnimation = false;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("PinchAH{");
        sb2.append(this.mAnimationManager.size());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return j.h(sb2, this.mPreparingAnimation, "}");
    }

    public void updateSpanCount() {
        boolean z;
        GalleryPinchView galleryPinchView = this.mListView;
        if (this.mScaleType != ScaleType.DEC) {
            z = true;
        } else {
            z = false;
        }
        galleryPinchView.setScale(z);
    }

    private void prepareAnimation(boolean z, int i2, int i7) {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            pinchAnimationManager.onPrepareAnimation(i2, getNextGrid(z), i7);
        }
    }
}
