package com.sec.samsung.gallery.decoder;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuramCodec {
    static {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            System.loadLibrary("JPEGProgFinder.quram");
            Log.i("QuramCodec", "JPF.quram library loaded +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (SecurityException | UnsatisfiedLinkError e) {
            Log.e("QuramCodec", "JPF.quram library load failed", e);
        }
    }

    public static boolean isJpegSyntaxCompatible(byte[] bArr, int i2, int i7) {
        return nativeCheckIssueProgressive(bArr, i2, i7) == 0;
    }

    public static native int nativeCheckIssueProgressive(byte[] bArr, int i2, int i7);

    public static native int nativeCheckIssueProgressiveFile(String str);

    public static boolean isJpegSyntaxCompatible(String str) {
        return nativeCheckIssueProgressiveFile(str) == 0;
    }
}
