package com.samsung.android.qrengine;

import android.graphics.Bitmap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QRBarcodeEncoder {
    static {
        System.loadLibrary("QREngine.camera.samsung");
    }

    private static native Bitmap generateAppQR(String str, int i2, int i7, int[] iArr);

    private static native Bitmap generateQR(String str, int i2, int i7);

    private static native Bitmap generateQRlogo(String str, int i2, int i7, int i8, Bitmap bitmap, int i10, int i11, int[] iArr);
}
