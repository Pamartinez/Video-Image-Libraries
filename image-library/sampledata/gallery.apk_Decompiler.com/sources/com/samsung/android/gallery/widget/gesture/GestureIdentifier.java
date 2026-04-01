package com.samsung.android.gallery.widget.gesture;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GestureIdentifier {
    private final GestureDetector mGestureDetector;
    private final ScaleGestureDetector mScaleGestureDetector;
    private final SimpleScaleGestureListener mScaleGestureListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimpleScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        boolean mIsHandled;
        final ScaleGestureDetector.OnScaleGestureListener mListener;

        public SimpleScaleGestureListener(ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener) {
            this.mListener = onScaleGestureListener;
        }

        private boolean setHandled(boolean z) {
            this.mIsHandled = z;
            return z;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            return setHandled(this.mListener.onScale(scaleGestureDetector));
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return setHandled(this.mListener.onScaleBegin(scaleGestureDetector));
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            this.mListener.onScaleEnd(scaleGestureDetector);
            setHandled(false);
        }
    }

    public GestureIdentifier(Context context, SimpleGestureListener simpleGestureListener) {
        GestureDetector gestureDetector = new GestureDetector(context, simpleGestureListener);
        this.mGestureDetector = gestureDetector;
        gestureDetector.setOnDoubleTapListener(simpleGestureListener);
        SimpleScaleGestureListener simpleScaleGestureListener = new SimpleScaleGestureListener(simpleGestureListener);
        this.mScaleGestureListener = simpleScaleGestureListener;
        this.mScaleGestureDetector = new ScaleGestureDetector(context, simpleScaleGestureListener);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mScaleGestureDetector.onTouchEvent(motionEvent);
        return this.mGestureDetector.onTouchEvent(motionEvent) | this.mScaleGestureListener.mIsHandled;
    }

    public void setQuickScaleEnabled(boolean z) {
        this.mScaleGestureDetector.setQuickScaleEnabled(z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener implements ScaleGestureDetector.OnScaleGestureListener {
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }
    }
}
