package com.samsung.o3dp.lib3dphotography.animation.animations;

import He.F;
import android.hardware.SensorManager;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.animation.time.AnimationTime;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.interaction.InputEvent;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GyroAnimation extends Animation {
    private static final float CAMERA_DIR_PIVOT_Z_MAX = 0.7f;
    private static final float CAMERA_DIR_PIVOT_Z_MIN = 0.3f;
    private static final float CAMERA_EYE_INTENSITY = -3.0f;
    private static final float CONVERGENCE_RATIO = 0.05f;
    private static final float DEFAULT_CAMERA_EYE_Z = 10.5f;
    private static final float DEFAULT_FOV = 10.0f;
    private static final float DEPTH_INTENSITY = 1.0f;
    private static final float NANO_SECONDS_PER_FRAME = 1.6666667E7f;
    private static final float ROTATION_VECTOR_ANGLE_MAX = 0.27f;
    private static final float ROTATION_VECTOR_ANGLE_MIN = 0.15f;
    private static final float TIME_RANGE_OFFSET = 0.1f;
    private Vector3 mConvergenceValue = null;
    private Vector3 mCurrentAxisAngle;
    private Vector3 mCurrentCameraEye;
    private float mRotationVectorAngleLimit = F.M(ROTATION_VECTOR_ANGLE_MAX, ROTATION_VECTOR_ANGLE_MIN, 0.0f);
    private AnimationParams mTargetParams;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ConvergenceTime extends AnimationTime {
        private final float convergenceRatio;
        private long prevTime = 0;

        public ConvergenceTime(float f) {
            this.convergenceRatio = f;
        }

        public void consumeByDeltaTime() {
            long nanoTime = System.nanoTime();
            setDeltaTime((float) (nanoTime - this.prevTime));
            this.prevTime = nanoTime;
            super.consumeByDeltaTime();
        }

        public void reset() {
            super.reset();
            this.prevTime = System.nanoTime();
        }

        public void update(float f) {
            float f5 = f / GyroAnimation.NANO_SECONDS_PER_FRAME;
            this.m_currentTime = Math.max(Math.min(1.0f, this.convergenceRatio * f5), 0.0f);
            this.m_totalTime = (f5 * 0.005f) + this.m_totalTime;
        }
    }

    public GyroAnimation() {
        initAnimation();
    }

    private Vector3 getAxisAngleVector(InputEvent.MotionSensor motionSensor) {
        float[] fArr = new float[4];
        SensorManager.getQuaternionFromVector(fArr, motionSensor.getSensorEvent().values);
        float asin = (float) Math.asin((double) (((fArr[0] * fArr[2]) - (fArr[3] * fArr[1])) * 2.0f));
        float asin2 = (float) Math.asin((double) (((fArr[2] * fArr[3]) + (fArr[0] * fArr[1])) * 2.0f));
        float asin3 = (float) Math.asin((double) (((fArr[1] * fArr[2]) + (fArr[0] * fArr[3])) * 2.0f));
        Vector3 vector3 = new Vector3();
        int rotation = motionSensor.getRotation();
        if (rotation == 0) {
            vector3.set(asin, asin2, asin3);
            return vector3;
        } else if (rotation == 1) {
            vector3.set(asin2, -asin, asin3);
            return vector3;
        } else if (rotation == 2) {
            vector3.set(-asin, -asin2, asin3);
            return vector3;
        } else if (rotation != 3) {
            return vector3;
        } else {
            vector3.set(-asin2, asin, asin3);
            return vector3;
        }
    }

    private static float symmetricNormalizer(float f) {
        return Math.abs(Math.max(Math.min(1.0f, f), 0.0f) - 0.5f) * 2.0f;
    }

    public void consumeGyroParallax() {
        Vector3 vector3 = this.mConvergenceValue;
        if (vector3 != null) {
            Vector3 lerp = Vector3.lerp(vector3, this.mCurrentAxisAngle, getAnimationTime().getCurrentTime());
            this.mConvergenceValue = lerp;
            Vector3 subtract = Vector3.subtract(lerp, this.mCurrentAxisAngle);
            float f = this.mRotationVectorAngleLimit;
            subtract.clamp(-f, f);
            Vector3 vector32 = this.mCurrentCameraEye;
            vector32.f4210x = (subtract.f4210x * CAMERA_EYE_INTENSITY) + vector32.f4210x;
            vector32.y = (subtract.y * CAMERA_EYE_INTENSITY) + vector32.y;
            AnimationParams defaultAnimationParams = getDefaultAnimationParams();
            this.mTargetParams = defaultAnimationParams;
            Vector3 vector33 = defaultAnimationParams.cameraEye;
            float f5 = vector33.f4210x;
            Vector3 vector34 = this.mCurrentCameraEye;
            vector33.f4210x = C0212a.a(vector34.f4210x, f5, 0.5f, f5);
            float f8 = vector33.y;
            vector33.y = C0212a.a(vector34.y, f8, 0.5f, f8);
            this.mCurrentCameraEye = vector33;
            defaultAnimationParams.cameraDir = Vector3.direction(vector33, new Vector3(0.0f, 0.0f, getCameraDirPivotZ()));
        }
    }

    public AnimationParams[] getAnimationParams() {
        consumeGyroParallax();
        setAnimationParams(new AnimationParams[]{this.mTargetParams});
        return super.getAnimationParams();
    }

    public float getCameraDirPivotZ() {
        return Math.min(CAMERA_DIR_PIVOT_Z_MAX, Math.max(super.getCameraDirPivotZ(), 0.3f));
    }

    public AnimationParams getDefaultAnimationParams() {
        AnimationParams animationParams = new AnimationParams();
        animationParams.time = Float.valueOf(0.0f);
        animationParams.cameraEye = new Vector3(0.0f, 0.0f, DEFAULT_CAMERA_EYE_Z);
        animationParams.cameraUp = new Vector3(0.0f, 1.0f, 0.0f);
        animationParams.cameraDir = new Vector3(0.0f, 0.0f, -1.0f);
        animationParams.fov = 10.0f;
        animationParams.depthIntensity = 1.0f;
        return animationParams;
    }

    public int getSensorType() {
        return 15;
    }

    public AnimationParams getTargetParams() {
        return this.mTargetParams;
    }

    public void initAnimation() {
        setName("Gyro-Parallax");
        setSortOrder(1001);
        setAnimationTimeMode((AnimationTime) new ConvergenceTime(CONVERGENCE_RATIO));
        setDynamicOffset("none");
        setBokehEnabled(Boolean.FALSE);
        setAnimationParams(new AnimationParams[]{getDefaultAnimationParams()});
        this.mCurrentAxisAngle = new Vector3();
        this.mCurrentCameraEye = new Vector3(0.0f, 0.0f, DEFAULT_CAMERA_EYE_Z);
        this.mTargetParams = getDefaultAnimationParams();
    }

    public boolean onMotionSensorEvent(InputEvent.MotionSensor motionSensor) {
        if (motionSensor.isFirstEvent()) {
            this.mConvergenceValue = null;
        }
        Vector3 axisAngleVector = getAxisAngleVector(motionSensor);
        this.mCurrentAxisAngle = axisAngleVector;
        if (this.mConvergenceValue != null) {
            return true;
        }
        this.mConvergenceValue = axisAngleVector;
        return true;
    }

    public void reset() {
        super.reset();
        this.mConvergenceValue = null;
        this.mCurrentCameraEye = new Vector3(0.0f, 0.0f, DEFAULT_CAMERA_EYE_Z);
        setAnimationParams(new AnimationParams[]{getDefaultAnimationParams()});
    }

    public void setCameraDirPivotZ(float f) {
        super.setCameraDirPivotZ(f);
        this.mRotationVectorAngleLimit = F.M(ROTATION_VECTOR_ANGLE_MAX, ROTATION_VECTOR_ANGLE_MIN, symmetricNormalizer(getCameraDirPivotZ()));
    }

    public GyroAnimation(String str) {
        super(str);
        initAnimation();
    }
}
