package com.samsung.android.app.sdk.deepsky.textextraction;

import L2.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Looper;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType;
import com.samsung.android.app.sdk.deepsky.textextraction.entity.OcrEntityExtractor;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LogUtil;
import com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper.OcrDataConverter;
import com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper.TextExtractionOCRLanguage;
import com.samsung.android.app.sdk.deepsky.textextraction.result.EntityData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;
import com.samsung.android.app.sdk.deepsky.textextraction.result.TextData;
import com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.util.BitmapUtil;
import com.samsung.android.sdk.ocr.OCRResult;
import com.samsung.android.sdk.ocr.OCRType;
import com.samsung.android.sdk.ocr.Recognizer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.k;
import me.x;
import ne.C1202t;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000b\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ#\u0010\u001f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J-\u0010\"\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010!\u001a\u00020\rH\u0002¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\nH\u0002¢\u0006\u0004\b$\u0010\u001cJ\u000f\u0010%\u001a\u00020\nH\u0002¢\u0006\u0004\b%\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010&R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010'¨\u0006)"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/RecognizerImpl;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/Recognizer;", "Landroid/content/Context;", "context", "Lcom/samsung/android/sdk/ocr/Recognizer;", "recognizer", "<init>", "(Landroid/content/Context;Lcom/samsung/android/sdk/ocr/Recognizer;)V", "", "language", "Lme/x;", "initialize", "(I)V", "", "keepOpen", "(IZ)V", "Landroid/graphics/Bitmap;", "bitmap", "detectText", "(Landroid/graphics/Bitmap;)Z", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "extractTextData", "(Landroid/graphics/Bitmap;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;", "ocrData", "extractEntityData", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/result/OcrData;)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "release", "()V", "Landroid/graphics/Rect;", "validRect", "detectTextInternal", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;)Z", "extractOcrDataOnly", "extractTextDataInternal", "(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Z)Lcom/samsung/android/app/sdk/deepsky/textextraction/result/TextData;", "checkThread", "checkIfRecognizerValid", "Landroid/content/Context;", "Lcom/samsung/android/sdk/ocr/Recognizer;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RecognizerImpl implements Recognizer {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;
    private Recognizer recognizer;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/RecognizerImpl$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public RecognizerImpl(Context context2, Recognizer recognizer2) {
        j.e(context2, "context");
        this.context = context2;
        this.recognizer = recognizer2;
    }

    private final void checkIfRecognizerValid() {
        if (this.recognizer == null) {
            throw new IllegalStateException("DeepSky Recognizer is not initialized");
        }
    }

    private final void checkThread() {
        if (j.a(Thread.currentThread(), Looper.getMainLooper().getThread())) {
            throw new IllegalStateException("Should be called on worker thread");
        }
    }

    private final boolean detectTextInternal(Bitmap bitmap, Rect rect) {
        Bitmap bitmap2;
        Object obj;
        boolean z;
        checkThread();
        checkIfRecognizerValid();
        if (rect == null) {
            Bitmap.Config config = bitmap.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }
            bitmap2 = bitmap.copy(config, true);
        } else {
            bitmap2 = BitmapUtil.INSTANCE.getCroppedBitmap(bitmap, rect);
        }
        try {
            Recognizer recognizer2 = this.recognizer;
            if (recognizer2 != null) {
                z = recognizer2.detectText(bitmap2);
            } else {
                z = false;
            }
            LibLogger.i("RecognizerImpl", "detectTextInternal detectText=" + z);
            obj = Boolean.valueOf(z);
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            LibLogger.e("RecognizerImpl", "Recognizer failed", a7);
        }
        if (obj instanceof me.j) {
            obj = null;
        }
        Boolean bool = (Boolean) obj;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static /* synthetic */ boolean detectTextInternal$default(RecognizerImpl recognizerImpl, Bitmap bitmap, Rect rect, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            rect = null;
        }
        return recognizerImpl.detectTextInternal(bitmap, rect);
    }

    private final TextData extractTextDataInternal(Bitmap bitmap, Rect rect, boolean z) {
        Bitmap bitmap2;
        Object obj;
        boolean z3;
        checkThread();
        checkIfRecognizerValid();
        if (rect == null) {
            Bitmap.Config config = bitmap.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }
            bitmap2 = bitmap.copy(config, true);
        } else {
            bitmap2 = BitmapUtil.INSTANCE.getCroppedBitmap(bitmap, rect);
        }
        OCRResult oCRResult = new OCRResult();
        try {
            Recognizer recognizer2 = this.recognizer;
            if (recognizer2 != null) {
                obj = Boolean.valueOf(recognizer2.recognize(bitmap2, oCRResult));
            } else {
                obj = null;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            LibLogger.e("RecognizerImpl", "Recognizer failed", a7);
        }
        if (obj instanceof me.j) {
            obj = null;
        }
        Boolean bool = (Boolean) obj;
        if (bool != null) {
            z3 = bool.booleanValue();
        } else {
            z3 = false;
        }
        LibLogger.i("RecognizerImpl", "recognized: " + z3);
        if (rect == null) {
            rect = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        }
        Rect rect2 = rect;
        if (!z3) {
            LibLogger.i("RecognizerImpl", "extractTextInternal empty result");
            return new TextData(new OcrData((String) null, C1202t.d, rect2, 1, (e) null), (EntityData) null, 2, (e) null);
        }
        OcrData ocrData = OcrDataConverter.INSTANCE.toOcrData(oCRResult, rect2);
        if (z) {
            return new TextData(ocrData, (EntityData) null, 2, (e) null);
        }
        return new TextData(ocrData, extractEntityData(ocrData).getEntityData());
    }

    public static /* synthetic */ TextData extractTextDataInternal$default(RecognizerImpl recognizerImpl, Bitmap bitmap, Rect rect, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            rect = null;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return recognizerImpl.extractTextDataInternal(bitmap, rect, z);
    }

    public boolean detectText(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        String logString = LogUtil.INSTANCE.logString(bitmap);
        LibLogger.i("RecognizerImpl", "detectText with " + logString);
        return detectTextInternal$default(this, bitmap, (Rect) null, 2, (Object) null);
    }

    public TextData extractEntityData(OcrData ocrData) {
        j.e(ocrData, "ocrData");
        int size = ocrData.getBlockList().size();
        LibLogger.i("RecognizerImpl", "extractEntityData with " + size + " blocks");
        return new TextData(ocrData, OcrDataConverter.INSTANCE.toEntityData(OcrEntityExtractor.INSTANCE.extractEntity(ocrData, EntityType.Companion.listOfEntityType(), ocrData.getValidRect(), new TextClassificationHelper(this.context)), ocrData.getValidRect()));
    }

    public TextData extractTextData(Bitmap bitmap) {
        j.e(bitmap, "bitmap");
        String logString = LogUtil.INSTANCE.logString(bitmap);
        LibLogger.i("RecognizerImpl", "extractTextData with " + logString);
        return extractTextDataInternal$default(this, bitmap, (Rect) null, false, 6, (Object) null);
    }

    public void initialize(int i2) {
        initialize(i2, false);
    }

    public void release() {
        Object obj;
        checkThread();
        try {
            Recognizer recognizer2 = this.recognizer;
            obj = null;
            if (recognizer2 != null) {
                recognizer2.close();
                this.recognizer = null;
                obj = x.f4917a;
            }
        } catch (Throwable th) {
            obj = a.l(th);
        }
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            LibLogger.e("RecognizerImpl", "Recognizer failed", a7);
        }
    }

    public void initialize(int i2, boolean z) {
        Object obj;
        TextExtractionOCRLanguage byValue = TextExtractionOCRLanguage.Companion.getByValue(i2);
        String name = byValue.name();
        LibLogger.i("RecognizerImpl", "initialize Recognizer with language=" + name + " keepOpen=" + z);
        checkThread();
        if (this.recognizer == null) {
            try {
                obj = new Recognizer(this.context, OCRType.OCR_ALL, byValue.getConvertedId$deepsky_sdk_textextraction_8_5_30_release(), z);
            } catch (Throwable th) {
                obj = a.l(th);
            }
            Throwable a7 = k.a(obj);
            if (a7 != null) {
                LibLogger.e("RecognizerImpl", "Recognizer failed", a7);
            }
            if (obj instanceof me.j) {
                obj = null;
            }
            this.recognizer = (Recognizer) obj;
            return;
        }
        throw new IllegalStateException("Recognizer already initialized");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RecognizerImpl(Context context2, Recognizer recognizer2, int i2, e eVar) {
        this(context2, (i2 & 2) != 0 ? null : recognizer2);
    }
}
