package com.samsung.android.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemQuramCodecRegionDecoder {
    public static native Bitmap nativeDecodeRegion(long j2, Rect rect, BitmapFactory.Options options);

    public static native int nativeGetCropOriginX(long j2);

    public static native int nativeGetCropOriginY(long j2);

    public static native int nativeGetCropSizeHeight(long j2);

    public static native int nativeGetCropSizeWidth(long j2);

    public static native int nativeGetHeight(long j2);

    public static native String nativeGetMake(long j2);

    public static native int nativeGetWidth(long j2);

    public static native long nativeNewInstance(String str, BitmapFactory.Options options);

    public static native long nativeNewInstanceByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options);

    public static native int nativeRecycle(long j2);

    public static native int nativeRegionCancel(long j2);
}
