package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class UxTune {
    private static float TEST_FLOAT_VALUE = 0.0f;
    private static int TEST_INT_VALUE = 0;
    private static float[] TEST_PATH_INTERPOLATOR = {0.17f, 0.17f, 0.4f, 1.0f};
    private static int testValue = 500;

    public static void setInterpolator(float f, float f5, float f8, float f10) {
        float[] fArr = TEST_PATH_INTERPOLATOR;
        fArr[0] = f;
        fArr[1] = f5;
        fArr[2] = f8;
        fArr[3] = f10;
    }

    public static void setTestFloatValue(float f) {
        TEST_FLOAT_VALUE = f;
    }

    public static void setTestIntValue(int i2) {
        TEST_INT_VALUE = i2;
    }
}
