package com.samsung.android.photoremaster.engine.nativeInterface;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VSWEngineNativeInterface {
    private static final String TAG = "VSWEngineNativeInterface";

    static {
        try {
            System.loadLibrary("RemasterEngine.camera.samsung");
        } catch (Exception e) {
            Log.e(e.getClass().getName(), e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static native int loadBGRFromFile(String str, byte[] bArr);

    public static native byte[] makeByteArray(int i2);

    public static void printLibraryVersion() {
        Log.i(TAG, "libRemasterEngine.aar - v1.1.1, Build 2023.June.8");
        printNativeLibraryVersion();
    }

    public static native void printNativeLibraryVersion();

    public static native int releaseByteArray(byte[] bArr);

    public static native boolean runBlurEnhancer(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native boolean runBlurEstimator(byte[] bArr, int i2, int i7);

    public static native boolean runClarityEnhancer(byte[] bArr, byte[] bArr2, int i2, int i7, int i8);

    public static native boolean runDistortionEnhancer(byte[] bArr, byte[] bArr2, String str, boolean[] zArr, byte[] bArr3, int i2, int i7);

    public static native boolean runDistortionEstimator(byte[] bArr, byte[] bArr2, boolean[] zArr, int i2, int i7);

    public static native boolean runFaceRestorationEnhancer(byte[] bArr, byte[] bArr2, int i2, int i7, int i8);

    public static native boolean runGraphicEstimator(byte[] bArr, int i2, int i7);

    public static native boolean runHDREnhancer(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native boolean runLowLightEstimator(byte[] bArr, int i2, int i7);

    public static native boolean runMQABlurEstimator(byte[] bArr, float[] fArr, int i2, int i7);

    public static native boolean runMQANoiseEstimator(byte[] bArr, float[] fArr, int i2, int i7);

    public static native boolean runMQAQualityEstimator(byte[] bArr, float[] fArr, int i2, int i7);

    public static native boolean runMoireEnhancer(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native boolean runMoireEstimator(byte[] bArr, int i2, int i7);

    public static native boolean runMoireEstimatorWithScore(byte[] bArr, float[] fArr, int i2, int i7);

    public static native boolean runNoiseEnhancer(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native boolean runNoiseEstimator(byte[] bArr, int i2, int i7);

    public static native boolean runResolutionEnhancer(byte[] bArr, byte[] bArr2, int i2, int i7, int i8);

    public static native boolean runUWDistortionEnhancer(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i2, int i7);

    public static native boolean runUWDistortionEstimator(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native boolean runUnifiedEnhancer(byte[] bArr, byte[] bArr2, int i2, int i7, int i8);

    public static native int saveBGRToFile(String str, byte[] bArr, int i2, int i7);

    public static native void stopEnhancer();
}
