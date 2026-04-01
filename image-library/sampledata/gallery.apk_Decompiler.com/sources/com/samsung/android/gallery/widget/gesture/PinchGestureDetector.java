package com.samsung.android.gallery.widget.gesture;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchGestureDetector implements ScaleGestureDetector.OnScaleGestureListener {
    private float mCurrentSpan;
    private ScaleGestureDetector mDetector;
    private float mFirstSpan;
    private IPinchGestureListener mListener;
    private boolean mOverTouchSlop;
    private DIRECTION mPinchDirection;
    private long mPinchStartTime;
    private float mPrevSpan;
    private int mTouchSlop;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DIRECTION {
        DIRECTION_NONE,
        DIRECTION_PINCH_IN,
        DIRECTION_PINCH_OUT
    }

    public PinchGestureDetector(Context context) {
        this(context, (IPinchGestureListener) null);
    }

    private DIRECTION getPinchDirection(ScaleGestureDetector scaleGestureDetector) {
        DIRECTION direction;
        boolean z;
        DIRECTION direction2 = DIRECTION.DIRECTION_NONE;
        float f = this.mCurrentSpan;
        float currentSpan = setCurrentSpan(scaleGestureDetector, f);
        this.mCurrentSpan = currentSpan;
        if (Math.abs(currentSpan - this.mPrevSpan) > 1.0f) {
            this.mPrevSpan = f;
            float f5 = this.mCurrentSpan;
            if (f > f5) {
                direction = DIRECTION.DIRECTION_PINCH_IN;
            } else if (f < f5) {
                direction = DIRECTION.DIRECTION_PINCH_OUT;
            } else {
                direction = direction2;
            }
            IPinchGestureListener iPinchGestureListener = this.mListener;
            if (iPinchGestureListener != null) {
                if (direction == DIRECTION.DIRECTION_PINCH_IN) {
                    z = true;
                } else {
                    z = false;
                }
                if (!iPinchGestureListener.canPinch(z)) {
                    return direction2;
                }
            }
            return direction;
        }
        return direction2;
    }

    private float setCurrentSpan(ScaleGestureDetector scaleGestureDetector, float f) {
        int i2;
        float currentSpan = scaleGestureDetector.getCurrentSpan();
        float f5 = currentSpan - f;
        if (Math.abs(f5) <= 50.0f) {
            return currentSpan;
        }
        if (f5 < 0.0f) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        return (((float) i2) * 50.0f) + f;
    }

    public boolean hasPinchDirection() {
        if (this.mPinchDirection != DIRECTION.DIRECTION_NONE) {
            return true;
        }
        return false;
    }

    public boolean isPinchDirectionIn() {
        if (this.mPinchDirection == DIRECTION.DIRECTION_PINCH_IN) {
            return true;
        }
        return false;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        boolean z;
        IPinchGestureListener iPinchGestureListener;
        long eventTime = scaleGestureDetector.getEventTime() - this.mPinchStartTime;
        float currentSpan = scaleGestureDetector.getCurrentSpan();
        float f = this.mCurrentSpan;
        float f5 = currentSpan - f;
        float currentSpan2 = setCurrentSpan(scaleGestureDetector, f);
        float f8 = currentSpan2 - this.mPrevSpan;
        float f10 = currentSpan2 - this.mFirstSpan;
        if (!this.mOverTouchSlop && Math.abs(f10) > ((float) this.mTouchSlop)) {
            this.mOverTouchSlop = true;
        }
        if (Math.abs(f8) > 1.0f) {
            z = true;
        } else {
            z = false;
        }
        if (this.mOverTouchSlop && z) {
            this.mPinchDirection = getPinchDirection(scaleGestureDetector);
            if (eventTime >= 1 && (iPinchGestureListener = this.mListener) != null) {
                iPinchGestureListener.onScale(f5);
            }
        }
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        IPinchGestureListener iPinchGestureListener = this.mListener;
        if (iPinchGestureListener != null) {
            if (!iPinchGestureListener.onScaleBegin(new float[]{scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY()})) {
                return false;
            }
        }
        this.mOverTouchSlop = false;
        this.mPinchStartTime = scaleGestureDetector.getEventTime();
        float currentSpan = scaleGestureDetector.getCurrentSpan();
        this.mCurrentSpan = currentSpan;
        this.mPrevSpan = currentSpan;
        this.mFirstSpan = currentSpan;
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        this.mPinchStartTime = 0;
        this.mCurrentSpan = 0.0f;
        this.mPrevSpan = 0.0f;
        IPinchGestureListener iPinchGestureListener = this.mListener;
        if (iPinchGestureListener != null) {
            iPinchGestureListener.onScaleEnd();
        }
        this.mPinchDirection = DIRECTION.DIRECTION_NONE;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mDetector.onTouchEvent(motionEvent);
    }

    public PinchGestureDetector(Context context, IPinchGestureListener iPinchGestureListener) {
        this.mPinchStartTime = 0;
        this.mPrevSpan = 1.0f;
        this.mCurrentSpan = 1.0f;
        this.mFirstSpan = 1.0f;
        this.mOverTouchSlop = false;
        this.mPinchDirection = DIRECTION.DIRECTION_NONE;
        this.mListener = iPinchGestureListener;
        ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(context, this);
        this.mDetector = scaleGestureDetector;
        scaleGestureDetector.setQuickScaleEnabled(false);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
}
