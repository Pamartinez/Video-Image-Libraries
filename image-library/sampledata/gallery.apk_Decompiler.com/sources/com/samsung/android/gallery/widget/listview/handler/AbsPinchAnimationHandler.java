package com.samsung.android.gallery.widget.listview.handler;

import D7.g;
import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.AnimationManager;
import com.samsung.android.gallery.widget.gesture.PinchGestureDetector;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryPinchView;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationInterface;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsPinchAnimationHandler implements PinchAnimationInterface {
    protected final String TAG = getClass().getSimpleName();
    protected final AnimationManager mAnimationManager;
    private boolean mIsColumnRecovered;
    protected final GalleryPinchView mListView;
    protected PinchAnimationManager mPinchAnimationManager;

    public AbsPinchAnimationHandler(GalleryPinchView galleryPinchView) {
        AnimationManager animationManager = new AnimationManager();
        this.mAnimationManager = animationManager;
        this.mListView = galleryPinchView;
        animationManager.setAnimationListener(new AnimationManager.AnimationListener() {
            public void onAnimationCancel() {
                Log.d(AbsPinchAnimationHandler.this.TAG, "onAnimationCancel");
                AbsPinchAnimationHandler.this.finishAnimation();
            }

            public void onAnimationEnd() {
                Log.d(AbsPinchAnimationHandler.this.TAG, "onAnimationEnd");
                AbsPinchAnimationHandler.this.finishAnimation();
            }

            public void onAnimationStart() {
                Log.d(AbsPinchAnimationHandler.this.TAG, "onPinchAnimStart");
                AbsPinchAnimationHandler.this.onPinchAnimStart();
            }
        });
    }

    private void onPinchAnimCompleted(boolean z) {
        if (this.mPinchAnimationManager != null && this.mListView.getAdapter() != null) {
            this.mPinchAnimationManager.onAnimationCompleted(z, this.mIsColumnRecovered);
        }
    }

    private void setScrollPosition() {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            int[] scrollPositionAndOffset = pinchAnimationManager.getScrollPositionAndOffset();
            this.mListView.scrollToPositionWithOffset(scrollPositionAndOffset[0], scrollPositionAndOffset[1]);
        }
    }

    public void completePinchAnimation() {
        this.mListView.completePinchAnimation();
    }

    public void endYearOrMonthForViewingAnimation() {
        this.mAnimationManager.onAnimationEnd((Animator) null);
    }

    public void finishAnimation() {
        this.mAnimationManager.clear();
        onPinchAnimCompleted(isAnimationAvailable());
        if (isAnimationAvailable()) {
            updateSpanCount();
            setScrollPosition();
            onGridChanged();
        }
        ThreadUtil.postOnUiThread(new g(23, this));
        this.mIsColumnRecovered = false;
    }

    public int[] getActiveColumns() {
        return this.mListView.getActiveColumns();
    }

    public float[] getFocusXY() {
        return this.mListView.getFocusedXY();
    }

    public int getNextGrid(boolean z) {
        return this.mListView.getNextGrid(z);
    }

    public int getNextGridDepth(boolean z) {
        return this.mListView.getNextGridDepth(z);
    }

    public RecyclerView.ViewHolder getViewHolder(View view) {
        return this.mListView.getChildViewHolder(view);
    }

    public boolean isAnimating() {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager == null || !pinchAnimationManager.isAnimating()) {
            return false;
        }
        return true;
    }

    public abstract boolean isAnimationAvailable();

    public abstract boolean needRecoverColumn();

    public void onDump(PrintWriter printWriter, String str) {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            pinchAnimationManager.onDump(printWriter, str);
        }
    }

    public void onGridChanged() {
        this.mListView.onGridChanged();
    }

    public void onLayout() {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            pinchAnimationManager.onLayout();
        }
    }

    public void onPinchAnimStart() {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            pinchAnimationManager.onAnimationStarted();
        }
    }

    public abstract boolean onScale(float f, PinchGestureDetector pinchGestureDetector, int i2, int i7);

    public void onScaleEnd() {
        if (this.mAnimationManager.isEmpty()) {
            ThreadUtil.postOnUiThread(new g(23, this));
            return;
        }
        boolean needRecoverColumn = needRecoverColumn();
        this.mIsColumnRecovered = needRecoverColumn;
        if (needRecoverColumn) {
            recoverColumn();
        }
        this.mAnimationManager.playLayoutAnimation(this.mIsColumnRecovered);
    }

    public abstract void recoverColumn();

    public void setPinchAnimationManager(PinchAnimationManager pinchAnimationManager) {
        this.mPinchAnimationManager = pinchAnimationManager;
    }

    public void startAnimation() {
        this.mAnimationManager.setAnimationProgress(0.0f);
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            this.mAnimationManager.addAnimations(pinchAnimationManager.getPropertyAnimators());
        }
    }

    public void startMonthForViewingClickedAnimation(int i2, RectF rectF) {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            pinchAnimationManager.startMonthForViewingClickedAnimation(i2, rectF);
        }
    }

    public void startYearClickedAnimation(int i2, RectF rectF) {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            pinchAnimationManager.startYearClickedAnimation(i2, rectF);
        } else {
            Log.w(this.TAG, "startYearClickedAnimation failed");
        }
    }

    public void updateAnimation() {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            this.mAnimationManager.addAnimations(pinchAnimationManager.getPropertyAnimators());
        }
    }

    public abstract void updateSpanCount();

    public GalleryListView getRecyclerView() {
        return this.mListView;
    }

    public void startMonthForViewingClickedAnimation(int i2, float f, float f5) {
        PinchAnimationManager pinchAnimationManager = this.mPinchAnimationManager;
        if (pinchAnimationManager != null) {
            pinchAnimationManager.startMonthForViewingClickedAnimation(i2, f, f5);
        } else {
            Log.w(this.TAG, "startMonthForViewingClickedAnimation failed");
        }
    }
}
