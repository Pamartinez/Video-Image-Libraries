package com.samsung.android.portrait.imagehandler;

import android.graphics.Bitmap;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageHandlerJNI {
    static {
        try {
            Log.i("ImageHandlerJNI", "Load libPortraitSolution.camera.samsung E");
            System.loadLibrary("PortraitSolution.camera.samsung");
            Log.i("ImageHandlerJNI", "Load libPortraitSolution.camera.samsung X");
        } catch (Exception e) {
            Log.e(e.getClass().getName(), e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static native int convertARGBtoNV21(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native int convertBitmaptoARGB(Bitmap bitmap, byte[] bArr);

    public static native int convertBitmaptoNV21(Bitmap bitmap, byte[] bArr);

    public static native int convertNV21toARGB(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native int convertNV21toBitmap(byte[] bArr, byte[] bArr2, int i2, int i7);

    public static native int loadARGBFromFile(String str, byte[] bArr);

    public static native int loadNV21FromFile(String str, byte[] bArr);

    public static native int loadResizedARGBFromFile(String str, int i2, int i7, byte[] bArr);

    public static native byte[] makeByteArray(int i2);

    public static native int releaseByteArray(byte[] bArr);

    public static native int resizeNV21(byte[] bArr, byte[] bArr2, int i2, int i7, int i8, int i10);

    public static native int saveARGBToFile(String str, byte[] bArr, int i2, int i7);

    public static native int saveNV21ToFile(String str, byte[] bArr, int i2, int i7, int i8, int i10);
}
