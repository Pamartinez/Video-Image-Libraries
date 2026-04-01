package com.samsung.o3dp.lib3dphotography.animation;

import H7.u;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.filter.n;
import com.samsung.o3dp.lib3dphotography.LayerInfo;
import com.samsung.o3dp.lib3dphotography.Quaternion;
import com.samsung.o3dp.lib3dphotography.animation.movement.Movement;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Animator {
    private static final String TAG = "Animator";
    private boolean mIsVideoRecording = false;
    private Animation m_animation = new Animation();
    private float m_embossingZ = -1.0f;
    private int m_foregroundLayer = 1;
    private int m_lastIndex = 0;
    private List<LayerInfo> m_layerInfo = null;

    public Animator() {
        ArrayList arrayList = new ArrayList();
        this.m_layerInfo = arrayList;
        arrayList.add(new LayerInfo());
    }

    private Vector3 calculateAutoLookAt(AnimationParams animationParams) {
        Vector3 cameraLookAt = this.m_animation.getCameraLookAt();
        return Vector3.direction(animationParams.cameraEye, Quaternion.makeRotation(new Vector3(0.0f, 0.0f, 1.0f), animationParams.layerDirection[this.m_foregroundLayer]).mult(new Vector3(cameraLookAt.f4210x, cameraLookAt.y, 0.0f)).add(animationParams.layerPosition[this.m_foregroundLayer]).add(new Vector3(0.0f, 0.0f, animationParams.depthIntensity * cameraLookAt.z)));
    }

    private int getAnimationParamIndex(AnimationParams[] animationParamsArr, float f) {
        int i2 = this.m_lastIndex;
        if (i2 < 0 || i2 > animationParamsArr.length - 1) {
            LogUtil.e(TAG, "Array index out of bounds");
            return 0;
        }
        if (f <= animationParamsArr[i2].time.floatValue()) {
            while (true) {
                int i7 = this.m_lastIndex;
                if (i7 <= 0 || f > animationParamsArr[i7].time.floatValue()) {
                    break;
                }
                this.m_lastIndex--;
            }
        } else if (f > animationParamsArr[this.m_lastIndex + 1].time.floatValue()) {
            while (true) {
                int i8 = this.m_lastIndex;
                if (i8 >= animationParamsArr.length - 1 || f <= animationParamsArr[i8 + 1].time.floatValue()) {
                    break;
                }
                this.m_lastIndex++;
            }
        }
        return this.m_lastIndex;
    }

    private AnimationParams getInterpolatedParams(int i2, AnimationParams[] animationParamsArr, float f) {
        AnimationParams animationParams = new AnimationParams();
        animationParams.time = Float.valueOf(f);
        if (i2 >= 0 && i2 < animationParamsArr.length) {
            float floatValue = (f - animationParamsArr[i2].time.floatValue()) / (animationParamsArr[i2 + 1].time.floatValue() - animationParamsArr[i2].time.floatValue());
            LayerInfo layerInfo = (LayerInfo) C0086a.f(1, this.m_layerInfo);
            Movement movement = this.m_animation.getMovement();
            animationParams.cameraEye = movement.getVector3Interpolate(i2, animationParamsArr, floatValue, new n(22));
            animationParams.cameraDir = movement.slerpVector3(i2, animationParamsArr, floatValue, new b(15, this));
            animationParams.cameraUp = movement.slerpVector3(i2, animationParamsArr, floatValue, new n(23));
            for (int i7 = 0; i7 < 2; i7++) {
                animationParams.layerPosition[i7] = movement.getVector3Interpolate(i2, animationParamsArr, floatValue, new Vb.b(i7, 1));
                if (!this.mIsVideoRecording && this.m_animation.isEmbossing() && i7 == this.m_foregroundLayer) {
                    float max = Math.max(animationParams.layerPosition[i7].z, this.m_embossingZ);
                    this.m_embossingZ = max;
                    animationParams.layerPosition[i7].z = max;
                }
                animationParams.layerDirection[i7] = movement.slerpVector3(i2, animationParamsArr, floatValue, new Vb.b(i7, 2));
            }
            animationParams.depthIntensity = movement.getFloatInterpolate(i2, animationParamsArr, floatValue, new n(24)).floatValue();
            animationParams.fov = movement.getFloatInterpolate(i2, animationParamsArr, floatValue, new n(25)).floatValue();
            animationParams.bokehIntensity = movement.getFloatInterpolate(i2, animationParamsArr, floatValue, new n(26)).floatValue();
            animationParams.bokehFocusDepth = movement.getFloatInterpolate(i2, animationParamsArr, floatValue, new u((layerInfo.minDepth + layerInfo.maxDepth) * 0.5f, 1)).floatValue();
            animationParams.bokehFocusRange = movement.getFloatInterpolate(i2, animationParamsArr, floatValue, new n(27)).floatValue();
        }
        return animationParams;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Vector3 lambda$getInterpolatedParams$1(AnimationParams animationParams) {
        if (animationParams.autoCameraDir.booleanValue()) {
            return calculateAutoLookAt(animationParams);
        }
        return animationParams.cameraDir;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Vector3 lambda$getInterpolatedParams$3(int i2, AnimationParams animationParams) {
        return animationParams.layerPosition[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Vector3 lambda$getInterpolatedParams$4(int i2, AnimationParams animationParams) {
        return animationParams.layerDirection[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Float lambda$getInterpolatedParams$8(float f, AnimationParams animationParams) {
        if (!animationParams.autoBokehFocusDepth.booleanValue()) {
            f = animationParams.bokehFocusDepth;
        }
        return Float.valueOf(f);
    }

    public void computeAnimation(int i2, int i7, int i8, int i10) {
        this.m_animation.compute(i2, i7, i8, i10);
    }

    public void consumeNextAnimationTime() {
        this.m_animation.getAnimationTime().consumeByDeltaTime();
    }

    public Animation getAnimation() {
        return this.m_animation;
    }

    public AnimationParams getAnimationParams() {
        Animation animation = this.m_animation;
        if (animation == null) {
            return new AnimationParams();
        }
        AnimationParams[] animationParams = animation.getAnimationParams();
        if (animationParams == null) {
            return new AnimationParams();
        }
        AnimationParams[] animationParamsArr = (AnimationParams[]) animationParams.clone();
        if (animationParams.length == 1) {
            return animationParams[0];
        }
        float transitionTime = this.m_animation.getTransition().transitionTime(Math.min(Math.max(this.m_animation.getAnimationTime().getCurrentTime(), 0.0f), 1.0f));
        AnimationParams interpolatedParams = getInterpolatedParams(getAnimationParamIndex(animationParamsArr, transitionTime), animationParamsArr, transitionTime);
        interpolatedParams.dynamicOffset = this.m_animation.getDynamicOffset().getDynamicOffset(this.m_animation.getAnimationTime().getOverallTime());
        return interpolatedParams;
    }

    public void resetAnimation() {
        this.m_lastIndex = 0;
        resetAnimationTime();
    }

    public void resetAnimationTime() {
        this.m_animation.reset();
        this.m_embossingZ = -1.0f;
    }

    public void setAnimation(Animation animation) {
        this.m_animation = animation;
        resetAnimation();
    }

    public void setCameraLookAt(Vector3 vector3) {
        this.m_animation.setCameraLookAt(vector3);
    }

    public void setForegroundLayer(int i2) {
        this.m_foregroundLayer = i2;
    }

    public void setLayerInfo(List<LayerInfo> list) {
        this.m_layerInfo = list;
    }

    public void setVideoRecording(boolean z) {
        this.mIsVideoRecording = z;
    }

    public void updateDeltaTime(float f) {
        float f5;
        if (this.mIsVideoRecording) {
            f5 = 1.0f;
        } else {
            f5 = 0.5f;
        }
        float speedFactor = this.m_animation.getSpeedFactor() * f * f5;
        LogUtil.i(TAG, StringUtil.format("updateDeltaTime: name[%s]: isVideoRecording[%b], deltaTime[%f], scaleFactor[%f], globalSpeed[%f]", this.m_animation.getName(), Boolean.valueOf(this.mIsVideoRecording), Float.valueOf(speedFactor), Float.valueOf(f5), Float.valueOf(f)));
        this.m_animation.getAnimationTime().setDeltaTime(speedFactor);
    }
}
