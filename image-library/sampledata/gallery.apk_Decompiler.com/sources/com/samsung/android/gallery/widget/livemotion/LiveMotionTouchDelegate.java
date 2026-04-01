package com.samsung.android.gallery.widget.livemotion;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.InterceptTouchListener;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveMotionTouchDelegate implements InterceptTouchListener, ScaleGestureDetector.OnScaleGestureListener {
    private boolean mIsMultiTouch = false;
    private boolean mIsNeedToUpdateLastTouch = false;
    private boolean mIsTouchMode = false;
    private final OnTouchEventListener mListener;
    private final ScaleGestureDetector mScaleGestureDetector;
    private float mTouchDownPoint = -1.0f;
    private long mTouchTimeTick = Long.MIN_VALUE;
    private final LiveMotionViewPager mViewPager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnTouchEventListener {
        void isTouchUpOrCancel();

        void onClicked(float f);

        boolean onMove(float f, float f5);

        void onScale(ScaleGestureDetector scaleGestureDetector);

        boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector);

        void updateLastTouch(float f, float f5);
    }

    public LiveMotionTouchDelegate(Context context, OnTouchEventListener onTouchEventListener) {
        this.mViewPager = (LiveMotionViewPager) onTouchEventListener;
        this.mListener = onTouchEventListener;
        this.mScaleGestureDetector = new ScaleGestureDetector(context, this);
    }

    private boolean isValidClicked() {
        if (this.mTouchDownPoint == -1.0f || System.currentTimeMillis() - this.mTouchTimeTick > 300) {
            return false;
        }
        return true;
    }

    public boolean isInTouchMode() {
        return this.mIsTouchMode;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OnTouchEventListener onTouchEventListener;
        OnTouchEventListener onTouchEventListener2;
        this.mScaleGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction() & ScoverState.TYPE_NFC_SMART_COVER;
        if (action != 0) {
            if (action == 1) {
                Log.d("LiveMotionTouchDelegate", "ACTION_UP : " + motionEvent.getX());
                if (isValidClicked() && (onTouchEventListener = this.mListener) != null) {
                    onTouchEventListener.onClicked(this.mTouchDownPoint);
                }
            } else if (action != 2) {
                if (action != 3) {
                    if (action == 5) {
                        this.mIsMultiTouch = true;
                        Log.d("LiveMotionTouchDelegate", "ACTION_POINTER_DOWN");
                    } else if (action == 6) {
                        this.mIsNeedToUpdateLastTouch = true;
                    }
                }
            } else if (this.mScaleGestureDetector.isInProgress()) {
                return true;
            } else {
                if (!this.mIsNeedToUpdateLastTouch || (onTouchEventListener2 = this.mListener) == null) {
                    OnTouchEventListener onTouchEventListener3 = this.mListener;
                    if (onTouchEventListener3 != null && onTouchEventListener3.onMove(motionEvent.getX(), motionEvent.getY())) {
                        return true;
                    }
                    if (this.mIsMultiTouch && !this.mViewPager.isSwipeMode()) {
                        return true;
                    }
                    if (this.mTouchDownPoint != -1.0f && Math.abs(motionEvent.getX() - this.mTouchDownPoint) > 30.0f) {
                        this.mTouchDownPoint = -1.0f;
                        this.mTouchTimeTick = Long.MIN_VALUE;
                        this.mViewPager.setSwipeMode();
                        this.mViewPager.requestDisallowInterceptTouchEvent(true);
                        Log.d("LiveMotionTouchDelegate", "ACTION_MOVE : setSwipeMode = " + motionEvent.getX());
                    }
                } else {
                    onTouchEventListener2.updateLastTouch(motionEvent.getX(), motionEvent.getY());
                    this.mIsNeedToUpdateLastTouch = false;
                    return true;
                }
            }
            this.mTouchTimeTick = Long.MIN_VALUE;
            this.mTouchDownPoint = -1.0f;
            this.mIsMultiTouch = false;
            this.mIsTouchMode = false;
            this.mViewPager.requestDisallowInterceptTouchEvent(false);
            OnTouchEventListener onTouchEventListener4 = this.mListener;
            if (onTouchEventListener4 != null) {
                onTouchEventListener4.isTouchUpOrCancel();
            }
        } else {
            this.mTouchDownPoint = motionEvent.getX();
            this.mTouchTimeTick = System.currentTimeMillis();
            this.mIsTouchMode = true;
            Log.d("LiveMotionTouchDelegate", "ACTION_DOWN : " + this.mTouchDownPoint);
        }
        return false;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        OnTouchEventListener onTouchEventListener = this.mListener;
        if (onTouchEventListener == null) {
            return false;
        }
        onTouchEventListener.onScale(scaleGestureDetector);
        return false;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        OnTouchEventListener onTouchEventListener = this.mListener;
        if (onTouchEventListener == null || !onTouchEventListener.onScaleBegin(scaleGestureDetector)) {
            return false;
        }
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }
}
