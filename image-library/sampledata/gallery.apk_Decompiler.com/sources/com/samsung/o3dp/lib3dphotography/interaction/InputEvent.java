package com.samsung.o3dp.lib3dphotography.interaction;

import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface InputEvent {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MotionSensor implements InputEvent {
        private final boolean mIsFirstEvent;
        private final float[] mLowPassFilteredValues;
        private final int mRotation;
        private final SensorEvent mSensorEvent;

        public MotionSensor(SensorEvent sensorEvent, boolean z, int i2, float[] fArr) {
            this.mSensorEvent = sensorEvent;
            this.mRotation = i2;
            this.mLowPassFilteredValues = fArr;
            this.mIsFirstEvent = z;
        }

        public float[] getLowPassFilteredValues() {
            return this.mLowPassFilteredValues;
        }

        public int getRotation() {
            return this.mRotation;
        }

        public SensorEvent getSensorEvent() {
            return this.mSensorEvent;
        }

        public boolean isFirstEvent() {
            return this.mIsFirstEvent;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Touch implements InputEvent {
        private final boolean mIsTopLayerTouched;
        private final MotionEvent mMotionEvent;
        private final View mView;

        public Touch(View view, MotionEvent motionEvent, boolean z) {
            boolean z3;
            this.mView = view;
            this.mMotionEvent = motionEvent;
            if (!z || motionEvent.getAction() != 0) {
                z3 = false;
            } else {
                z3 = true;
            }
            this.mIsTopLayerTouched = z3;
        }

        public MotionEvent getMotionEvent() {
            return this.mMotionEvent;
        }

        public View getView() {
            return this.mView;
        }

        public boolean isTopLayeredTouched() {
            return this.mIsTopLayerTouched;
        }
    }
}
