package com.samsung.android.portrait.imagehandler;

import android.graphics.Bitmap;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BufferHandler {
    static {
        try {
            Log.i("BufferHandler", "Load libPortraitSolution.camera.samsung E");
            System.loadLibrary("PortraitSolution.camera.samsung");
            Log.i("BufferHandler", "Load libPortraitSolution.camera.samsung X");
        } catch (Exception e) {
            Log.e(e.getClass().getName(), e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static native long AllocateBuffer(long j2, byte[] bArr);

    public static native void ConvertNV21AndWriteToBitmap(long j2, long j3, int i2, int i7, Bitmap bitmap);

    public static native void ConvertNV21AndWriteToHalfBitmap(long j2, long j3, int i2, int i7, Bitmap bitmap);

    public static native long DecodeJpegBufferToNV21(long j2, byte[] bArr, int i2, int i7);

    public static native void Deinit(long j2);

    public static native void GetARGBBufAsIntArray(long j2, long j3, int[] iArr, int i2, int i7);

    public static native void GetBufferAsByteArray(long j2, long j3, byte[] bArr, int i2);

    public static native void GetBufferAsIntArray(long j2, long j3, int[] iArr, int i2, int i7);

    public static native long InitLib();

    public static native void ReleaseBuffer(long j2, long j3);

    public static native void SaveToTempJpeg(long j2, long j3, int i2, int i7, String str);

    public static native void WriteToBitmap(long j2, long j3, int i2, int i7, Bitmap bitmap);
}
