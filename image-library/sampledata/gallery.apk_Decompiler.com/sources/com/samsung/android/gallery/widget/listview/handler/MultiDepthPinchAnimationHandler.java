package com.samsung.android.gallery.widget.listview.handler;

import A.a;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.abstraction.GridSpans;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.gesture.PinchGestureDetector;
import com.samsung.android.gallery.widget.listview.GalleryPinchView;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MultiDepthPinchAnimationHandler extends AbsPinchAnimationHandler {
    private boolean mBlockPinchAnimation;
    private int mCurrentDepth;
    private boolean mDirectionBackward;
    private int mInitialDepth;
    private boolean mIsYearClickedAnimationDone;
    private int mMaxDepth;
    private int mMinDepth;
    private int mNextDepth;
    private final boolean mNormalColumnOrder;
    private float mTotalProgress;

    public MultiDepthPinchAnimationHandler(GalleryPinchView galleryPinchView) {
        super(galleryPinchView);
        this.mNormalColumnOrder = galleryPinchView.isNormalColumnOrder();
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

    private float getCurrentProgress() {
        return Math.abs(this.mTotalProgress % 1.0f);
    }

    private float getMaxProgress() {
        return ((float) (this.mMaxDepth - this.mInitialDepth)) - 0.001f;
    }

    private float getMinProgress() {
        return ((float) (this.mMinDepth - this.mInitialDepth)) + 0.001f;
    }

    private boolean isAnimationIdle() {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager == null || !pinchAnimationManager.isAnimating()) {
            return true;
        }
        return false;
    }

    private void swapDepth() {
        int i2 = this.mNextDepth;
        this.mNextDepth = this.mCurrentDepth;
        this.mCurrentDepth = i2;
    }

    private void updateAnimators(int i2) {
        this.mCurrentDepth += i2;
        int i7 = this.mNextDepth + i2;
        this.mNextDepth = i7;
        if (i7 == this.mInitialDepth) {
            swapDepth();
        }
        this.mAnimationManager.cancel();
        this.mAnimationManager.clear();
        try {
            PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
            int i8 = this.mCurrentDepth;
            int i10 = this.mMinDepth;
            pinchAnimationManager.updateAnimator(i8 - i10, this.mNextDepth - i10);
        } catch (ArrayIndexOutOfBoundsException unused) {
            Log.e(this.TAG, "Wrong depth is calculated [" + (this.mCurrentDepth - this.mMinDepth) + "][" + (this.mNextDepth - this.mMinDepth) + "]");
            this.mNextDepth = Math.max(0, this.mNextDepth + -1);
            finishAnimation();
        }
    }

    private int updateNextProgress(float f) {
        if (this.mNormalColumnOrder) {
            f *= -1.0f;
        }
        float minProgress = getMinProgress();
        float maxProgress = getMaxProgress();
        float f5 = this.mTotalProgress;
        float clamp = MathUtils.clamp(this.mTotalProgress + MathUtils.clamp(f / (((float) this.mListView.getWidth()) / 2.5f), -0.2f, 0.2f), minProgress, maxProgress);
        this.mTotalProgress = clamp;
        boolean z = true;
        if (clamp <= 0.0f ? f5 >= clamp : clamp >= f5) {
            z = false;
        }
        this.mDirectionBackward = z;
        if (f5 == 0.0f) {
            return 0;
        }
        return (int) (Math.floor((double) clamp) - Math.floor((double) f5));
    }

    public void endYearOrMonthForViewingAnimation() {
        this.mIsYearClickedAnimationDone = true;
        super.endYearOrMonthForViewingAnimation();
    }

    public void finishAnimation() {
        super.finishAnimation();
        if (this.mNextDepth == this.mInitialDepth) {
            this.mListView.checkPreviewCandidateAsync();
        }
        this.mIsYearClickedAnimationDone = false;
    }

    public boolean isAnimationAvailable() {
        if (this.mIsYearClickedAnimationDone || this.mNextDepth != this.mInitialDepth) {
            return true;
        }
        return false;
    }

    public boolean needRecoverColumn() {
        if (this.mAnimationManager.getAnimationProgress() < 0.2f) {
            return true;
        }
        if (!this.mDirectionBackward || this.mAnimationManager.getAnimationProgress() >= 0.8f) {
            return false;
        }
        return true;
    }

    public boolean onScale(float f, PinchGestureDetector pinchGestureDetector, int i2, int i7) {
        if (this.mBlockPinchAnimation) {
            return true;
        }
        if (this.mAnimationManager.isEmpty()) {
            prepareAnimation(pinchGestureDetector, i2, i7);
            this.mBlockPinchAnimation = this.mAnimationManager.isEmpty();
        } else {
            int updateNextProgress = updateNextProgress(f);
            if (updateNextProgress != 0) {
                updateAnimators(updateNextProgress);
            }
            this.mAnimationManager.setAnimationProgress(getCurrentProgress());
        }
        return true;
    }

    public void onScaleEnd() {
        super.onScaleEnd();
        this.mBlockPinchAnimation = false;
    }

    public void prepareAnimation(PinchGestureDetector pinchGestureDetector, int i2, int i7) {
        if (isAnimationIdle() && availableOnScale() && pinchGestureDetector.hasPinchDirection()) {
            prepareAnimation(pinchGestureDetector.isPinchDirectionIn(), i2, i7);
        }
    }

    public void recoverColumn() {
        this.mNextDepth = this.mCurrentDepth;
    }

    public String toString() {
        return "PinchAH{" + this.mAnimationManager.size() + "}";
    }

    public void updateAnimation() {
        this.mAnimationManager.setAnimationProgressOnly(getCurrentProgress());
        super.updateAnimation();
    }

    public void updateSpanCount() {
        if (this.mIsYearClickedAnimationDone) {
            this.mListView.setScale(false);
        } else {
            this.mListView.changeDepth(this.mNextDepth);
        }
    }

    private void prepareAnimation(boolean z, int i2, int i7) {
        Log.d(this.TAG, "prepareAnimation", Integer.valueOf(this.mListView.getChildCount()), Integer.valueOf(i2), Integer.valueOf(i7));
        if (this.mListView.getChildCount() > 0 && this.mPinchAnimationManager != null) {
            int[] columns = this.mListView.getColumns();
            int depth = this.mListView.getDepth();
            int nextGridDepth = getNextGridDepth(z);
            int i8 = columns[nextGridDepth];
            GridSpans gridSpans = this.mListView.getGridSpans();
            int[] selectableRange = this.mListView.isSelectionMode() ? gridSpans.selectableRange() : new int[]{0, gridSpans.indexMax()};
            int i10 = selectableRange[0];
            int i11 = selectableRange[1];
            int i12 = columns[depth];
            int spanMax = gridSpans.spanMax();
            this.mNextDepth = nextGridDepth;
            this.mMinDepth = i10;
            this.mMaxDepth = i11;
            this.mInitialDepth = depth;
            this.mCurrentDepth = depth;
            this.mTotalProgress = 0.0f;
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("prepareAnimation");
            sb2.append(Logger.v(a.d(depth, i12, "cur=[", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "]"), a.d(nextGridDepth, i8, "next=[", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "]"), "start=" + this.mMinDepth, "end=" + this.mMaxDepth, C0086a.i(spanMax, "max=")));
            sb2.append(" ");
            sb2.append(StringCompat.toString(columns));
            Log.d(str, sb2.toString());
            this.mPinchAnimationManager.onPrepareAnimation(i12, i8, spanMax);
        }
    }
}
