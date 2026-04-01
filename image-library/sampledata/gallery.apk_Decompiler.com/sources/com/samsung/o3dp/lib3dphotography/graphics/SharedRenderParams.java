package com.samsung.o3dp.lib3dphotography.graphics;

import com.samsung.o3dp.lib3dphotography.animation.AnimationParams;
import com.samsung.o3dp.lib3dphotography.animation.Animator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedRenderParams {
    private final long m_renderParamsPtr;

    public SharedRenderParams(long j2) {
        this.m_renderParamsPtr = j2;
    }

    private static native float nativeGetBokehFocusDepth(long j2);

    private static native float nativeGetBokehFocusRange(long j2);

    private static native float nativeGetBokehIntensity(long j2);

    private static native float[] nativeGetCameraDir(long j2);

    private static native float[] nativeGetCameraEye(long j2);

    private static native float[] nativeGetCameraUp(long j2);

    private static native float nativeGetDepthIntensity(long j2);

    private static native float nativeGetFov(long j2);

    private static native float[] nativeGetLayerDirection(long j2, int i2);

    private static native float[] nativeGetLayerPosition(long j2, int i2);

    private static native void nativeSetBokehFocusDepth(long j2, float f);

    private static native void nativeSetBokehFocusRange(long j2, float f);

    private static native void nativeSetBokehIntensity(long j2, float f);

    private static native void nativeSetCameraDir(long j2, float f, float f5, float f8);

    private static native void nativeSetCameraEye(long j2, float f, float f5, float f8);

    private static native void nativeSetCameraUp(long j2, float f, float f5, float f8);

    private static native void nativeSetDepthIntensity(long j2, float f);

    private static native void nativeSetDynamicOffset(long j2, float f, float f5, float f8);

    private static native void nativeSetDynamicOffsetForegroundOnly(long j2, boolean z);

    private static native void nativeSetFov(long j2, float f);

    private static native void nativeSetLayerDirection(long j2, int i2, float f, float f5, float f8);

    private static native void nativeSetLayerPosition(long j2, int i2, float f, float f5, float f8);

    private static native void nativeSetPanoramaCurvature(long j2, float f);

    public void applyAnimation(Animator animator) {
        AnimationParams animationParams = animator.getAnimationParams();
        setCameraEye(animationParams.cameraEye);
        setCameraDir(animationParams.cameraDir);
        setCameraUp(animationParams.cameraUp);
        setDepthIntensity(animationParams.depthIntensity);
        setFov(animationParams.fov);
        setBokehIntensity(animationParams.bokehIntensity);
        setBokehFocusDepth(animationParams.bokehFocusDepth);
        setBokehFocusRange(animationParams.bokehFocusRange);
        setPanoramaCurvature(animationParams.panoramaCurvature);
        setDynamicOffset(animationParams.dynamicOffset);
        nativeSetDynamicOffsetForegroundOnly(this.m_renderParamsPtr, animator.getAnimation().getDynamicOffset().getForegroundOnly());
        for (int i2 = 0; i2 < 2; i2++) {
            setLayerPosition(i2, animationParams.layerPosition[i2]);
            setLayerDirection(i2, animationParams.layerDirection[i2]);
        }
    }

    public float getBokehFocusDepth() {
        return nativeGetBokehFocusDepth(this.m_renderParamsPtr);
    }

    public float getBokehFocusRange() {
        return nativeGetBokehFocusRange(this.m_renderParamsPtr);
    }

    public float getBokehIntensity() {
        return nativeGetBokehIntensity(this.m_renderParamsPtr);
    }

    public Vector3 getCameraDir() {
        float[] nativeGetCameraDir = nativeGetCameraDir(this.m_renderParamsPtr);
        return new Vector3(nativeGetCameraDir[0], nativeGetCameraDir[1], nativeGetCameraDir[2]);
    }

    public Vector3 getCameraEye() {
        float[] nativeGetCameraEye = nativeGetCameraEye(this.m_renderParamsPtr);
        return new Vector3(nativeGetCameraEye[0], nativeGetCameraEye[1], nativeGetCameraEye[2]);
    }

    public Vector3 getCameraUp() {
        float[] nativeGetCameraUp = nativeGetCameraUp(this.m_renderParamsPtr);
        return new Vector3(nativeGetCameraUp[0], nativeGetCameraUp[1], nativeGetCameraUp[2]);
    }

    public float getDepthIntensity() {
        return nativeGetDepthIntensity(this.m_renderParamsPtr);
    }

    public float getFov() {
        return nativeGetFov(this.m_renderParamsPtr);
    }

    public Vector3 getLayerDirection(int i2) {
        float[] nativeGetLayerDirection = nativeGetLayerDirection(this.m_renderParamsPtr, i2);
        return new Vector3(nativeGetLayerDirection[0], nativeGetLayerDirection[1], nativeGetLayerDirection[2]);
    }

    public Vector3 getLayerPosition(int i2) {
        float[] nativeGetLayerPosition = nativeGetLayerPosition(this.m_renderParamsPtr, i2);
        return new Vector3(nativeGetLayerPosition[0], nativeGetLayerPosition[1], nativeGetLayerPosition[2]);
    }

    public void setBokehFocusDepth(float f) {
        nativeSetBokehFocusDepth(this.m_renderParamsPtr, f);
    }

    public void setBokehFocusRange(float f) {
        nativeSetBokehFocusRange(this.m_renderParamsPtr, f);
    }

    public void setBokehIntensity(float f) {
        nativeSetBokehIntensity(this.m_renderParamsPtr, f);
    }

    public void setCameraDir(Vector3 vector3) {
        nativeSetCameraDir(this.m_renderParamsPtr, vector3.f4210x, vector3.y, vector3.z);
    }

    public void setCameraEye(Vector3 vector3) {
        nativeSetCameraEye(this.m_renderParamsPtr, vector3.f4210x, vector3.y, vector3.z);
    }

    public void setCameraUp(Vector3 vector3) {
        nativeSetCameraUp(this.m_renderParamsPtr, vector3.f4210x, vector3.y, vector3.z);
    }

    public void setDepthIntensity(float f) {
        nativeSetDepthIntensity(this.m_renderParamsPtr, f);
    }

    public void setDynamicOffset(Vector3 vector3) {
        nativeSetDynamicOffset(this.m_renderParamsPtr, vector3.f4210x, vector3.y, vector3.z);
    }

    public void setFov(float f) {
        nativeSetFov(this.m_renderParamsPtr, f);
    }

    public void setLayerDirection(int i2, Vector3 vector3) {
        nativeSetLayerDirection(this.m_renderParamsPtr, i2, vector3.f4210x, vector3.y, vector3.z);
    }

    public void setLayerPosition(int i2, Vector3 vector3) {
        nativeSetLayerPosition(this.m_renderParamsPtr, i2, vector3.f4210x, vector3.y, vector3.z);
    }

    public void setPanoramaCurvature(float f) {
        nativeSetPanoramaCurvature(this.m_renderParamsPtr, f);
    }
}
