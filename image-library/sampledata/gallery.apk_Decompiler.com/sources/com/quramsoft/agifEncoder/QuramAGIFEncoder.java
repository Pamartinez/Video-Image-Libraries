package com.quramsoft.agifEncoder;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuramAGIFEncoder {
    protected long mHandle = 0;

    static {
        loadLib();
    }

    public QuramAGIFEncoder() {
        nativeInitHandle(this);
    }

    public static void loadLib() {
        try {
            System.loadLibrary("agifencoder.quram");
        } catch (Exception e) {
            Log.e("QuramAGIFEncoder", "Load library fail : " + e.toString());
        }
    }

    public static native ByteArrayOutputStream nativeMakeContactBuffer(byte[] bArr, int i2, int i7, int i8);

    public static native int nativeMakeContactImage(String str, String str2, int i2);

    public static native int nativeMakeContactImageRect(String str, String str2, int i2, int i7, int i8, int i10);

    public static native ByteArrayOutputStream nativeMakeContactRectBuffer(byte[] bArr, int i2, int i7, int i8, int i10, int i11, int i12);

    public static native ByteArrayOutputStream nativeMakeContactRectResizeBuffer(byte[] bArr, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14);

    public static native int nativeMakeContactResizeImageRect(String str, String str2, int i2, int i7, int i8, int i10, int i11, int i12);

    public boolean addFrameTP(Bitmap bitmap) {
        if (bitmap != null) {
            setSize(bitmap.getWidth(), bitmap.getHeight());
        }
        return nativeAddFrameTP(this.mHandle, bitmap);
    }

    public boolean finish() {
        return nativeFinish(this.mHandle);
    }

    public native boolean nativeAddFrame(long j2, Bitmap bitmap);

    public native boolean nativeAddFrameMP(long j2, Bitmap bitmap);

    public native boolean nativeAddFrameTP(long j2, Bitmap bitmap);

    public native boolean nativeFinish(long j2);

    public native void nativeInitHandle(QuramAGIFEncoder quramAGIFEncoder);

    public native void nativeSetColorCount(long j2, int i2);

    public native void nativeSetDelay(long j2, int i2);

    public native void nativeSetDispose(long j2, int i2);

    public native void nativeSetDither(long j2, int i2);

    public native void nativeSetFrameRate(long j2, float f);

    public native void nativeSetGlobalSize(long j2, int i2, int i7);

    public native void nativeSetMaxResolution(long j2, int i2);

    public native void nativeSetMaxTask(long j2, int i2);

    public native void nativeSetMaxTaskTP(long j2, int i2);

    public native void nativeSetPosition(long j2, int i2, int i7);

    public native void nativeSetQuality(long j2, int i2);

    public native void nativeSetRepeat(long j2, int i2);

    public native void nativeSetSize(long j2, int i2, int i7);

    public native void nativeSetThreshold(long j2, int i2);

    public native void nativeSetTransPair(long j2, int i2);

    public native void nativeSetTransparent(long j2, int i2);

    public native void nativeSetWriteFunc(long j2, int i2);

    public native boolean nativeStart(long j2, String str);

    public native boolean nativeStartFD(long j2, FileDescriptor fileDescriptor);

    public void setDelay(int i2) {
        nativeSetDelay(this.mHandle, i2);
    }

    public void setDispose(int i2) {
        nativeSetDispose(this.mHandle, i2);
    }

    public void setGlobalSize(int i2, int i7) {
        nativeSetGlobalSize(this.mHandle, i2, i7);
    }

    public void setMaxTaskTP(int i2) {
        nativeSetMaxTaskTP(this.mHandle, i2);
    }

    public void setPosition(int i2, int i7) {
        nativeSetPosition(this.mHandle, i2, i7);
    }

    public void setRepeat(int i2) {
        nativeSetRepeat(this.mHandle, i2);
    }

    public void setSize(int i2, int i7) {
        nativeSetSize(this.mHandle, i2, i7);
    }

    public void setTransparent(int i2) {
        nativeSetTransparent(this.mHandle, i2);
    }

    public boolean start(String str) {
        return nativeStart(this.mHandle, str);
    }
}
