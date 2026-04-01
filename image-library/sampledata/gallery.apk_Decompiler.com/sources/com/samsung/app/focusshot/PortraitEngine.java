package com.samsung.app.focusshot;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PortraitEngine {
    static {
        try {
            System.loadLibrary("PortraitSolution.camera.samsung");
            Log.i("SIOFV_PortraitEngine", "Library Loaded");
        } catch (Exception | UnsatisfiedLinkError e) {
            Log.e(e.getClass().getName(), e.getMessage(), e);
            Log.e("SIOFV_PortraitEngine", "Library load failed");
            e.printStackTrace();
        }
    }

    public static native boolean DeinitEngine();

    public static native boolean InitEngine(long j2, int i2, int i7, long j3, long j8, long j10, long j11, long j12, int i8);

    public static native boolean InitEngineV2(long j2, int i2, int i7, long j3, long j8, long j10, long j11, long j12, long j13, long j14, int i8);

    public static native boolean RunCapture(long j2);

    public static native boolean RunPreview(long j2);

    public static native boolean SetMode(int i2);

    public static native boolean SetProperty(int i2, int i7);
}
