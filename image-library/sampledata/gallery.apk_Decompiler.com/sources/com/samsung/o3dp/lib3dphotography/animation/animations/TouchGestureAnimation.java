package com.samsung.o3dp.lib3dphotography.animation.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.interaction.InputEvent;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TouchGestureAnimation extends Animation {
    public static final float DEFAULT_CAMERA_EYE_Z = 7.0f;
    private static final int INTERACTION_IDLE_TIMEOUT_MS = 2000;
    private static final int INTRO_ANIMATION_MS = 300;
    private static final int RECOVERY_ANIMATION_MS = 800;
    private static final String TAG = "TouchGestureAnimation";
    private static final float TOUCH_GESTURE_FOV = 15.0f;
    private IContract mContract;
    private Vector3 mCurrentDir;
    private Vector3 mCurrentEye;
    private final Handler mHandler;
    private final ValueAnimator mIntroAnimator;
    private Vector3 mOriginDir;
    private Vector3 mOriginEye;
    private int mPrevPointerCount = 0;
    private float mPrevSpan = 0.0f;
    private final ValueAnimator mRecoveryAnimator;
    private Vector3 mStartDir;
    private Vector3 mStartEye;
    private final PointF mStartTouch = new PointF();
    /* access modifiers changed from: private */
    public boolean mStartTouchGesture = false;
    private final Runnable mTouchGestureTimeOut;
    private float mTouchSlope = 1000.0f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface IContract {
        Vector3 getCameraDir();

        Vector3 getCameraEye();

        boolean isInverse();

        boolean isSupportCentroidLook();

        boolean isSupportIntroAnimation();

        boolean isSupportRecoverAnimation();

        void setCameraDir(Vector3 vector3);

        void setCameraEye(Vector3 vector3);
    }

    public TouchGestureAnimation(Context context) {
        setName("Touch-Gesture");
        setSortOrder(2000);
        setSpeedFactor(0.0f);
        setDynamicOffset("none");
        AnimationParams animationParams = new AnimationParams();
        animationParams.cameraEye = new Vector3(0.0f, 0.0f, 7.0f);
        animationParams.fov = 15.0f;
        setAnimationParams(new AnimationParams[]{animationParams});
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mRecoveryAnimator = ofFloat;
        ofFloat.setDuration(800);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                TouchGestureAnimation.this.mStartTouchGesture = false;
            }
        });
        ofFloat.addUpdateListener(new c(this, 0));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mIntroAnimator = ofFloat2;
        ofFloat2.setDuration(300);
        ofFloat2.addUpdateListener(new c(this, 1));
        Looper myLooper = Looper.myLooper();
        Objects.requireNonNull(myLooper);
        this.mHandler = new Handler(myLooper);
        this.mTouchGestureTimeOut = new a(this, 1);
        this.mContract = new IContract() {
            public Vector3 getCameraDir() {
                return TouchGestureAnimation.this.getAnimationParams()[0].cameraDir;
            }

            public Vector3 getCameraEye() {
                return TouchGestureAnimation.this.getAnimationParams()[0].cameraEye;
            }

            public boolean isInverse() {
                return false;
            }

            public boolean isSupportCentroidLook() {
                return true;
            }

            public boolean isSupportIntroAnimation() {
                return true;
            }

            public boolean isSupportRecoverAnimation() {
                return true;
            }

            public void setCameraDir(Vector3 vector3) {
                TouchGestureAnimation.this.getAnimationParams()[0].cameraDir = vector3;
            }

            public void setCameraEye(Vector3 vector3) {
                TouchGestureAnimation.this.getAnimationParams()[0].cameraEye = vector3;
            }
        };
    }

    private void cameraFreeLook(Vector3 vector3) {
        Vector3 vector32;
        Vector3 vector33;
        if (this.mContract.isSupportCentroidLook()) {
            float f = vector3.f4210x;
            float f5 = vector3.y;
            Vector3 vector34 = this.mStartEye;
            float f8 = vector3.z;
            vector32 = vector34.add(new Vector3((-f) * (-f8), (-f5) * (-f8), (f + f5) / 2.0f));
            vector33 = Vector3.direction(vector32, new Vector3());
        } else {
            Vector3 vector35 = this.mStartEye;
            float f10 = vector3.z;
            Vector3 add = vector35.add(new Vector3((-vector3.f4210x) * (-f10), (-vector3.y) * (-f10), 0.0f));
            vector33 = this.mStartDir.add(new Vector3(-vector3.f4210x, -vector3.y, 0.0f));
            vector32 = add;
        }
        this.mContract.setCameraEye(vector32);
        this.mContract.setCameraDir(vector33);
    }

    private void cameraPanning(Vector3 vector3) {
        if (!this.mContract.isSupportCentroidLook()) {
            Vector3 vector32 = this.mStartEye;
            float f = vector3.f4210x;
            float f5 = vector3.z;
            this.mContract.setCameraEye(vector32.add(new Vector3(f * f5, vector3.y * f5, 0.0f)));
        }
    }

    private void cameraPinchZoom(Vector3 vector3, MotionEvent motionEvent) {
        float f;
        float rawX = motionEvent.getRawX(1) - motionEvent.getRawX(0);
        float rawY = motionEvent.getRawY(1) - motionEvent.getRawY(0);
        float sqrt = (float) Math.sqrt((double) ((rawY * rawY) + (rawX * rawX)));
        float f5 = this.mPrevSpan;
        if (f5 > 0.0f) {
            f = sqrt / f5;
        } else {
            f = 1.0f;
        }
        float f8 = 1.0f - f;
        this.mPrevSpan = sqrt;
        if (this.mContract.isInverse()) {
            f8 *= -1.0f;
        }
        Vector3 vector32 = this.mStartEye;
        float f10 = vector32.z;
        Vector3 add = vector32.add(new Vector3((-vector3.f4210x) * (-f10), (-vector3.y) * (-f10), 0.0f));
        add.z = this.mContract.getCameraEye().z + f8;
        this.mContract.setCameraEye(add);
        if (this.mContract.isSupportCentroidLook()) {
            this.mContract.setCameraDir(Vector3.direction(add, new Vector3()));
        }
    }

    private Vector3 getTouchDeltaXYZ(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        PointF pointF = new PointF(0.0f, 0.0f);
        for (int i2 = 0; i2 < pointerCount; i2++) {
            pointF.offset(motionEvent.getX(i2), motionEvent.getY(i2));
        }
        float f = (float) pointerCount;
        pointF.set(pointF.x / f, pointF.y / f);
        if (resetStartInfo(pointF, pointerCount)) {
            motionEvent.setAction(3);
        }
        PointF pointF2 = this.mStartTouch;
        float f5 = pointF2.y - pointF.y;
        float f8 = (pointF2.x - pointF.x) / this.mTouchSlope;
        float f10 = 1.0f;
        float max = Math.max(Math.min(f8, 1.0f), -1.0f);
        float max2 = Math.max(Math.min(f5 / this.mTouchSlope, 1.0f), -1.0f);
        float f11 = this.mContract.getCameraEye().z;
        if (f11 != 0.0f) {
            f10 = f11;
        }
        if (this.mContract.isInverse()) {
            f10 *= -1.0f;
            if (f10 >= 0.0f) {
                max *= -1.0f;
                max2 *= -1.0f;
            }
        }
        return new Vector3(max, max2, f10);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mContract.setCameraDir(Vector3.lerp(this.mCurrentDir, this.mOriginDir, floatValue));
        this.mContract.setCameraEye(Vector3.lerp(this.mCurrentEye, this.mOriginEye, floatValue));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        Vector3 cameraDir = this.mContract.getCameraDir();
        if (cameraDir != null) {
            this.mContract.setCameraDir(Vector3.lerp(cameraDir, new Vector3(0.0f, 0.0f, -1.0f), floatValue));
        } else {
            LogUtil.e(TAG, "mCamera.getCameraDir() is null");
        }
        Vector3 cameraEye = this.mContract.getCameraEye();
        if (cameraEye != null) {
            this.mContract.setCameraEye(Vector3.lerp(cameraEye, new Vector3(0.0f, 0.0f, 9.5f), floatValue));
        } else {
            LogUtil.e(TAG, "mCamera.getCameraEye() is null");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2() {
        this.mCurrentEye = this.mContract.getCameraEye();
        this.mCurrentDir = this.mContract.getCameraDir();
        if (this.mContract.isSupportRecoverAnimation()) {
            this.mRecoveryAnimator.start();
        } else {
            this.mStartTouchGesture = false;
        }
    }

    private boolean resetStartInfo(PointF pointF, int i2) {
        if (this.mPrevPointerCount == i2) {
            return false;
        }
        this.mStartTouch.set(pointF);
        this.mStartEye = new Vector3(this.mContract.getCameraEye());
        this.mStartDir = new Vector3(this.mContract.getCameraDir());
        this.mPrevSpan = 0.0f;
        this.mPrevPointerCount = i2;
        return true;
    }

    public void compute(int i2, int i7, int i8, int i10) {
        this.mTouchSlope = (float) Math.min(i8, i10);
    }

    public boolean isTouchInteraction() {
        return true;
    }

    public boolean onTouchEvent(InputEvent.Touch touch) {
        if (this.mIntroAnimator.isRunning()) {
            this.mHandler.removeCallbacks(this.mTouchGestureTimeOut);
            this.mHandler.postDelayed(this.mTouchGestureTimeOut, 2000);
            return false;
        }
        if (this.mStartTouchGesture) {
            this.mHandler.removeCallbacks(this.mTouchGestureTimeOut);
        }
        if (this.mRecoveryAnimator.isRunning()) {
            this.mRecoveryAnimator.pause();
        }
        MotionEvent motionEvent = touch.getMotionEvent();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                if (this.mStartTouchGesture) {
                    this.mHandler.postDelayed(this.mTouchGestureTimeOut, 2000);
                }
                this.mPrevPointerCount = 0;
            } else if (action == 2) {
                Vector3 touchDeltaXYZ = getTouchDeltaXYZ(motionEvent);
                int pointerCount = motionEvent.getPointerCount();
                if (pointerCount == 1) {
                    cameraFreeLook(touchDeltaXYZ);
                } else if (pointerCount == 2) {
                    cameraPinchZoom(touchDeltaXYZ, motionEvent);
                } else if (pointerCount <= 3) {
                    cameraPanning(touchDeltaXYZ);
                }
            }
        } else if (!this.mStartTouchGesture) {
            this.mStartTouchGesture = true;
            this.mOriginEye = new Vector3(this.mContract.getCameraEye());
            this.mOriginDir = new Vector3(this.mContract.getCameraDir());
            if (this.mContract.isSupportIntroAnimation()) {
                this.mIntroAnimator.start();
            }
        }
        return true;
    }

    public void setCameraContract(IContract iContract) {
        this.mContract = iContract;
    }
}
