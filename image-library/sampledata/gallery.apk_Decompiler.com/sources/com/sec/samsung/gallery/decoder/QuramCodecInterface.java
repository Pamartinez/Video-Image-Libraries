package com.sec.samsung.gallery.decoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Trace;
import android.util.Log;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuramCodecInterface {
    private static Boolean sLibraryLoaded;

    static {
        loadLibrary();
    }

    public static int getVersion() {
        try {
            int nativeGetVersion = nativeGetVersion();
            Log.d("QuramCodecInterface", "version=" + nativeGetVersion);
            return nativeGetVersion;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("version failed e="), "QuramCodecInterface");
            return 0;
        }
    }

    public static boolean loadLibrary() {
        if (sLibraryLoaded == null) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                Trace.beginSection("imagecodec.quram".concat("-loading"));
                System.loadLibrary("imagecodec.quram");
                sLibraryLoaded = Boolean.TRUE;
                Log.i("QuramCodecInterface", "Quram library loaded +" + (System.currentTimeMillis() - currentTimeMillis));
            } catch (SecurityException | UnsatisfiedLinkError unused) {
                Log.e("QuramCodecInterface", "Quram library load failed");
                sLibraryLoaded = Boolean.FALSE;
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
            Trace.endSection();
        }
        return sLibraryLoaded.booleanValue();
    }

    public static native Bitmap nativeDecodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options);

    public static native Bitmap nativeDecodeByteArrayST(byte[] bArr, int i2, int i7, BitmapFactory.Options options);

    public static native Bitmap nativeDecodeFile(String str, BitmapFactory.Options options);

    public static native Bitmap nativeDecodeFileST(String str, BitmapFactory.Options options);

    public static native Bitmap nativeDecodePreview(String str);

    public static native byte[] nativeGetDNGPreviewImageFromByteArray(byte[] bArr, int i2, int i7);

    public static native byte[] nativeGetDNGPreviewImageFromFile(String str);

    public static native String nativeGetDNGPrivateDataFromByteArray(byte[] bArr, int i2, int i7);

    public static native String nativeGetDNGPrivateDataFromFile(String str);

    public static native int nativeGetSECDngVersionFromByteArray(byte[] bArr, int i2, int i7);

    public static native int nativeGetSECDngVersionFromFile(String str);

    public static native int nativeGetVersion();

    public static native int nativeParseMetadata(String str, QuramCodecMetadata quramCodecMetadata);

    public static native int nativeParseMetadataBA(byte[] bArr, int i2, QuramCodecMetadata quramCodecMetadata);
}
