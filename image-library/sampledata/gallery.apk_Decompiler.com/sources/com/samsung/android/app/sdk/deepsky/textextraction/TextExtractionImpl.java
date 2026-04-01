package com.samsung.android.app.sdk.deepsky.textextraction;

import android.content.Context;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper.OcrWrapper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelperImpl;
import com.samsung.android.sdk.ocr.OCRType;
import com.samsung.android.sdk.ocr.Recognizer;
import com.samsung.android.sum.core.types.NumericEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtractionImpl;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtraction;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper;", "getTextExtractionDrawHelper", "(Landroid/content/Context;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/Recognizer;", "getRecognizer", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/Recognizer;", "Lcom/samsung/android/sdk/ocr/OCRType;", "ocrType", "", "isSupported", "(Lcom/samsung/android/sdk/ocr/OCRType;)Z", "Landroid/content/Context;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextExtractionImpl implements TextExtraction {
    public static final Companion Companion = new Companion((e) null);
    private final Context context;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtractionImpl$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TextExtractionImpl(Context context2) {
        j.e(context2, "context");
        this.context = context2;
        String packageName = context2.getPackageName();
        LibLogger.i("TextExtractionImpl", "TextExtraction initialized at " + packageName);
    }

    public Recognizer getRecognizer() {
        return new RecognizerImpl(this.context, (Recognizer) null, 2, (e) null);
    }

    public TextExtractionDrawHelper getTextExtractionDrawHelper(Context context2) {
        j.e(context2, "context");
        return new TextExtractionDrawHelperImpl(context2);
    }

    public boolean isSupported(OCRType oCRType) {
        j.e(oCRType, "ocrType");
        boolean isSupported = OcrWrapper.INSTANCE.isSupported(this.context, oCRType);
        String name = oCRType.name();
        LibLogger.i("TextExtractionImpl", "isSupported " + name + NumericEnum.SEP + isSupported);
        return isSupported;
    }
}
