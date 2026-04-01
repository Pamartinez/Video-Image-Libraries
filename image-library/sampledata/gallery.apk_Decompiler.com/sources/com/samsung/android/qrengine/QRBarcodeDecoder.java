package com.samsung.android.qrengine;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import zc.a;
import zc.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QRBarcodeDecoder {

    /* renamed from: a  reason: collision with root package name */
    public int f3255a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public final int[] f3256c = new int[5];
    public final int[][] d;
    public final int e;
    public final char[] f;
    public final String[] g;

    /* renamed from: h  reason: collision with root package name */
    public final String[] f3257h;

    /* renamed from: i  reason: collision with root package name */
    public Matrix f3258i;

    /* renamed from: j  reason: collision with root package name */
    public Integer f3259j;
    public final boolean k;

    static {
        System.loadLibrary("QREngine.camera.samsung");
    }

    public QRBarcodeDecoder(a aVar, b bVar) {
        int[] iArr = new int[2];
        iArr[1] = 8;
        iArr[0] = 5;
        Class cls = Integer.TYPE;
        this.d = (int[][]) Array.newInstance(cls, iArr);
        this.f = new char[51];
        this.k = false;
        new ArrayList();
        this.g = new String[10];
        this.f3257h = new String[10];
        int[] iArr2 = new int[2];
        iArr2[1] = 8;
        iArr2[0] = 10;
        this.d = (int[][]) Array.newInstance(cls, iArr2);
        if (!this.k) {
            if (TextUtils.isEmpty("")) {
                this.e = initEngine(aVar.ordinal(), bVar.ordinal());
            } else {
                this.e = initEngineWithModelPath(aVar.ordinal(), bVar.ordinal(), "");
            }
            this.k = true;
            Log.d("SRCB/QRBarcodeDecoder", "init end mEngineId = " + this.e);
        }
    }

    private static native void abortProcessJni();

    public static native String barcodeRecognize(byte[] bArr, int i2, int i7, int i8, int i10, int i11, int i12, int[] iArr, char[] cArr, int i13, int i14, int i15);

    public static native String barcodeRecognizeRGB(byte[] bArr, int i2, int i7, int[] iArr, char[] cArr, int i8);

    private static native Bitmap cropWarpImage(int i2, byte[] bArr, int i7, int i8, int[] iArr, int[] iArr2, int i10, int i11);

    private static native void disableBarcodeTypesJNI(int i2, int[] iArr, int i7);

    private static native void enableBarcodeTypesJNI(int i2, int[] iArr, int i7);

    private static native Bitmap getBitmap(int i2);

    private static native int getHammingDistance(byte[] bArr, int i2, int i7, int i8, int i10);

    private static native int getRecogObjectCount(int i2);

    private static native boolean getRecogObjectPoint(int i2, int i7, int[] iArr);

    private static native String getRecogObjectText(int i2, int i7);

    private static native String getRecogObjectType(int i2, int i7);

    public static native List<Object> getResult(int i2, long j2, int i7);

    private static native int initEngine(int i2, int i7);

    private static native int initEngineWithModelPath(int i2, int i7, String str);

    public static native String recognizeCode(long j2, int i2, byte[] bArr, int[] iArr, char[] cArr, int i7, int i8, int i10, int i11, int i12, int[] iArr2);

    private static native void releaseEngine(int i2);

    private static native void resumeProcessJni();

    private static native void setImageSizeJni(int i2, int i7, int i8, int i10, int i11);

    private static native void setRoiJni(int i2, int i7, int i8, int i10, int i11);

    public final int a() {
        Log.d("SRCB/QRBarcodeDecoder", "getRecognizedObjectCount mEngineId : " + this.e);
        if (TextUtils.isEmpty(this.g[0])) {
            return 0;
        }
        int i2 = 1;
        int i7 = 1;
        while (i2 < 10 && !TextUtils.isEmpty(this.g[i2])) {
            i7++;
            i2++;
        }
        return i7;
    }

    public final void b(int i2, int[] iArr) {
        if (TextUtils.isEmpty(this.g[0])) {
            return;
        }
        if (i2 == 0) {
            System.arraycopy(this.d[0], 0, iArr, 0, iArr.length);
        } else {
            getRecogObjectPoint(this.e, i2, iArr);
        }
    }

    public final String c(int i2) {
        if (i2 == 0) {
            return this.g[0];
        }
        return getRecogObjectText(this.e, i2);
    }

    public final String d(int i2) {
        if (i2 == 0) {
            return this.f3257h[0];
        }
        return getRecogObjectType(this.e, i2);
    }

    public final void e() {
        releaseEngine(this.e);
    }

    public native String getSoVersion();
}
