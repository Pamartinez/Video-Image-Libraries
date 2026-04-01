package com.samsung.android.gallery.widget.gesture;

import android.view.MotionEvent;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.animations.e;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RotationGestureDetector {
    private OnRotationGestureListener mListener;
    private final TouchInfo[] mTouchInfo = new TouchInfo[2];

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TouchInfo {
        /* access modifiers changed from: private */
        public final int mActionIndex;
        /* access modifiers changed from: private */
        public float mX;
        /* access modifiers changed from: private */
        public float mY;

        public TouchInfo(MotionEvent motionEvent) {
            this.mActionIndex = motionEvent.getActionIndex();
            setTouchInfo(motionEvent);
        }

        public void setTouchInfo(MotionEvent motionEvent) {
            this.mX = motionEvent.getX(this.mActionIndex);
            this.mY = motionEvent.getY(this.mActionIndex);
        }
    }

    private float ClipAngleTo0_360(float f) {
        return f % 360.0f;
    }

    private float findAngleDelta(float f, float f5) {
        float ClipAngleTo0_360 = ClipAngleTo0_360(f) - ClipAngleTo0_360(f5);
        if (ClipAngleTo0_360 < -180.0f) {
            return ClipAngleTo0_360 + 360.0f;
        }
        if (ClipAngleTo0_360 > 180.0f) {
            return ClipAngleTo0_360 - 360.0f;
        }
        return ClipAngleTo0_360;
    }

    private float getAngle() {
        return (float) Math.toDegrees(Math.atan2((double) getYDelta(), (double) getXDelta()));
    }

    private float getXDelta() {
        return this.mTouchInfo[1].mX - this.mTouchInfo[0].mX;
    }

    private float getYDelta() {
        return this.mTouchInfo[1].mY - this.mTouchInfo[0].mY;
    }

    private boolean isValidEvent(MotionEvent motionEvent, int i2) {
        if (motionEvent.getPointerId(i2) != -1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.util.function.Predicate, java.lang.Object] */
    public void onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        TouchInfo touchInfo = null;
        if (actionMasked == 0) {
            TouchInfo[] touchInfoArr = this.mTouchInfo;
            if (isValidEvent(motionEvent, 0)) {
                touchInfo = new TouchInfo(motionEvent);
            }
            touchInfoArr[0] = touchInfo;
        } else if (actionMasked == 1) {
            this.mTouchInfo[0] = null;
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                TouchInfo[] touchInfoArr2 = this.mTouchInfo;
                touchInfoArr2[1] = null;
                touchInfoArr2[0] = null;
            } else if (actionMasked == 5) {
                TouchInfo[] touchInfoArr3 = this.mTouchInfo;
                if (isValidEvent(motionEvent, motionEvent.getActionIndex())) {
                    touchInfo = new TouchInfo(motionEvent);
                }
                touchInfoArr3[1] = touchInfo;
            } else if (actionMasked == 6) {
                this.mTouchInfo[1] = null;
            }
        } else if (!Arrays.stream(this.mTouchInfo).allMatch(new Object())) {
        } else {
            if (motionEvent.getPointerCount() > 1) {
                OnRotationGestureListener onRotationGestureListener = this.mListener;
                if (onRotationGestureListener != null) {
                    ((e) onRotationGestureListener).a(findAngleDelta(getAngle(), getAngle(motionEvent)));
                }
                try {
                    Arrays.stream(this.mTouchInfo).iterator().forEachRemaining(new b(motionEvent));
                } catch (IllegalArgumentException unused) {
                    Log.w((CharSequence) "RotationGestureDetector", "setTouchInfo fail ", Integer.valueOf(motionEvent.getPointerCount()));
                }
            } else {
                Log.w((CharSequence) "RotationGestureDetector", "wrong touch event -> ", Integer.valueOf(motionEvent.getPointerCount()));
            }
        }
    }

    public void setListener(OnRotationGestureListener onRotationGestureListener) {
        this.mListener = onRotationGestureListener;
    }

    private float getAngle(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        if (this.mTouchInfo[0].mActionIndex < pointerCount && this.mTouchInfo[1].mActionIndex < pointerCount) {
            return (float) Math.toDegrees(Math.atan2((double) getYDelta(motionEvent), (double) getXDelta(motionEvent)));
        }
        Log.w("RotationGestureDetector", "actionIndex is bigger than pointerCount" + Logger.v(Integer.valueOf(this.mTouchInfo[0].mActionIndex), Integer.valueOf(this.mTouchInfo[1].mActionIndex), Integer.valueOf(pointerCount)));
        return 0.0f;
    }

    private float getXDelta(MotionEvent motionEvent) {
        return motionEvent.getX(this.mTouchInfo[1].mActionIndex) - motionEvent.getX(this.mTouchInfo[0].mActionIndex);
    }

    private float getYDelta(MotionEvent motionEvent) {
        return motionEvent.getY(this.mTouchInfo[1].mActionIndex) - motionEvent.getY(this.mTouchInfo[0].mActionIndex);
    }
}
