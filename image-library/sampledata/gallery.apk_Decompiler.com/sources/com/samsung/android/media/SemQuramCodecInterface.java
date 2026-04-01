package com.samsung.android.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemQuramCodecInterface {
    public static native Bitmap nativeDecodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options);

    public static native Bitmap nativeDecodeByteArrayST(byte[] bArr, int i2, int i7, BitmapFactory.Options options);

    public static native Bitmap nativeDecodeFile(String str, BitmapFactory.Options options);

    public static native Bitmap nativeDecodeFileST(String str, BitmapFactory.Options options);

    public static native Bitmap nativeDecodePreview(String str);

    public static native byte[] nativeGetDNGPreviewImageFromByteArray(byte[] bArr, int i2, int i7);

    public static native byte[] nativeGetDNGPreviewImageFromFile(String str);

    public static native String nativeGetDNGPrivateDataFromByteArray(byte[] bArr, int i2, int i7);

    public static native String nativeGetDNGPrivateDataFromFile(String str);

    public static native int nativeGetVersion();

    public static native int nativeParseMetadata(String str, SemQuramCodecMetadata semQuramCodecMetadata);

    public static native int nativeParseMetadataBA(byte[] bArr, int i2, SemQuramCodecMetadata semQuramCodecMetadata);

    public static native int testMode();
}
