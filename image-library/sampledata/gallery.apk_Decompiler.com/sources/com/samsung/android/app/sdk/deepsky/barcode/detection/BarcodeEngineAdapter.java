package com.samsung.android.app.sdk.deepsky.barcode.detection;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.Log;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.barcode.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.barcode.logger.LogUtil;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.qrengine.QRBarcodeDecoder;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;
import zc.a;
import zc.b;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/detection/BarcodeEngineAdapter;", "Lcom/samsung/android/app/sdk/deepsky/barcode/detection/BarcodeEngine;", "<init>", "()V", "barcodeDecoder", "Lcom/samsung/android/qrengine/QRBarcodeDecoder;", "process", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/detection/BarcodeScanResult;", "bitmap", "Landroid/graphics/Bitmap;", "isDecodeAvailable", "", "getBarcodeScanResult", "getQRBarcodeDecoder", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BarcodeEngineAdapter implements BarcodeEngine {
    public static final Companion Companion = new Companion((e) null);
    private final QRBarcodeDecoder barcodeDecoder = new QRBarcodeDecoder(a.StillPhoto, b.Barcode_QR_DMC);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/detection/BarcodeEngineAdapter$Companion;", "", "<init>", "()V", "TAG", "", "MIN_SIZE", "", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    private final List<BarcodeScanResult> getBarcodeScanResult(Bitmap bitmap) {
        int i2;
        int i7;
        int i8;
        ArrayList arrayList = new ArrayList();
        QRBarcodeDecoder qRBarcodeDecoder = getQRBarcodeDecoder(bitmap);
        char[] cArr = qRBarcodeDecoder.f;
        if (bitmap == null) {
            Log.e("SRCB/QRBarcodeDecoder", "Bitmap is null");
            i8 = -1;
            i7 = 8;
            i2 = 2;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Arrays.fill(cArr, 0);
            int height2 = bitmap.getHeight() * bitmap.getWidth();
            int[] iArr = new int[height2];
            bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            System.out.println("temp size :" + height2);
            Log.i("ImageUtil", "bitmap size :" + bitmap.getWidth() + ": " + bitmap.getHeight());
            byte[] bArr = new byte[(height2 * 3)];
            for (int i10 = 0; i10 < height2; i10++) {
                int i11 = iArr[i10];
                int i12 = i10 * 3;
                bArr[i12] = (byte) ((i11 & 16711680) >> 16);
                bArr[i12 + 1] = (byte) ((i11 & 65280) >> 8);
                bArr[i12 + 2] = (byte) (i11 & ScoverState.TYPE_NFC_SMART_COVER);
            }
            i7 = 8;
            i2 = 2;
            String barcodeRecognizeRGB = QRBarcodeDecoder.barcodeRecognizeRGB(bArr, width, height, qRBarcodeDecoder.d[0], qRBarcodeDecoder.f, qRBarcodeDecoder.e);
            Log.i("SRCB/QRBarcodeDecoder", "barcodeRecognizeRGB mResultRoi: " + qRBarcodeDecoder.d[0][0] + ArcCommonLog.TAG_COMMA + qRBarcodeDecoder.d[0][1] + ArcCommonLog.TAG_COMMA + qRBarcodeDecoder.d[0][2] + ArcCommonLog.TAG_COMMA + qRBarcodeDecoder.d[0][3] + ArcCommonLog.TAG_COMMA + qRBarcodeDecoder.d[0][4] + ArcCommonLog.TAG_COMMA + qRBarcodeDecoder.d[0][5] + ArcCommonLog.TAG_COMMA + qRBarcodeDecoder.d[0][6] + ArcCommonLog.TAG_COMMA + qRBarcodeDecoder.d[0][7]);
            qRBarcodeDecoder.g[0] = "";
            qRBarcodeDecoder.f3257h[0] = "";
            if (!TextUtils.isEmpty(barcodeRecognizeRGB)) {
                int i13 = 0;
                while (i13 < 50 && cArr[i13] != 0) {
                    i13++;
                }
                if (cArr[0] != 0 && i13 < 50 && i13 > 0) {
                    qRBarcodeDecoder.f3257h[0] = String.copyValueOf(cArr, 0, i13);
                }
                if (TextUtils.isEmpty(qRBarcodeDecoder.f3257h[0])) {
                    qRBarcodeDecoder.f3257h[0] = "None";
                } else {
                    qRBarcodeDecoder.g[0] = barcodeRecognizeRGB;
                    i8 = (int) (System.currentTimeMillis() - currentTimeMillis);
                }
            }
            i8 = -1;
        }
        LibLogger.d("BarcodeEngineAdapter", "process: result=" + i8 + ", getRecognizedObjectCount()=" + qRBarcodeDecoder.a());
        if (i8 > 0) {
            int a7 = qRBarcodeDecoder.a();
            for (int i14 = 0; i14 < a7; i14++) {
                BarcodeScanResult barcodeScanResult = new BarcodeScanResult(String.format(Locale.UK, "%s:%s", Arrays.copyOf(new Object[]{qRBarcodeDecoder.d(i14), qRBarcodeDecoder.c(i14)}, i2)), new int[i7]);
                qRBarcodeDecoder.b(i14, barcodeScanResult.getQrPositionArray());
                arrayList.add(barcodeScanResult);
            }
        }
        qRBarcodeDecoder.e();
        return arrayList;
    }

    private final QRBarcodeDecoder getQRBarcodeDecoder(Bitmap bitmap) {
        int i2;
        int i7;
        int i8;
        QRBarcodeDecoder qRBarcodeDecoder = this.barcodeDecoder;
        boolean z = false;
        int[] iArr = {0, 0, bitmap.getWidth(), bitmap.getHeight()};
        for (int i10 = 0; i10 < 4; i10++) {
            qRBarcodeDecoder.f3256c[i10] = iArr[i10];
        }
        qRBarcodeDecoder.getClass();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        qRBarcodeDecoder.b = height;
        qRBarcodeDecoder.f3255a = width;
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap.createBitmap(width, height, config);
        Bitmap.createBitmap(400, 400, config);
        qRBarcodeDecoder.f3259j = 90;
        qRBarcodeDecoder.f3258i = L2.a.q(width, height, 400, 400, 90);
        qRBarcodeDecoder.f3258i.invert(new Matrix());
        if (qRBarcodeDecoder.f3259j.intValue() % MOCRLang.KHMER == 90) {
            z = true;
        }
        Log.d("SRCB/QRBarcodeDecoder", "initData width = " + width + ", height: " + height);
        int i11 = qRBarcodeDecoder.b;
        float f = (float) i11;
        if (z) {
            i2 = qRBarcodeDecoder.f3255a;
        } else {
            i2 = i11;
        }
        float f5 = f / ((float) i2);
        int i12 = qRBarcodeDecoder.f3255a;
        float f8 = (float) i12;
        if (!z) {
            i11 = i12;
        }
        float min = Math.min(f5, f8 / ((float) i11));
        int i13 = qRBarcodeDecoder.f3255a;
        int i14 = qRBarcodeDecoder.b;
        if (z) {
            i7 = i14;
        } else {
            i7 = i13;
        }
        int i15 = (int) (((float) i7) * min);
        if (z) {
            i8 = i13;
        } else {
            i8 = i14;
        }
        L2.a.q(i13, i14, i15, (int) (min * ((float) i8)), qRBarcodeDecoder.f3259j.intValue());
        return this.barcodeDecoder;
    }

    private final boolean isDecodeAvailable(Bitmap bitmap) {
        boolean z;
        if (bitmap.getWidth() < 100 || bitmap.getHeight() < 100) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            LibLogger.d("BarcodeEngineAdapter", "isDecodeAvailable true");
            return z;
        }
        String logString = LogUtil.INSTANCE.logString(bitmap);
        LibLogger.e("BarcodeEngineAdapter", "isDecodeAvailable false: " + logString + " is too small!");
        return z;
    }

    public List<BarcodeScanResult> process(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        if (!isDecodeAvailable(bitmap)) {
            return C1202t.d;
        }
        String logString = LogUtil.INSTANCE.logString(bitmap);
        LibLogger.d("BarcodeEngineAdapter", "process barcode with " + logString);
        return getBarcodeScanResult(bitmap);
    }
}
