package com.samsung.o3dp.lib3dphotography.animation.dynamics;

import android.graphics.PointF;
import android.view.MotionEvent;
import com.samsung.o3dp.lib3dphotography.animation.transition.PathTransition;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.interaction.InputEvent;
import com.samsung.o3dp.lib3dphotography.interaction.TouchData;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DampedOscillationDynamicOffset extends DynamicOffset {
    public static final float ACCEL_NORMAL_INTENSITY = 0.25f;
    public static final float ACCEL_STATIC_INTENSITY = 0.001f;
    public static final float DAMPED_OSCILLATION_EMBOSSING_Z = 0.1f;
    public static final float DAMPED_OSCILLATION_INTENSITY = 0.0325f;
    public static final float DAMPED_OSCILLATION_SPEED = 1.0f;
    private static final String TAG = "DampedOscillationDynamicOffset";
    public static final float TOUCH_DISTANCE_LIMIT = 15.0f;
    public static final float TOUCH_NORMAL_INTENSITY = 0.01f;
    public static final float TOUCH_STATIC_INTENSITY = 0.001f;
    private float mAccelerometerSensitivity = 0.5f;
    private float mAudioSensitivity = 0.0f;
    private float mDamping = 0.9f;
    private boolean mIsInteracting = false;
    private boolean mIsOverThreshold = false;
    private float mLambda = 5.0f;
    private float mMinimumDB = 58.0f;
    private final Vector3 mOffset = new Vector3(0.0f, 0.0f, 0.0f);
    private float mPrevTime = -1.0f;
    private final PathTransition mSineInOut90 = new PathTransition(PathTransition.SINE_IN_OUT_90);
    private Vector3 mStartDragOffset = new Vector3(0.0f, 0.0f, 0.0f);
    private int mStaticDynamicsDirection = 0;
    private final TouchData mTouchData = new TouchData();
    private final Vector3 mVelocity = new Vector3(0.0f, 0.0f, 0.0f);

    public DampedOscillationDynamicOffset() {
        setIntensity(0.0325f);
        setEmbossingZ(0.1f);
        this.mForegroundOnly = true;
        this.mSpeed = 1.0f;
    }

    private Vector3 getCurrentDynamicOffset() {
        float intensity = getIntensity();
        Vector3 vector3 = this.mOffset;
        Vector3 vector32 = this.mAmplitude;
        return new Vector3(vector3.f4210x * intensity * vector32.f4210x, vector3.y * intensity * vector32.y, getInterpolatedEmbossingZ());
    }

    private int getDirection(float f, float f5) {
        int i2;
        if (f > 0.0f) {
            i2 = 8;
        } else if (f < 0.0f) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        if (f5 > 0.0f) {
            return i2 | 2;
        }
        if (f5 < 0.0f) {
            return i2 | 1;
        }
        return i2;
    }

    private float getInterpolatedEmbossingZ() {
        float min = Math.min(this.mPrevTime, 1.0f);
        return this.mSineInOut90.transitionTime(min) * getEmbossingZ();
    }

    private boolean isActivated(float f, float f5, float f8) {
        if (this.mIsOverThreshold) {
            return true;
        }
        if (Math.abs(f) <= 10.0f && Math.abs(f5) <= 10.0f && Math.abs(f8) <= 10.0f) {
            return false;
        }
        this.mIsOverThreshold = true;
        return true;
    }

    private boolean isHorizontallyStatic(int i2) {
        if (i2 == 0) {
            return false;
        }
        if ((i2 & 4) > 0 && (this.mStaticDynamicsDirection & 8) > 0) {
            return true;
        }
        if ((i2 & 8) <= 0 || (this.mStaticDynamicsDirection & 4) <= 0) {
            return false;
        }
        return true;
    }

    private boolean isVerticallyStatic(int i2) {
        if (i2 == 0) {
            return false;
        }
        if (((i2 & 1) <= 0 || (this.mStaticDynamicsDirection & 2) <= 0) && ((i2 & 2) <= 0 || (this.mStaticDynamicsDirection & 1) <= 0)) {
            return false;
        }
        return true;
    }

    private void setAccelerometerData(float f, float f5, float f8) {
        float f10;
        if (isActivated(f, f5, f8)) {
            int direction = getDirection(-f, -f5);
            float f11 = 0.001f;
            if (isVerticallyStatic(direction)) {
                f10 = 0.001f;
            } else {
                f10 = 0.25f;
            }
            if (!isHorizontallyStatic(direction)) {
                f11 = 0.25f;
            }
            Vector3 vector3 = this.mVelocity;
            float f12 = vector3.f4210x;
            float f13 = this.mAccelerometerSensitivity;
            vector3.f4210x = f12 - ((f11 * f) * f13);
            vector3.y -= (f10 * f5) * f13;
            vector3.z -= (f8 * 0.25f) * f13;
        }
    }

    private void updateDampedOscillation(float f) {
        Vector3 vector3 = this.mOffset;
        float f5 = this.mLambda;
        float f8 = this.mDamping;
        Vector3 vector32 = this.mVelocity;
        Vector3 vector33 = new Vector3(((-vector3.f4210x) * f5) - (vector32.f4210x * f8), ((-vector3.y) * f5) - (vector32.y * f8), ((-vector3.z) + f5) - (f8 * vector32.z));
        Vector3 vector34 = this.mVelocity;
        float f10 = (vector33.f4210x * f) + vector34.f4210x;
        vector34.f4210x = f10;
        float f11 = (vector33.y * f) + vector34.y;
        vector34.y = f11;
        float f12 = (vector33.z * f) + vector34.z;
        vector34.z = f12;
        Vector3 vector35 = this.mOffset;
        vector35.f4210x = (f10 * f) + vector35.f4210x;
        vector35.y = (f11 * f) + vector35.y;
        vector35.z = (f12 * f) + vector35.z;
    }

    public Vector3 getDynamicOffset(float f) {
        if (this.mPrevTime < 0.0f) {
            this.mPrevTime = f;
        }
        float max = Math.max(f - this.mPrevTime, 0.0f) * this.mSpeed * 10.0f;
        this.mPrevTime = f;
        if (!this.mIsInteracting) {
            updateDampedOscillation(max);
        }
        return getCurrentDynamicOffset();
    }

    public int getSensorType() {
        return 10;
    }

    public boolean isTouchInteraction() {
        return true;
    }

    public void onMotionSensorEvent(InputEvent.MotionSensor motionSensor) {
        if (motionSensor.getSensorEvent().sensor.getType() == 10) {
            float f = motionSensor.getLowPassFilteredValues()[0];
            float f5 = motionSensor.getLowPassFilteredValues()[1];
            float f8 = motionSensor.getLowPassFilteredValues()[2];
            int rotation = motionSensor.getRotation();
            if (rotation == 0) {
                setAccelerometerData(f, f5, f8);
            } else if (rotation == 1) {
                setAccelerometerData(f5, -f, f8);
            } else if (rotation == 2) {
                setAccelerometerData(-f, -f5, f8);
            } else if (rotation == 3) {
                setAccelerometerData(-f5, f, f8);
            }
        }
    }

    public boolean onTouchEvent(InputEvent.Touch touch) {
        PointF dragDist;
        float f;
        MotionEvent motionEvent = touch.getMotionEvent();
        this.mTouchData.onTouch(motionEvent);
        if (motionEvent.getAction() == 0) {
            this.mIsInteracting = true;
            this.mStartDragOffset = new Vector3(this.mOffset);
            return true;
        } else if (!this.mTouchData.getIsTouched()) {
            this.mIsInteracting = false;
            return true;
        } else if (!this.mIsInteracting || (dragDist = this.mTouchData.getDragDist(0)) == null) {
            return true;
        } else {
            int direction = getDirection(dragDist.x, dragDist.y);
            float f5 = 0.01f;
            if (isVerticallyStatic(direction)) {
                f = 0.001f;
            } else {
                f = 0.01f;
            }
            if (isHorizontallyStatic(direction)) {
                f5 = 0.001f;
            }
            float f8 = dragDist.x * f5;
            Vector3 vector3 = this.mStartDragOffset;
            float f10 = f8 + vector3.f4210x;
            float f11 = (dragDist.y * f) + vector3.y;
            if (Math.abs(f10) < 15.0f) {
                this.mOffset.f4210x = f10;
            }
            if (Math.abs(f11) < 15.0f) {
                this.mOffset.y = f11;
            }
            return true;
        }
    }

    public void parseConfig(JSONObject jSONObject) {
        super.parseConfig(jSONObject);
        this.mLambda = (float) jSONObject.optDouble("lambda", (double) this.mLambda);
        this.mDamping = (float) jSONObject.optDouble("damping", (double) this.mDamping);
        this.mAccelerometerSensitivity = (float) jSONObject.optDouble("accelerometer_sensitivity", (double) this.mAccelerometerSensitivity);
        this.mMinimumDB = (float) jSONObject.optDouble("min_decibels", (double) this.mMinimumDB);
        this.mAudioSensitivity = (float) jSONObject.optDouble("audio_sensitivity", (double) this.mAudioSensitivity);
        setStaticDynamicsDirection(jSONObject.optInt("static_dynamics_direction", this.mStaticDynamicsDirection));
    }

    public boolean respondsToAudio() {
        if (this.mAudioSensitivity > 0.0f) {
            return true;
        }
        return false;
    }

    public void setAudioLevel(float f) {
        this.mVelocity.y -= (Math.max(0.0f, f - this.mMinimumDB) * 0.1f) * this.mAudioSensitivity;
    }

    public void setStaticDynamicsDirection(int i2) {
        this.mStaticDynamicsDirection = i2;
        LogUtil.i(TAG, "setStaticDynamicsDirection() : " + i2);
    }
}
