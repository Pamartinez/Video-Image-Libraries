package com.samsung.android.gallery.widget.listview;

import M4.f;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GallerySwipeView extends GalleryPinchView implements GestureDetector.OnGestureListener {
    private AppbarScrollListener mAppbarScrollListener;
    private GestureDetector mGestureDetector;
    private HeaderViewScrollListener mHeaderViewScrollListener;
    private SimpleGestureListener mListener;
    private boolean mOngoingAnimation = false;
    private PinchAnimationManager mSwipeAnimationManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AppbarScrollListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface HeaderViewScrollListener {
    }

    public GallerySwipeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initDetector();
    }

    private void initDetector() {
        this.mGestureDetector = new GestureDetector(getContext(), this);
    }

    private boolean isAppbarScrolling() {
        AppbarScrollListener appbarScrollListener = this.mAppbarScrollListener;
        if (appbarScrollListener == null || !((f) appbarScrollListener).f2394a.isAppbarScrolling()) {
            return false;
        }
        return true;
    }

    private boolean isHeaderViewScrolling() {
        HeaderViewScrollListener headerViewScrollListener = this.mHeaderViewScrollListener;
        if (headerViewScrollListener == null || !((f) headerViewScrollListener).f2394a.isHeaderViewScrolling()) {
            return false;
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (isAnimating() || this.mGestureDetector.onTouchEvent(motionEvent)) {
            z = true;
        } else {
            z = false;
        }
        if (!this.mOngoingAnimation) {
            z |= super.dispatchTouchEvent(motionEvent);
        } else {
            updateTouch(motionEvent);
        }
        if (z || this.mOngoingAnimation) {
            return true;
        }
        return false;
    }

    public boolean isAnimating() {
        if (this.mOngoingAnimation || super.isAnimating()) {
            return true;
        }
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        Logger.dumpLog(printWriter, "mOngoingAnimation in SwipeView : " + this.mOngoingAnimation);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
        if (motionEvent != null && motionEvent2 != null && this.mListener != null && !this.mOngoingAnimation && !isAppbarScrolling() && !isHeaderViewScrolling()) {
            float x9 = motionEvent2.getX() - motionEvent.getX();
            if (Math.abs(x9) > Math.abs(motionEvent2.getY() - motionEvent.getY()) * 2.0f && Math.abs(x9) > 100.0f && Math.abs(f) > 50.0f) {
                if (x9 > 0.0f) {
                    this.mListener.onSwipeRight(motionEvent, motionEvent2);
                } else {
                    this.mListener.onSwipeLeft(motionEvent, motionEvent2);
                }
                dispatchCancelTouchEvent(motionEvent);
                return true;
            }
        }
        return false;
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        PinchAnimationManager pinchAnimationManager = this.mSwipeAnimationManager;
        if (pinchAnimationManager != null) {
            pinchAnimationManager.onLayout();
        }
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void setAppbarScrollListener(AppbarScrollListener appbarScrollListener) {
        this.mAppbarScrollListener = appbarScrollListener;
    }

    public void setGestureListener(SimpleGestureListener simpleGestureListener) {
        this.mListener = simpleGestureListener;
    }

    public void setHeaderViewScrollListener(HeaderViewScrollListener headerViewScrollListener) {
        this.mHeaderViewScrollListener = headerViewScrollListener;
    }

    public void setOngoingAnimation(boolean z) {
        this.mOngoingAnimation = z;
    }

    public void setSwipeAnimationManager(PinchAnimationManager pinchAnimationManager) {
        this.mSwipeAnimationManager = pinchAnimationManager;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public void onShowPress(MotionEvent motionEvent) {
    }
}
