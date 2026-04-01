package com.samsung.android.sdk.pen.ocr;

import N2.j;
import android.graphics.Bitmap;
import android.util.Log;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenTypeClassifier implements SpenITypeClassifier {
    private static final String TAG = "SpenTypeClassifier";
    private long mNativeHandle;

    public SpenTypeClassifier(long j2) {
        this.mNativeHandle = j2;
        Log.i(TAG, "SpenTypeClassifier is created! [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
    }

    private native void Native_finalize(long j2);

    private native boolean Native_isHandwrittenImage(long j2, Bitmap bitmap);

    private native boolean Native_isPrintedImage(long j2, Bitmap bitmap);

    private boolean isDebugMode() {
        try {
            return new File("/data/local/tmp/isHandwritten.test").exists();
        } catch (Exception e) {
            j.D(e, new StringBuilder("Fail to check debug file! : "), TAG);
            return false;
        }
    }

    public void close() {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            Native_finalize(j2);
        }
        this.mNativeHandle = 0;
    }

    public void finalize() {
        super.finalize();
        close();
    }

    public boolean isHandwrittenImage(Bitmap bitmap) {
        if (this.mNativeHandle == 0) {
            return false;
        }
        if (isDebugMode()) {
            return true;
        }
        return Native_isHandwrittenImage(this.mNativeHandle, bitmap);
    }

    public boolean isPrintedImage(Bitmap bitmap) {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            return Native_isPrintedImage(j2, bitmap);
        }
        return false;
    }
}
