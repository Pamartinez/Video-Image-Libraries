package com.samsung.o3dp.lib3dphotography.animation;

import com.samsung.o3dp.lib3dphotography.graphics.Vector3;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AnimationParams {
    public static final int MAX_LAYERS = 2;
    public Boolean autoBokehFocusDepth;
    public Boolean autoCameraDir;
    public float bokehFocusDepth;
    public float bokehFocusRange;
    public float bokehIntensity;
    public Vector3 cameraDir;
    public Vector3 cameraEye;
    public Vector3 cameraUp;
    public float depthIntensity;
    public Vector3 dynamicOffset;
    public float fov;
    public Vector3[] layerDirection;
    public Vector3[] layerPosition;
    public float panoramaCurvature;
    public Float time = null;

    public AnimationParams() {
        Boolean bool = Boolean.FALSE;
        this.autoCameraDir = bool;
        this.cameraEye = new Vector3(0.0f, 0.0f, 1.0f);
        this.cameraDir = new Vector3(0.0f, 0.0f, -1.0f);
        this.cameraUp = new Vector3(0.0f, 1.0f, 0.0f);
        this.dynamicOffset = new Vector3(0.0f, 0.0f, 0.0f);
        this.layerPosition = new Vector3[2];
        this.layerDirection = new Vector3[2];
        this.depthIntensity = 1.0f;
        this.fov = 58.5f;
        this.autoBokehFocusDepth = bool;
        this.bokehIntensity = 1.0f;
        this.bokehFocusDepth = 1.0f;
        this.bokehFocusRange = 0.1f;
        this.panoramaCurvature = 1.0f;
        for (int i2 = 0; i2 < 2; i2++) {
            this.layerPosition[i2] = new Vector3(0.0f, 0.0f, 0.0f);
            this.layerDirection[i2] = new Vector3(0.0f, 0.0f, 1.0f);
        }
    }
}
