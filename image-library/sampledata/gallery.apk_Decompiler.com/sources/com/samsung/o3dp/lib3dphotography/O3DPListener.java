package com.samsung.o3dp.lib3dphotography;

import com.samsung.o3dp.lib3dphotography.animation.Animation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class O3DPListener {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ErrorType {
        UNKNOWN,
        DEPTH_ESTIMATION_FAIL,
        SEGMENTATION_FAIL,
        ESTIMATING_FAIL,
        RENDERING_FAIL,
        VIDEO_RECORDING_FAIL,
        VIDEO_RECORDING_ABORTED,
        INPUT_INVALID_FAIL,
        ABNORMAL_USE,
        BLOCKED_ANIMATION,
        JPEG3D_RECORDING_FAIL,
        JPEG3D_ENCODING_FAIL
    }

    public abstract void onFailed(ErrorType errorType, String str);

    public void onRecommendedAnimation(Animation animation) {
    }

    public void onRecommendedAnimation(String str) {
    }

    public void onDeformableObjectDetected() {
    }

    public void onRenderReady() {
    }

    public void onRenderStart() {
    }

    public void onRenderUpdate() {
    }
}
