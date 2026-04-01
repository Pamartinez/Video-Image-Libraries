package com.samsung.android.sdk.pen.ocr;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SpenOcrRecognizer implements SpenIOcrRecognizer {
    private static final String TAG = "SpenOcrRecognizer";
    private long mNativeHandle;

    public SpenOcrRecognizer(long j2) {
        this.mNativeHandle = j2;
        Log.i(TAG, "SpenOcrRecognizer is created! [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        StringBuilder sb2 = new StringBuilder("Native Version : ");
        sb2.append(getVersion());
        Log.i(TAG, sb2.toString());
    }

    private native void Native_cancelRequest(long j2);

    private native int Native_detect(long j2, Bitmap bitmap);

    private native int Native_detect(long j2, Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener);

    private native void Native_finalize(long j2);

    private native String Native_getVersion(long j2);

    private native int Native_recognize(long j2, Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrLineData spenOcrLineData);

    private native int Native_recognize(long j2, Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener);

    private native int Native_recognize_block_at(long j2, Bitmap bitmap, int i2, int i7, boolean z, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener);

    private native void Native_setConfiguration(long j2, String[] strArr, String[] strArr2);

    public void cancelRequest() {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            Native_cancelRequest(j2);
        }
    }

    public void close() {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            Native_finalize(j2);
        }
        this.mNativeHandle = 0;
    }

    public SpenOcrError detect(Bitmap bitmap) {
        Log.i(TAG, "Start detect(Bitmap) of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        if (this.mNativeHandle == 0) {
            return SpenOcrError.OE_RecogError;
        }
        SpenOcrError spenOcrError = SpenOcrError.values()[Native_detect(this.mNativeHandle, bitmap)];
        Log.i(TAG, "End of detect(Bitmap) of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        return spenOcrError;
    }

    public void finalize() {
        super.finalize();
        close();
    }

    public String getVersion() {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            return Native_getVersion(j2);
        }
        return null;
    }

    public SpenOcrError recognize(Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener) {
        Log.i(TAG, "Start recognize() of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        if (this.mNativeHandle == 0) {
            return SpenOcrError.OE_RecogError;
        }
        SpenOcrError spenOcrError = SpenOcrError.values()[Native_recognize(this.mNativeHandle, bitmap, spenRecogConfig, spenOcrRecognitionListener)];
        Log.i(TAG, "End of SpenOcrRecognizer::recognize() of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        return spenOcrError;
    }

    public SpenOcrError recognizeBlockAt(Bitmap bitmap, Point point, boolean z, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener) {
        Log.i(TAG, "Start recognizeBlockAt(" + point.x + GlobalPostProcInternalPPInterface.SPLIT_REGEX + point.y + ") of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        if (this.mNativeHandle == 0) {
            return SpenOcrError.OE_RecogError;
        }
        SpenOcrError spenOcrError = SpenOcrError.values()[Native_recognize_block_at(this.mNativeHandle, bitmap, point.x, point.y, z, spenRecogConfig, spenOcrRecognitionListener)];
        Log.i(TAG, "End of SpenOcrRecognizer::recognizeBlockAt() of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        return spenOcrError;
    }

    public SpenOcrError recognize_line(Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrLineData spenOcrLineData) {
        Log.i(TAG, "Start recognize_line() of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        if (this.mNativeHandle == 0) {
            return SpenOcrError.OE_RecogError;
        }
        SpenOcrError spenOcrError = SpenOcrError.values()[Native_recognize(this.mNativeHandle, bitmap, spenRecogConfig, spenOcrLineData)];
        Log.i(TAG, "End of recognize_line() of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        return spenOcrError;
    }

    public void setConfiguration(String str, String str2) {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            Native_setConfiguration(j2, new String[]{str}, new String[]{str2});
        }
    }

    public void setConfiguration(String[] strArr, String[] strArr2) {
        long j2 = this.mNativeHandle;
        if (j2 != 0) {
            Native_setConfiguration(j2, strArr, strArr2);
        }
    }

    public SpenOcrError detect(Bitmap bitmap, SpenRecogConfig spenRecogConfig, SpenOcrRecognitionListener spenOcrRecognitionListener) {
        Log.i(TAG, "Start detect() of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        if (this.mNativeHandle == 0) {
            return SpenOcrError.OE_RecogError;
        }
        SpenOcrError spenOcrError = SpenOcrError.values()[Native_detect(this.mNativeHandle, bitmap, spenRecogConfig, spenOcrRecognitionListener)];
        Log.i(TAG, "End of detect() of SpenOcrRecognizer [mNativeHandle : " + Long.toHexString(this.mNativeHandle) + "]");
        return spenOcrError;
    }
}
