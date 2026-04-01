package com.samsung.android.gallery.widget;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.samsung.android.gallery.support.helper.DeviceInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DragExitHandler {
    private long mDownEventTime;
    private final float mDragTouchSlop;
    private final float mDragUpBoundSlop;
    private PointF mTouchDownPoint;
    TouchListener mTouchListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TouchListener {
        boolean isMovable();

        boolean isSupportedDragExit();

        void onDetectedDragExit();
    }

    public DragExitHandler(Context context, TouchListener touchListener) {
        float scaledPagingTouchSlop = (float) ViewConfiguration.get(context).getScaledPagingTouchSlop();
        this.mDragTouchSlop = scaledPagingTouchSlop;
        this.mDragUpBoundSlop = scaledPagingTouchSlop / 3.0f;
        this.mTouchListener = touchListener;
    }

    private boolean checkMinDragExitExecutionTime(MotionEvent motionEvent) {
        if (motionEvent.getEventTime() - this.mDownEventTime > 25) {
            return true;
        }
        return false;
    }

    private void onMove(MotionEvent motionEvent) {
        TouchListener touchListener;
        if (this.mTouchDownPoint != null && motionEvent.getPointerCount() <= 1) {
            float abs = Math.abs(motionEvent.getX() - this.mTouchDownPoint.x);
            float abs2 = Math.abs(motionEvent.getY() - this.mTouchDownPoint.y);
            float y = motionEvent.getY();
            float f = this.mTouchDownPoint.y;
            if (y - f > 0.0f) {
                if (f > ((float) DeviceInfo.getStatusBarHeight()) && abs2 > this.mDragTouchSlop && abs2 / abs > 1.4f && checkMinDragExitExecutionTime(motionEvent) && (touchListener = this.mTouchListener) != null) {
                    touchListener.onDetectedDragExit();
                }
            } else if (f - motionEvent.getY() > this.mDragUpBoundSlop) {
                this.mTouchDownPoint = null;
            }
        }
    }

    public void cancel() {
        this.mTouchDownPoint = null;
    }

    public void onDown(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 1 && this.mTouchListener.isSupportedDragExit()) {
            this.mTouchDownPoint = new PointF(motionEvent.getX(), motionEvent.getY());
            this.mDownEventTime = motionEvent.getEventTime();
        }
    }

    public boolean onTouch(MotionEvent motionEvent) {
        if (!this.mTouchListener.isMovable()) {
            this.mTouchDownPoint = null;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            onDown(motionEvent);
        } else if (actionMasked == 1) {
            this.mTouchDownPoint = null;
        } else if (actionMasked == 2 || actionMasked == 3) {
            onMove(motionEvent);
        }
        return false;
    }
}
