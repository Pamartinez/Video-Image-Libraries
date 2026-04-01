package com.samsung.o3dp.lib3dphotography.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class O3DPConstant {
    public static final float BASE_FRAME_RATE = 30.0f;
    public static final float DEFAULT_ANIMATION_SPEED = 1.0f;
    public static final int DEFAULT_NUMBER_OF_DEPTH_BINS = 2;
    public static final int DIR_BOTTOM = 2;
    public static final int DIR_LEFT = 4;
    public static final int DIR_NONE = 0;
    public static final int DIR_RIGHT = 8;
    public static final int DIR_TOP = 1;
    public static final int MAX_ROI_SHORT_SIDE = 1080;
    public static final float MIN_BODY_SIZE_RATIO = 0.16666667f;
    public static final float MIN_DEFORMABLE_OBJECT_SIZE_RATIO = 0.16666667f;
    public static final float MIN_HUMAN_FACE_SIZE_RATIO = 0.083333336f;
    public static final float MIN_PET_FACE_SIZE_RATIO = 0.11111111f;
    public static final int MIN_ROI_SIDE = 500;
    public static final String MP4_EXTENSION = ".mp4";
    public static final float PANORAMA_MAX_LENGTH = 9720.0f;
    public static final float PANORAMA_MIN_LENGTH = 1080.0f;
    public static final int RENDER_FRAME_RATE = 60;
    public static final int SUPPORT_MIRACLEFILTER_MAXIMUM = 1000000;
    public static final float TARGET_ASPECT_RATIO = 2.4444444f;
    public static final float THRESHOLD_DYNAMICS_ACCELEROMETER = 10.0f;
    public static final float THRESHOLD_IMAGE_BOUNDARY_BOTTOM = 0.1f;
    public static final float THRESHOLD_IMAGE_BOUNDARY_LEFT = 0.1f;
    public static final float THRESHOLD_IMAGE_BOUNDARY_RIGHT = 0.1f;
    public static final float THRESHOLD_IMAGE_BOUNDARY_TOP = 0.2f;
    public static final int VIDEO_FRAME_RATE = 30;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface Direction {
    }

    private O3DPConstant() {
        throw new AssertionError();
    }
}
