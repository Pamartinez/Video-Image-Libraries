package com.samsung.android.gallery.app.activity;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.gesture.OnRotationGestureListener;
import com.samsung.android.gallery.widget.gesture.OnShrinkGestureListener;
import com.samsung.android.gallery.widget.gesture.RotationGestureDetector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DefaultGestureDetector extends GestureDetector {
    private final Blackboard mBlackboard;
    private RotationGestureDetector mRotationDetector;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultGestureListener extends GestureDetector.SimpleOnGestureListener {
        private final Blackboard mBlackBoard;

        public DefaultGestureListener(Blackboard blackboard) {
            this.mBlackBoard = blackboard;
        }

        private boolean isAllowMouseSingleClick(MotionEvent motionEvent) {
            if (motionEvent.getToolType(0) != 3 || DeviceInfo.isDexMode(Blackboard.getContext())) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDoubleTap$0() {
            this.mBlackBoard.erase("data://gesture_on_double_tapped");
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (DeviceInfo.isDexMode(Blackboard.getContext())) {
                this.mBlackBoard.publish("data://gesture_on_double_tapped", Boolean.TRUE);
                ThreadUtil.postOnBgThreadDelayed(new i(3, this), 200);
            }
            return super.onDoubleTap(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
            if (motionEvent == null || motionEvent2 == null || Math.abs(motionEvent.getY() - motionEvent2.getY()) > 1920.0f || Math.abs(motionEvent.getY()) < ((float) DeviceInfo.getStatusBarHeight())) {
                return false;
            }
            float max = (float) Math.max(120, (int) Math.abs(motionEvent2.getX() - motionEvent.getX()));
            if (motionEvent.getY() - motionEvent2.getY() <= max || Math.abs(f5) <= 0.0f) {
                if (motionEvent2.getY() - motionEvent.getY() > max && Math.abs(f5) > 0.0f) {
                    Log.majorEvent("FlingDown");
                    this.mBlackBoard.postEvent(EventMessage.obtain(4002));
                }
                return false;
            }
            Log.majorEvent("FlingUp");
            this.mBlackBoard.postEvent(EventMessage.obtain(4001));
            return false;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return super.onSingleTapConfirmed(motionEvent);
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (isAllowMouseSingleClick(motionEvent)) {
                this.mBlackBoard.publish("data://gesture_on_double_tapped", Boolean.TRUE);
            }
            return super.onSingleTapUp(motionEvent);
        }
    }

    public DefaultGestureDetector(Context context, Blackboard blackboard) {
        super(context, new DefaultGestureListener(blackboard));
        this.mBlackboard = blackboard;
    }

    public boolean isAdvancedMouseEventAction(MotionEvent motionEvent, Context context) {
        if (motionEvent.getToolType(0) != 3 || !DeviceInfo.isAdvancedMouseCompat(context)) {
            this.mBlackboard.erase("data://motion_event_tool_type_mouse");
            return false;
        }
        this.mBlackboard.publish("data://motion_event_tool_type_mouse", Boolean.TRUE);
        if (!DeviceInfo.isDexMode(context) || !((Boolean) this.mBlackboard.read("data://gesture_on_double_tapped", Boolean.FALSE)).booleanValue()) {
            return true;
        }
        return false;
    }

    public boolean isPenAction(MotionEvent motionEvent) {
        if (motionEvent.getToolType(0) == 2) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        if (this.mRotationDetector == null) {
            this.mRotationDetector = new RotationGestureDetector();
        }
        this.mRotationDetector.onTouchEvent(motionEvent);
        Object[] objArr = (Object[]) this.mBlackboard.read("function://pinch_shrink_listeners");
        if (objArr != null) {
            this.mRotationDetector.setListener((OnRotationGestureListener) objArr[1]);
            return ((OnShrinkGestureListener) objArr[0]).onShrinkGesture(motionEvent);
        }
        this.mRotationDetector.setListener((OnRotationGestureListener) null);
        return false;
    }
}
