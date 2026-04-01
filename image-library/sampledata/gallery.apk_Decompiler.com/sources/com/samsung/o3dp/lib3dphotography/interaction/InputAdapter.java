package com.samsung.o3dp.lib3dphotography.interaction;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.MotionEvent;
import android.view.View;
import com.samsung.o3dp.lib3dphotography.animation.AnimationManager;
import com.samsung.o3dp.lib3dphotography.interaction.InputEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InputAdapter implements View.OnTouchListener, SensorEventListener {
    private static final float LOW_PASS_FILTER_WEIGHT = 0.8f;
    private final AnimationManager mAnimationManager;
    private final IContract mContract;
    private boolean mIsFirstEvent = true;
    private float[] mPreviousSensorEventValues;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface IContract {
        int getRotation();

        boolean isTopLayerTouched(MotionEvent motionEvent);
    }

    public InputAdapter(AnimationManager animationManager, IContract iContract) {
        this.mContract = iContract;
        this.mAnimationManager = animationManager;
    }

    private float[] applyLowPassFilter(float[] fArr) {
        float[] fArr2 = this.mPreviousSensorEventValues;
        if (fArr2 == null || fArr2.length != fArr.length) {
            float[] fArr3 = (float[]) fArr.clone();
            this.mPreviousSensorEventValues = fArr3;
            return fArr3;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            float[] fArr4 = this.mPreviousSensorEventValues;
            fArr4[i2] = (fArr[i2] * 0.19999999f) + (fArr4[i2] * 0.8f);
        }
        return this.mPreviousSensorEventValues;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        this.mAnimationManager.onInputEvent(new InputEvent.MotionSensor(sensorEvent, this.mIsFirstEvent, this.mContract.getRotation(), applyLowPassFilter(sensorEvent.values)));
        if (this.mIsFirstEvent) {
            this.mIsFirstEvent = false;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.mAnimationManager.onInputEvent(new InputEvent.Touch(view, motionEvent, this.mContract.isTopLayerTouched(motionEvent)));
    }

    public void resetFirstEvent() {
        this.mIsFirstEvent = true;
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
    }
}
