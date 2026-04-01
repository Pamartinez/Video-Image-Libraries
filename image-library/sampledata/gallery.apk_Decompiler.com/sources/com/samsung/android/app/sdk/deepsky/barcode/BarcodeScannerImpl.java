package com.samsung.android.app.sdk.deepsky.barcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.barcode.detection.BarcodeEngine;
import com.samsung.android.app.sdk.deepsky.barcode.detection.BarcodeEngineAdapter;
import com.samsung.android.app.sdk.deepsky.barcode.detection.BarcodeScanResult;
import com.samsung.android.app.sdk.deepsky.barcode.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.barcode.logger.LogUtil;
import com.samsung.android.app.sdk.deepsky.barcode.parser.BarcodeParser;
import com.samsung.android.app.sdk.deepsky.barcode.parser.BarcodeParserImpl;
import com.samsung.android.app.sdk.deepsky.barcode.parser.LegacyParserAdapter;
import com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.BarcodeParsedResult;
import com.samsung.android.app.sdk.deepsky.barcode.result.ActionUIResource;
import com.samsung.android.app.sdk.deepsky.barcode.result.Barcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u001b\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/BarcodeScannerImpl;", "Lcom/samsung/android/app/sdk/deepsky/barcode/BarcodeScanner;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "engine", "Lcom/samsung/android/app/sdk/deepsky/barcode/detection/BarcodeEngine;", "parser", "Lcom/samsung/android/app/sdk/deepsky/barcode/parser/BarcodeParser;", "process", "", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/Barcode;", "bitmap", "Landroid/graphics/Bitmap;", "getRectFromIntArray", "Landroid/graphics/Rect;", "qrPositionArray", "", "getArrayPoint", "", "Landroid/graphics/Point;", "([I)[Landroid/graphics/Point;", "getActionUIResource", "Lcom/samsung/android/app/sdk/deepsky/barcode/result/ActionUIResource;", "rawData", "", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BarcodeScannerImpl implements BarcodeScanner {
    public static final Companion Companion = new Companion((e) null);
    private final BarcodeEngine engine = new BarcodeEngineAdapter();
    private final BarcodeParser parser;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/BarcodeScannerImpl$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public BarcodeScannerImpl(Context context) {
        j.e(context, "context");
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "getApplicationContext(...)");
        this.parser = new BarcodeParserImpl(new LegacyParserAdapter(applicationContext));
    }

    private final ActionUIResource getActionUIResource(String str) {
        BarcodeParsedResult parsedBarcodeResult = this.parser.getParsedBarcodeResult(str);
        return new ActionUIResource(parsedBarcodeResult.getTitle(), parsedBarcodeResult.getBody(), parsedBarcodeResult.getBodyTts(), parsedBarcodeResult.getActions());
    }

    private final Point[] getArrayPoint(int[] iArr) {
        Point[] pointArr = {new Point(iArr[0], iArr[1]), new Point(iArr[2], iArr[3]), new Point(iArr[4], iArr[5]), new Point(iArr[6], iArr[7])};
        String arrays = Arrays.toString(pointArr);
        j.d(arrays, "toString(...)");
        LibLogger.d("BarcodeScannerImpl", "getArrayPoint array=".concat(arrays));
        return pointArr;
    }

    private final Rect getRectFromIntArray(int[] iArr) {
        Rect rect = new Rect(iArr[0], iArr[1], iArr[4], iArr[5]);
        LibLogger.d("BarcodeScannerImpl", "getRectFromIntArray rect=" + rect);
        return rect;
    }

    public List<Barcode> process(Bitmap bitmap) {
        Barcode barcode;
        j.e(bitmap, "bitmap");
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        LogUtil logUtil = LogUtil.INSTANCE;
        j.b(copy);
        String logString = logUtil.logString(copy);
        LibLogger.i("BarcodeScannerImpl", "process barcode with " + logString);
        try {
            ArrayList arrayList = new ArrayList();
            for (BarcodeScanResult barcodeScanResult : this.engine.process(copy)) {
                if (barcodeScanResult.getRawData().length() > 0) {
                    barcode = new Barcode(barcodeScanResult.getRawData(), getRectFromIntArray(barcodeScanResult.getQrPositionArray()), getArrayPoint(barcodeScanResult.getQrPositionArray()), getActionUIResource(barcodeScanResult.getRawData()));
                } else {
                    barcode = null;
                }
                if (barcode != null) {
                    arrayList.add(barcode);
                }
            }
            return arrayList;
        } catch (Exception e) {
            String message = e.getMessage();
            LibLogger.w("BarcodeScannerImpl", "process barcode failed: " + message);
            return C1202t.d;
        }
    }
}
