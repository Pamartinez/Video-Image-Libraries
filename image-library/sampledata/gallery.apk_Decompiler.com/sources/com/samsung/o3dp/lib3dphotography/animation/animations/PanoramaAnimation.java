package com.samsung.o3dp.lib3dphotography.animation.animations;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.animation.AnimationPolicy;
import com.samsung.o3dp.lib3dphotography.animation.time.AnimationTime;
import com.samsung.o3dp.lib3dphotography.animation.time.SingleAnimationTime;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.interaction.InputEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PanoramaAnimation extends Animation {
    public static final float EDGE_BOUNCE_TENSION = 0.3f;
    private static final float EXTENT_WEIGHT = 0.49f;
    private static final float VERTICAL_FOV = 40.0f;
    private int mDisplayHeight;
    private int mDisplayWidth;
    private final GestureDetector mGestureDetector;
    private boolean mIsPerformedHapticFeedback = true;
    private boolean mIsTouching = false;
    private final PanoramaGestureListener mPanoramaGestureListener;
    private int mSourceHeight;
    private int mSourceWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PanoramaGestureListener extends GestureDetector.SimpleOnGestureListener {
        FlingAnimation flingAnimation;
        float previousFlingValue;
        View targetView;

        public /* synthetic */ PanoramaGestureListener(PanoramaAnimation panoramaAnimation, int i2) {
            this();
        }

        private void cancelAllAnimation() {
            this.flingAnimation.cancel();
            this.targetView.animate().cancel();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onFling$0(DynamicAnimation dynamicAnimation, float f, float f5) {
            PanoramaAnimation.this.getAnimationTime().update(PanoramaAnimation.this.getDeltaTimeBy(this.previousFlingValue - f));
            this.previousFlingValue = f;
        }

        public View getTargetView() {
            return this.targetView;
        }

        public boolean onDown(MotionEvent motionEvent) {
            cancelAllAnimation();
            return super.onDown(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
            float currentTime = PanoramaAnimation.this.getAnimationTime().getCurrentTime();
            if (currentTime == 0.0f || currentTime == 1.0f) {
                return false;
            }
            this.flingAnimation.cancel();
            ((FlingAnimation) this.flingAnimation.setStartVelocity(f).setFriction(2.0f).addUpdateListener(new b(this))).start();
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
            cancelAllAnimation();
            float d = PanoramaAnimation.this.getDeltaTimeBy(f);
            if (PanoramaAnimation.this.edgeBounceEffect(this.targetView, d)) {
                return true;
            }
            PanoramaAnimation.this.getAnimationTime().update(d);
            return true;
        }

        public void setTargetView(View view) {
            this.targetView = view;
        }

        private PanoramaGestureListener() {
            this.flingAnimation = new FlingAnimation(new FloatValueHolder(0.0f));
            this.previousFlingValue = 0.0f;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PanoramaTime extends SingleAnimationTime {
        public void reset() {
            this.m_totalTime = 0.5f;
            this.m_currentTime = 0.5f;
            this.m_direction = 1.0f;
        }
    }

    public PanoramaAnimation(Context context) {
        setName(AnimationPolicy.ANIM_PANORAMA);
        setSortOrder(9999);
        setMovement("linear");
        setTransition("linear");
        setDynamicOffset("none");
        setAnimationTimeMode((AnimationTime) new PanoramaTime());
        setSpeedFactor(0.0f);
        compute(1920, 1440, 1920, 1440);
        PanoramaGestureListener panoramaGestureListener = new PanoramaGestureListener(this, 0);
        this.mPanoramaGestureListener = panoramaGestureListener;
        this.mGestureDetector = new GestureDetector(context, panoramaGestureListener);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean edgeBounceEffect(android.view.View r11, float r12) {
        /*
            r10 = this;
            com.samsung.o3dp.lib3dphotography.animation.time.AnimationTime r0 = r10.getAnimationTime()
            float r0 = r0.getCurrentTime()
            float r1 = r11.getScaleX()
            r2 = 0
            int r3 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            r4 = 1
            r5 = 0
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r3 != 0) goto L_0x001b
            int r10 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r10 != 0) goto L_0x001a
            return r4
        L_0x001a:
            return r5
        L_0x001b:
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 <= 0) goto L_0x0028
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0028
            int r8 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0028
            return r5
        L_0x0028:
            float r8 = java.lang.Math.abs(r12)
            int r10 = r10.mDisplayWidth
            float r9 = (float) r10
            float r8 = r8 / r9
            r9 = 1050253722(0x3e99999a, float:0.3)
            float r8 = r8 / r9
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 >= 0) goto L_0x003f
            if (r7 != 0) goto L_0x003f
            r11.setPivotX(r2)
        L_0x003d:
            float r1 = r1 + r8
            goto L_0x0070
        L_0x003f:
            if (r3 <= 0) goto L_0x004a
            int r2 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x004a
            float r10 = (float) r10
            r11.setPivotX(r10)
            goto L_0x003d
        L_0x004a:
            if (r3 <= 0) goto L_0x0058
            if (r7 != 0) goto L_0x0058
            int r2 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0058
            float r1 = r1 - r8
            float r1 = java.lang.Math.max(r1, r6)
            goto L_0x0070
        L_0x0058:
            if (r12 >= 0) goto L_0x0068
            int r12 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r12 != 0) goto L_0x0068
            int r12 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r12 <= 0) goto L_0x0068
            float r1 = r1 - r8
            float r1 = java.lang.Math.max(r1, r6)
            goto L_0x0070
        L_0x0068:
            float r10 = (float) r10
            r12 = 1073741824(0x40000000, float:2.0)
            float r10 = r10 / r12
            r11.setPivotX(r10)
            r1 = r6
        L_0x0070:
            r11.setScaleX(r1)
            float r10 = r11.getScaleX()
            int r10 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x007c
            return r4
        L_0x007c:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.animation.animations.PanoramaAnimation.edgeBounceEffect(android.view.View, float):boolean");
    }

    /* access modifiers changed from: private */
    public float getDeltaTimeBy(float f) {
        if (f == 0.0f) {
            return 0.0f;
        }
        float max = ((float) this.mSourceWidth) * Math.max(((float) this.mDisplayWidth) / ((float) this.mSourceWidth), ((float) this.mDisplayHeight) / ((float) this.mSourceHeight));
        int i2 = this.mDisplayWidth;
        return (((f / ((float) i2)) * (((float) i2) / max)) / 0.005f) * normalizeBitmapWidth(1.0f, 1.8f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTouchEvent$0() {
        this.mIsTouching = false;
    }

    private float normalizeBitmapWidth(float f, float f5) {
        return (((f5 - f) * (((float) this.mSourceWidth) - 1080.0f)) / 8640.0f) + f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setPanoramaAnimPosition(com.samsung.o3dp.lib3dphotography.interaction.InputEvent.MotionSensor r7) {
        /*
            r6 = this;
            float[] r0 = r7.getLowPassFilteredValues()
            int r7 = r7.getRotation()
            r1 = 0
            r2 = 0
            r3 = 1
            if (r7 == 0) goto L_0x0021
            if (r7 == r3) goto L_0x001d
            r4 = 2
            if (r7 == r4) goto L_0x001a
            r4 = 3
            if (r7 == r4) goto L_0x0017
            r7 = r1
            goto L_0x0024
        L_0x0017:
            r7 = r0[r2]
            goto L_0x0024
        L_0x001a:
            r7 = r0[r3]
            goto L_0x0024
        L_0x001d:
            r7 = r0[r2]
        L_0x001f:
            float r7 = -r7
            goto L_0x0024
        L_0x0021:
            r7 = r0[r3]
            goto L_0x001f
        L_0x0024:
            int r0 = r6.mDisplayWidth
            float r0 = (float) r0
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r0 = r0 / r4
            r4 = 1065353216(0x3f800000, float:1.0)
            float r0 = r0 + r4
            r5 = 1082130432(0x40800000, float:4.0)
            float r5 = r6.normalizeBitmapWidth(r4, r5)
            float r0 = r0 / r5
            float r0 = r0 * r7
            com.samsung.o3dp.lib3dphotography.animation.time.AnimationTime r7 = r6.getAnimationTime()
            r7.setDeltaTime(r0)
            com.samsung.o3dp.lib3dphotography.animation.time.AnimationTime r7 = r6.getAnimationTime()
            float r7 = r7.getCurrentTime()
            boolean r0 = r6.mIsPerformedHapticFeedback
            if (r0 != 0) goto L_0x005f
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0050
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 != 0) goto L_0x005f
        L_0x0050:
            com.samsung.o3dp.lib3dphotography.animation.animations.PanoramaAnimation$PanoramaGestureListener r7 = r6.mPanoramaGestureListener
            android.view.View r7 = r7.getTargetView()
            if (r7 == 0) goto L_0x005c
            r0 = 6
            r7.performHapticFeedback(r0)
        L_0x005c:
            r6.mIsPerformedHapticFeedback = r3
            return
        L_0x005f:
            if (r0 == 0) goto L_0x0071
            r0 = 1036831949(0x3dcccccd, float:0.1)
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 < 0) goto L_0x0071
            r0 = 1063675494(0x3f666666, float:0.9)
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 > 0) goto L_0x0071
            r6.mIsPerformedHapticFeedback = r2
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.animation.animations.PanoramaAnimation.setPanoramaAnimPosition(com.samsung.o3dp.lib3dphotography.interaction.InputEvent$MotionSensor):void");
    }

    public void compute(int i2, int i7, int i8, int i10) {
        this.mSourceWidth = i2;
        this.mSourceHeight = i7;
        this.mDisplayWidth = i8;
        this.mDisplayHeight = i10;
        float f = (((float) i2) / ((float) i7)) * VERTICAL_FOV;
        float f5 = (((float) i8) / ((float) i10)) * VERTICAL_FOV;
        float f8 = (f5 - f) * EXTENT_WEIGHT;
        float f10 = (f - f5) * EXTENT_WEIGHT;
        AnimationParams[] animationParamsArr = new AnimationParams[3];
        AnimationParams animationParams = new AnimationParams();
        animationParamsArr[0] = animationParams;
        animationParams.time = Float.valueOf(0.0f);
        animationParamsArr[0].cameraEye = new Vector3(0.0f, 0.0f, 0.0f);
        animationParamsArr[0].cameraUp = new Vector3(0.0f, 1.0f, 0.0f);
        double d = (double) f8;
        animationParamsArr[0].cameraDir = new Vector3((float) Math.sin(Math.toRadians(d)), 0.0f, (float) (-Math.cos(Math.toRadians(d))));
        animationParamsArr[0].fov = VERTICAL_FOV;
        AnimationParams animationParams2 = new AnimationParams();
        animationParamsArr[1] = animationParams2;
        animationParams2.time = Float.valueOf(0.5f);
        animationParamsArr[1].cameraEye = new Vector3(0.0f, 0.0f, 0.0f);
        animationParamsArr[1].cameraUp = new Vector3(0.0f, 1.0f, 0.0f);
        animationParamsArr[1].cameraDir = new Vector3(0.0f, 0.0f, -1.0f);
        animationParamsArr[1].fov = VERTICAL_FOV;
        AnimationParams animationParams3 = new AnimationParams();
        animationParamsArr[2] = animationParams3;
        animationParams3.time = Float.valueOf(1.0f);
        animationParamsArr[2].cameraEye = new Vector3(0.0f, 0.0f, 0.0f);
        animationParamsArr[2].cameraUp = new Vector3(0.0f, 1.0f, 0.0f);
        double d2 = (double) f10;
        animationParamsArr[2].cameraDir = new Vector3((float) Math.sin(Math.toRadians(d2)), 0.0f, (float) (-Math.cos(Math.toRadians(d2))));
        animationParamsArr[2].fov = VERTICAL_FOV;
        setAnimationParams(animationParamsArr);
    }

    public int getSensorType() {
        return 4;
    }

    public boolean isTouchInteraction() {
        return true;
    }

    public boolean onMotionSensorEvent(InputEvent.MotionSensor motionSensor) {
        if (this.mIsTouching) {
            return false;
        }
        setPanoramaAnimPosition(motionSensor);
        return true;
    }

    public boolean onTouchEvent(InputEvent.Touch touch) {
        this.mPanoramaGestureListener.setTargetView(touch.getView());
        this.mGestureDetector.onTouchEvent(touch.getMotionEvent());
        int action = touch.getMotionEvent().getAction();
        if (action == 0) {
            this.mIsTouching = true;
            return true;
        } else if (action != 1 && action != 3) {
            return true;
        } else {
            if (touch.getView().getScaleX() != 1.0f) {
                touch.getView().animate().scaleX(1.0f).setDuration(100).withEndAction(new a(this, 0)).start();
                return true;
            }
            this.mIsTouching = false;
            return true;
        }
    }
}
