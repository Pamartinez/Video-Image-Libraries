package com.samsung.android.sdk.pen.ocr;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrRecognitionListener {
    private static final String TAG = "SpenOcrRecognitionListener";
    private long mNativeHandle;

    public SpenOcrRecognitionListener() {
        this.mNativeHandle = 0;
        this.mNativeHandle = Native_init(this);
        Log.i(TAG, "SpenOcrRecognitionListener is created! mNativeHandle : " + Long.toHexString(this.mNativeHandle));
    }

    private native void Native_finalize(long j2);

    private native long Native_init(SpenOcrRecognitionListener spenOcrRecognitionListener);

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

    public void onPageRecognitionCompleted(SpenOcrPageData spenOcrPageData) {
        Log.i(TAG, "SpenOcrRecognitionListener onPageRecognitionCompleted");
    }

    public void onPageRecognitionStarted() {
        Log.i(TAG, "SpenOcrRecognitionListener onPageRecognitionStarted");
    }

    public void onTextBlockDetected(SpenOcrBlockData spenOcrBlockData) {
        Log.i(TAG, "SpenOcrRecognitionListener onTextBlockDetected");
    }

    public void onTextLineDetected(SpenOcrLineData spenOcrLineData) {
        Log.i(TAG, "SpenOcrRecognitionListener onTextLineDetected");
    }

    public void onTextLineRecognized(SpenOcrLineData spenOcrLineData) {
        Log.i(TAG, "SpenOcrRecognitionListener onTextLineRecognized");
    }
}
