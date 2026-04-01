package com.samsung.o3dp.lib3dphotography.nativelib;

import android.graphics.Bitmap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JNI extends NativeInterface {
    static {
        System.loadLibrary("OpenCv.camera.samsung");
    }

    public static native int calcBottomDistance(Bitmap bitmap, int i2, int i7);

    public static native float[] cardinalSpline(float f, float f5, float f8, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20);

    public static native boolean compositeForegroundSegments(List<Bitmap> list, Bitmap bitmap, float[] fArr, int i2, int i7);

    public static native boolean convertBgrToNv21(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native boolean convertBitmapToBgr(Bitmap bitmap, byte[] bArr);

    public static native boolean copyAlpha(Bitmap bitmap, Bitmap bitmap2);

    public static native int countTransparentPixels(Bitmap bitmap, int i2);

    public static native String findFrontObjectUsingDepth(float[] fArr, int i2, int i7, Bitmap bitmap, List<Integer> list);

    public static native boolean isClipperEnabled(long j2);

    public static native boolean isSegmentedObjectBehind(float[] fArr, int i2, int i7, Bitmap bitmap);

    public static native boolean refineFPInSegmentImg(float[] fArr, Bitmap bitmap, Bitmap bitmap2, int i2, int i7);

    public static native void runFaceDetector(byte[] bArr, int i2, int i7, List<String> list, List<String> list2);
}
